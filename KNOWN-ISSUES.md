# Known Issues

Known issues that have been confirmed by testing but not yet fixed. Each entry has enough detail to reproduce and fix without re-investigating.

---

## 1. Default VO mapping methods of the base services return base VOs instead of the service's VO type
**Status:** open
**Impact:** high for any service that doesn't override these methods: get by id throws `ClassCastException` in the caller, and lists return items with only the base VO's fields.
**Affects:**
- `BaseStandardService.findOneByIdAndMapToVoOrElseThrow(Long)` and `BaseStandardService.findAllAndMapToVo(spec, pageable)`
- `BaseStandardNoIdService.findOneByIdAndMapToVoOrElseThrow(ID)` and `BaseStandardNoIdService.findAllAndMapToVo(spec, pageable)`
- `BaseLovService.findOneByNameAndMapToVo(String)` and `BaseMasterService.findOneByNameAndMapToVo(String)`, which follow the same pattern (not tested)

### Description
The default methods map the entity with the base mapper method `getMapper().toBaseStandardVo(...)` (or `toBaseStandardNoIdVo(...)`, `toBaseLovBriefVo(...)`, `toBaseMasterVo(...)`). That method creates an instance of the base VO class, for example `BaseStandardEntityVo`, not of the service's VO type `V`, and the result is cast unchecked to `V` or `Page<V>`:

```java
default V findOneByIdAndMapToVoOrElseThrow(Long id) {
	return (V) getMapper().toBaseStandardVo(this.findOneByIdOrElseThrow(id));
}

default Page<V> findAllAndMapToVo(BaseStandardSpec<T, ? extends BaseStandardEntityFilterParams> spec, Pageable pageable) {
	return (Page<V>) this.findAll(spec, pageable).map(getMapper()::toBaseStandardVo);
}
```

Example, with a `Product` entity and its `ProductVo`:

```java
public class Product extends BaseStandardEntity {
	private String name;
	private BigDecimal unitPrice;
}

public class ProductVo extends BaseStandardEntityVo {
	private String name;
	private BigDecimal unitPrice;
}

public interface ProductService extends BaseStandardService<Product, ProductVo> {
}
```

If `ProductServiceImpl` doesn't override the two methods:

```java
ProductVo productVo = productService.findOneByIdAndMapToVoOrElseThrow(id);
// ClassCastException: class fi.ishtech.springboot.base.vo.BaseStandardEntityVo cannot be cast to class ...ProductVo

Page<ProductVo> page = productService.findAllAndMapToVo(spec, pageable);
// no exception, but each item is a BaseStandardEntityVo: only id, isActive and description, without name and unitPrice
```

Why get by id fails and the list doesn't: because of type erasure, the casts inside the methods check almost nothing; `(V)` becomes a cast to `BaseStandardEntityVo` and `(Page<V>)` a cast to `Page`, and both succeed. The real check is in the caller. Assigning the get-by-id result to a `ProductVo` makes the compiler insert a cast to `ProductVo`, which fails because the object is only a `BaseStandardEntityVo`. For the list, the caller's cast is only to `Page`, which succeeds, and the items are never cast to `ProductVo`; serializing the page to JSON returns them with only the base fields. Only code that reads an item as `ProductVo`, for example `ProductVo first = page.getContent().get(0);`, fails with the same `ClassCastException`.

`ProductServiceImpl` avoids the issue by overriding both methods and mapping with its own mapper, so the default methods never run:

```java
@Override
public ProductVo findOneByIdAndMapToVoOrElseThrow(Long id) {
	return productMapper.toSemiDetailVo(findOneByIdOrElseThrow(id));
}

@Override
public Page<ProductVo> findAllAndMapToVo(BaseStandardSpec<Product, ? extends BaseStandardEntityFilterParams> spec,
		Pageable pageable) {
	return findAll(spec, pageable).map(productMapper::toBriefVo);
}
```

The behavior dates from commit `d94df71` (`remove default toBriefVo methods from base classes to avoid conflicts in implementations in child services`, 2025-11-12), which replaced `getMapper().toBriefVo(...)` with the base mapper methods in these default methods. `toBriefVo` was declared in the base mappers and implemented by each child mapper, so it returned the concrete VO type; the base mapper methods don't.

### Steps to reproduce
With `Product`, `ProductVo` and `ProductService` as in the example, and a `ProductServiceImpl` that doesn't override the two methods:
1. Save a `Product` with a `name` and a `unitPrice`.
2. Call `productService.findOneByIdAndMapToVoOrElseThrow(id)` with its `id` and assign the result to a `ProductVo` variable.
   - Expected: a `ProductVo` with `name` and `unitPrice`.
   - Actual: `ClassCastException`, as in the example.
3. Call `productService.findAllAndMapToVo(spec, pageable)` and serialize the page to JSON.
   - Expected: items with `name` and `unitPrice`.
   - Actual: items with only `id`, `isActive` and `description`.

### Likely cause
The base services have no way to map to the concrete VO type `V`: the base mappers only declare methods that return the base VO classes, and the unchecked casts hide the mismatch at compile time.

### Suggested fix
Recommended: make the default methods return a real instance of `V` with the base fields filled, so that they keep working for services that don't override them, without a breaking change:
1. Add a base mapper method that fills an existing VO instead of creating one, for example `void toExistingBaseStandardVo(BaseStandardEntity entity, @MappingTarget BaseStandardEntityVo vo);`. MapStruct sets the base fields on whatever subclass instance it is given. Give it a new name, not `toBaseStandardVo`: child mappers use `@InheritConfiguration(name = "toBaseStandardVo")`, and a second method with that name could make it ambiguous and break their compilation.
2. In the default methods, resolve the service's VO class `V` from the service's class with Spring's `ResolvableType`, for example `ResolvableType.forClass(getClass()).as(BaseStandardService.class).resolveGeneric(1)`, create an instance with its no-argument constructor, and fill it with the method from step 1.
3. Remove the unchecked casts and `@SuppressWarnings("unchecked")`.
4. Do the same in `BaseStandardNoIdService`, `BaseLovService` and `BaseMasterService`.
5. Add tests with a concrete entity, VO and service, checking for get by id and for a page that the returned objects are of the VO class and have the base fields.

The consumer classes don't need code changes, with these conditions:

| Class | Change needed | Why |
|---|---|---|
| `ProductService` | none | It already declares `V` as `ProductVo` (`BaseStandardService<Product, ProductVo>`), which `ResolvableType` reads. |
| `ProductServiceImpl` | none | Without overrides, the default methods return a real `ProductVo` with the base fields. Keep the overrides, as above, to get the VO's own fields, such as `name` and `unitPrice`. |
| `ProductMapper` | none, but recompile | MapStruct generates the new base mapper method in `ProductMapperImpl`, because `ProductMapper` extends `BaseStandardMapper`. Consumers get it when they build against the new version. |
| `ProductVo` | only if it has no no-argument constructor | The default methods create the VO with its no-argument constructor, so the VO must have one and must not be abstract. Lombok `@Data` provides one; a VO with only `@Builder` or `@AllArgsConstructor` also needs `@NoArgsConstructor`, otherwise the default methods fail at runtime. |
