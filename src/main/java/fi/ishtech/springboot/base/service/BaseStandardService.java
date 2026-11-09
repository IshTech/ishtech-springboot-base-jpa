package fi.ishtech.springboot.base.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fi.ishtech.springboot.base.entity.BaseStandardEntity;
import fi.ishtech.springboot.base.mapper.BaseStandardMapper;
import fi.ishtech.springboot.base.payload.filter.BaseStandardEntityFilterParams;
import fi.ishtech.springboot.base.repo.BaseStandardRepo;
import fi.ishtech.springboot.base.spec.BaseStandardSpec;
import fi.ishtech.springboot.base.vo.BaseStandardEntityVo;

/**
 * Base interface for operations on standard (with auto id) entity classes
 *
 * @param <T> extends {@link BaseStandardEntity}
 * @param <V> extends {@link BaseStandardEntityVo}
 *
 * @author Muneer Ahmed Syed
 */
public interface BaseStandardService<T extends BaseStandardEntity, V extends BaseStandardEntityVo>
		extends BaseStandardNoIdService<T, V, Long> {

	@Override
	BaseStandardRepo<T> getRepo();

	@Override
	BaseStandardMapper getMapper();

	/**
	 * Checks by id if entity exists or not
	 *
	 * @param id {@link Long}
	 * @return {@code boolean}
	 */
	default boolean existsById(Long id) {
		return getRepo().existsById(id);
	}

	/**
	 * Finds by id
	 *
	 * @param id {@link Long}
	 * @return {@link Optional}&lt;T&gt;
	 */
	default Optional<T> findOneById(Long id) {
		return getRepo().findById(id);
	}

	/**
	 * Finds by id and if not present returns null
	 *
	 * @param id {@link Long}
	 * @return T
	 */
	default T findOneByIdOrElseNull(Long id) {
		return this.findOneById(id).orElse(null);
	}

	/**
	 * Finds by id and if not present, throws {@link NoSuchElementException}.
	 *
	 * @param id {@link Long}
	 * @return T
	 */
	default T findOneByIdOrElseThrow(Long id) {
		return this.findOneById(id).orElseThrow();
	}

	/**
	 * Finds by id and if not present, throws {@link RuntimeException}.
	 *
	 * @param id {@link Long}
	 * @return T
	 */
	default T findOneByIdOrElseThrow(Long id, Supplier<? extends RuntimeException> exceptionSupplier) {
		return this.findOneById(id).orElseThrow(exceptionSupplier);
	}

	/**
	 * Finds by id and map to VO and if not present, throws {@link NoSuchElementException}.
	 *
	 * @param id {@link Long}
	 * @return V
	 */
	@SuppressWarnings("unchecked")
	default V findOneByIdAndMapToVoOrElseThrow(Long id) {
		return (V) getMapper().toBaseStandardVo(this.findOneByIdOrElseThrow(id));
	}

	/**
	 * Finds all based on specification and pagination and maps to vo
	 *
	 * @param spec     {@link BaseStandardSpec}
	 * @param pageable {@link Pageable}
	 *
	 * @see BaseStandardEntityFilterParams
	 *
	 * @return {@link Page} of {@link BaseStandardEntityVo}
	 */
	@SuppressWarnings("unchecked")
	default Page<V> findAllAndMapToVo(BaseStandardSpec<T, ? extends BaseStandardEntityFilterParams> spec,
			Pageable pageable) {
		return (Page<V>) this.findAll(spec, pageable).map(getMapper()::toBaseStandardVo);
	}

}