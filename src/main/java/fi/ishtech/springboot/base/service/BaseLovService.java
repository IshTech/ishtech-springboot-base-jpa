package fi.ishtech.springboot.base.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

import fi.ishtech.springboot.base.entity.BaseLovEntity;
import fi.ishtech.springboot.base.mapper.BaseLovMapper;
import fi.ishtech.springboot.base.repo.BaseLovRepo;
import fi.ishtech.springboot.base.vo.BaseLovEntityVo;

/**
 * Base interface for operations on lov entity classes
 *
 * @param <T> extends {@link BaseLovEntity}
 * @param <V> extends {@link BaseLovEntityVo}
 *
 * @author Muneer Ahmed Syed
 */
public interface BaseLovService<T extends BaseLovEntity, V extends BaseLovEntityVo> extends BaseStandardService<T, V> {

	@Override
	BaseLovRepo<T> getRepo();

	@Override
	BaseLovMapper getMapper();

	/**
	 * Finds by name
	 *
	 * @param name {@link String}
	 * @return T
	 */
	default Optional<T> findOneByName(String name) {
		return getRepo().findOneByName(name);
	}

	/**
	 * Finds by name and if not present returns null
	 *
	 * @param name {@link String}
	 * @return T
	 */
	default T findOneByNameOrElseNull(String name) {
		return this.findOneByName(name).orElse(null);
	}

	/**
	 * Finds by name and if not present throws {@link NoSuchElementException}.
	 *
	 * @param name {@link String}
	 * @return T
	 */
	default T findOneByNameOrElseThrow(String name) {
		return this.findOneByName(name).orElseThrow(() -> new NoSuchElementException("Not found for lov:" + name));
	}

	/**
	 * Finds by name and if not present throws exception of type {@link RuntimeException}.
	 *
	 * @param name {@link String}
	 * @return T
	 */
	default T findOneByNameOrElseThrow(String name, Supplier<? extends RuntimeException> exceptionSupplier) {
		return this.findOneByName(name).orElseThrow(exceptionSupplier);
	}

	/**
	 * Finds by name and maps to Vo, if not present throws {@link NoSuchElementException}
	 *
	 * @param name {@link String}
	 * @return V
	 */
	@SuppressWarnings("unchecked")
	default V findOneByNameAndMapToVo(String name) {
		return (V) getMapper().toBaseLovBriefVo(this.findOneByNameOrElseThrow(name));
	}

	/**
	 * Finds id by name
	 *
	 * @param name {@link String}
	 * @return {@link Long} id
	 */
	default Long findIdByName(String name) {
		return getRepo().findIdByName(name);
	}

	/**
	 * Finds all entities with isActive true and maps to Vo
	 *
	 * @return {@link List}&lt;V&gt;
	 */
	default List<V> findAllActiveWithLangsAndMapToVo() {
		throw new UnsupportedOperationException();
	}

}