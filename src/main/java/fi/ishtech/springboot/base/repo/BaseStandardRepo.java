package fi.ishtech.springboot.base.repo;

import org.springframework.data.repository.NoRepositoryBean;

import fi.ishtech.springboot.base.entity.BaseStandardEntity;

/**
 * Base Repository
 *
 * @param <T> extends {@link BaseStandardEntity}
 *
 * @author Muneer Ahmed Syed
 */
@NoRepositoryBean
public interface BaseStandardRepo<T extends BaseStandardEntity> extends BaseStandardNoIdRepo<T, Long> {

}