package fi.ishtech.springboot.base.repo;

import org.springframework.data.repository.NoRepositoryBean;

import fi.ishtech.springboot.base.entity.BaseAuditableEntity;

/**
 * Base Repository
 *
 * @param <T>  extends {@link BaseAuditableEntity}
 * @param <ID> id
 *
 * @author Muneer Ahmed Syed
 */
@NoRepositoryBean
public interface BaseAuditableRepo<T extends BaseAuditableEntity, ID> extends BaseRepo<T, ID> {

}