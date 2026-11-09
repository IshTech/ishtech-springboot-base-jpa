package fi.ishtech.springboot.base.service;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Supplier;

import fi.ishtech.springboot.base.entity.BaseMasterEntity;
import fi.ishtech.springboot.base.mapper.BaseMasterMapper;
import fi.ishtech.springboot.base.repo.BaseMasterRepo;
import fi.ishtech.springboot.base.vo.BaseMasterEntityVo;

/**
 * Base interface for operations on master data entity classes
 *
 * @param <T> extends {@link BaseMasterEntity}
 * @param <V> extends {@link BaseMasterEntityVo}
 *
 * @author Muneer Ahmed Syed
 */
public interface BaseMasterService<T extends BaseMasterEntity, V extends BaseMasterEntityVo>
		extends BaseStandardService<T, V> {

	@Override
	BaseMasterRepo<T> getRepo();

	@Override
	BaseMasterMapper getMapper();

	/**
	 * Finds by name
	 *
	 * @param name
	 * @return T
	 */
	default Optional<T> findOneByName(String name) {
		return getRepo().findOneByName(name);
	}

	/**
	 * Finds by name and if not present returns null
	 *
	 * @param name
	 * @return T
	 */
	default T findOneByNameOrElseNull(String name) {
		return this.findOneByName(name).orElse(null);
	}

	/**
	 * Finds by name and if not present throws {@link NoSuchElementException}.
	 *
	 * @param name
	 * @return T
	 */
	default T findOneByNameOrElseThrow(String name) {
		return this.findOneByName(name).orElseThrow(() -> new NoSuchElementException("Not found for name:" + name));
	}

	/**
	 * Finds by name and if not present throws exception of type {@link RuntimeException}.
	 *
	 * @param name
	 * @return T
	 */
	default T findOneByNameOrElseThrow(String name, Supplier<? extends RuntimeException> exceptionSupplier) {
		return this.findOneByName(name).orElseThrow(exceptionSupplier);
	}

	/**
	 * Finds by name and maps to Vo, if not present throws {@link NoSuchElementException}
	 *
	 * @param name
	 * @return
	 */
	@SuppressWarnings("unchecked")
	default V findOneByNameAndMapToVo(String name) {
		return (V) getMapper().toBaseMasterVo(this.findOneByNameOrElseThrow(name));
	}

	/**
	 *
	 * @param name
	 * @return {@link Long} id
	 */
	default Long findIdByName(String name) {
		return getRepo().findIdByName(name);
	}

}