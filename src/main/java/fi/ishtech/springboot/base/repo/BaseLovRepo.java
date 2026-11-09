package fi.ishtech.springboot.base.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.query.Param;

import fi.ishtech.springboot.base.entity.BaseLovEntity;

/**
 * Base Repository
 *
 * @param <T> extends {@link BaseLovEntity}
 *
 * @author Muneer Ahmed Syed
 */
@NoRepositoryBean
public interface BaseLovRepo<T extends BaseLovEntity> extends BaseStandardRepo<T> {

	/**
	 * Finds by name
	 *
	 * @param name {@link String}
	 * @return {@link Optional}&lt;T&gt;
	 */
	Optional<T> findOneByName(String name);

	/**
	 * Find ID by name
	 *
	 * @param name {@link String}
	 * @return ID {@link Long}
	 */
	@Query("SELECT u.id FROM #{#entityName} u WHERE u.name = :name")
	Long findIdByName(@Param("name") String name);

	/**
	 * Finds active and order by displayOrder
	 *
	 * @return {@link List}&lt;T&gt;
	 */
	List<T> findAllByIsActiveTrueOrderByDisplayOrder();

}