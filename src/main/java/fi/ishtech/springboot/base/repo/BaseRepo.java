package fi.ishtech.springboot.base.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import fi.ishtech.springboot.base.entity.BaseEntity;

/**
 * Base Repository
 *
 * @param <T>  extends {@link BaseEntity}
 * @param <ID> id
 *
 * @author Muneer Ahmed Syed
 */
@NoRepositoryBean
public interface BaseRepo<T extends BaseEntity, ID> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

}