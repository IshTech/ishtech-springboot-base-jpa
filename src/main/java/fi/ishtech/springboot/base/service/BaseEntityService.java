package fi.ishtech.springboot.base.service;

import java.util.List;

import jakarta.persistence.EntityManager;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import fi.ishtech.springboot.base.entity.BaseEntity;
import fi.ishtech.springboot.base.mapper.BaseEntityMapper;
import fi.ishtech.springboot.base.payload.filter.BaseFilterParams;
import fi.ishtech.springboot.base.repo.BaseRepo;
import fi.ishtech.springboot.base.spec.BaseSpec;
import fi.ishtech.springboot.base.vo.BaseEntityVo;

/**
 * Base interface for operations on entity classes
 *
 * @param <T>  extends {@link BaseEntity}
 * @param <V>  extends {@link BaseEntityVo}
 * @param <ID> id
 *
 * @author Muneer Ahmed Syed
 */
public interface BaseEntityService<T extends BaseEntity, V extends BaseEntityVo, ID> extends BaseService {

	BaseRepo<T, ID> getRepo();

	BaseEntityMapper getMapper();

	default EntityManager getEntityManager() {
		return null;
	}

	/**
	 * Refreshes entity from DB with latest values
	 *
	 * @param entity T
	 */
	default void refresh(T entity) {
		getEntityManager().refresh(entity);
	}

	/**
	 * Finds all
	 *
	 * @return {@link List}&lt;T&gt;
	 */
	default List<T> findAll() {
		return getRepo().findAll();
	}

	/**
	 * Finds all based on specification and pagination
	 *
	 * @param spec     {@link BaseSpec}
	 * @param pageable {@link Pageable}
	 *
	 * @see BaseFilterParams
	 *
	 * @return {@link Page} of {@link BaseEntity}
	 */
	default Page<T> findAll(BaseSpec<T, ? extends BaseFilterParams> spec, Pageable pageable) {
		return getRepo().findAll(spec, pageable);
	}

}