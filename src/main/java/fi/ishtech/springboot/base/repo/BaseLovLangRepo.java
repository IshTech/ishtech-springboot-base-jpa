package fi.ishtech.springboot.base.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.NoRepositoryBean;

import fi.ishtech.springboot.base.entity.BaseLovEntity;
import fi.ishtech.springboot.base.entity.BaseLovLangEntity;

/**
 * Base Repository
 *
 * @param <T> extends {@link BaseLovLangEntity}
 *
 * @author Muneer Ahmed Syed
 */
@NoRepositoryBean
public interface BaseLovLangRepo<T extends BaseLovLangEntity<? extends BaseLovEntity>> extends BaseStandardRepo<T> {

	/**
	 * Finds by ID and lang
	 *
	 * @param lovId {@link Long}
	 * @param lang  {@link String}
	 * @return {@link Optional}&lt;T&gt;
	 */
	Optional<T> findOneByLovIdAndLang(Long lovId, String lang);

	/**
	 * Finds by name and lang
	 *
	 * @param lovName {@link String}
	 * @param lang    {@link String}
	 * @return {@link Optional}&lt;T&gt;
	 */
	Optional<T> findOneByLovNameAndLang(String lovName, String lang);

	/**
	 * Finds by lang
	 *
	 * @param lang {@link String}
	 * @return {@link List}&lt;T&gt;
	 */
	List<T> findAllByLang(String lang);

	/**
	 * Finds active by lang
	 *
	 * @param lang String
	 * @return {@link List}&lt;T&gt;
	 */
	List<T> findAllByLangAndIsActiveTrue(String lang);

	/**
	 * Finds by lovId
	 *
	 * @param lovId {@link Long}
	 * @return {@link List}&lt;T&gt;
	 */
	List<T> findAllByLovId(Long lovId);

	/**
	 * Finds active by lovId
	 *
	 * @param lovId Long
	 * @return {@link List}&lt;T&gt;
	 */
	List<T> findAllByLovIdAndIsActiveTrue(Long lovId);

	/**
	 * Finds active by name
	 *
	 * @param lovName
	 * @return {@link List}&lt;T&gt;
	 */
	List<T> findAllByLovNameAndIsActiveTrue(String lovName);

}