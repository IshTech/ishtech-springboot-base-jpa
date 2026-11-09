package fi.ishtech.springboot.base.service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import fi.ishtech.springboot.base.entity.BaseLovEntity;
import fi.ishtech.springboot.base.entity.BaseLovLangEntity;
import fi.ishtech.springboot.base.mapper.BaseLovLangMapper;
import fi.ishtech.springboot.base.repo.BaseLovLangRepo;
import fi.ishtech.springboot.base.vo.BaseLovEntityVo;
import fi.ishtech.springboot.base.vo.BaseLovLangEntityVo;
import fi.ishtech.common.i18n.enums.LangEnum;

/**
 * Base interface for operations on lov lang entity classes
 *
 * @param <T> extends {@link BaseLovLangEntity}
 * @param <V> extends {@link BaseLovLangEntityVo}
 *
 * @author Muneer Ahmed Syed
 */
public interface BaseLovLangService<T extends BaseLovLangEntity<? extends BaseLovEntity>, V extends BaseLovLangEntityVo<? extends BaseLovEntityVo>>
		extends BaseStandardService<T, V> {

	@Override
	BaseLovLangRepo<T> getRepo();

	@Override
	BaseLovLangMapper getMapper();

	/**
	 * Finds by name and lang
	 *
	 * @param lovName {@link String}
	 * @param lang {@link LangEnum}
	 * @return {@link Optional}&gt;T&lt;
	 */
	default Optional<T> findOneByLovNameAndLang(String lovName, LangEnum lang) {
		return getRepo().findOneByLovNameAndLang(lovName, lang.name());
	}

	/**
	 * Finds by lovId and lang
	 *
	 * @param lovId {@link Long}
	 * @param lang {@link LangEnum}
	 * @return T
	 */
	default Optional<T> findOneByLovIdAndLang(Long lovId, LangEnum lang) {
		return getRepo().findOneByLovIdAndLang(lovId, lang.name());
	}

	/**
	 * Finds by lovId and lang and if not present returns null
	 *
	 * @param lovId {@link Long}
	 * @param lang {@link LangEnum}
	 * @return T
	 */
	default T findOneByLovIdAndLangOrElseNull(Long lovId, LangEnum lang) {
		return this.findOneByLovIdAndLang(lovId, lang).orElse(null);
	}

	/**
	 * Finds by name and lang and if not present throws {@link NoSuchElementException}.
	 *
	 * @param lovName {@link String}
	 * @param lang {@link LangEnum}
	 * @return T
	 */
	default T findOneByLovNameAndLangOrElseNull(String lovName, LangEnum lang) {
		return this.findOneByLovNameAndLang(lovName, lang).orElse(null);
	}

	/**
	 * Finds by lovId and lang and if not present throws {@link NoSuchElementException}.
	 *
	 * @param lovId {@link Long}
	 * @param lang {@link LangEnum}
	 * @return T
	 */
	default T findOneByLovIdAndLangOrElseThrow(Long lovId, LangEnum lang) {
		return this.findOneByLovIdAndLang(lovId, lang)
				.orElseThrow(() -> new NoSuchElementException("Not found for lov:" + lovId + ", lang:" + lang));
	}

	/**
	 * Finds by lovName and lang and if not present throws {@link NoSuchElementException}.
	 *
	 * @param lovName {@link String}
	 * @param lang {@link LangEnum}
	 * @return T
	 */
	default T findOneByLovNameAndLangOrElseThrow(String lovName, LangEnum lang) {
		return this.findOneByLovNameAndLang(lovName, lang)
				.orElseThrow(() -> new NoSuchElementException("Not found for lov:" + lovName + ", lang:" + lang));
	}

	/**
	 * Finds all by lang
	 *
	 * @param lang {@link LangEnum}
	 * @return {@link List}&gt;T&lt;
	 */
	default List<T> findAllByLang(LangEnum lang) {
		return getRepo().findAllByLang(lang.name());
	}

	/**
	 * Finds all by lang with isActive true
	 *
	 * @param lang {@link LangEnum}
	 * @return {@link List}&gt;T&lt;
	 */
	default List<T> findAllActiveByLang(LangEnum lang) {
		return getRepo().findAllByLangAndIsActiveTrue(lang.name());
	}

	/**
	 * Finds all by lovId
	 *
	 * @param lovId {@link Long}
	 * @return {@link List}&gt;T&lt;
	 */
	default List<T> findAllByLovId(Long lovId) {
		return getRepo().findAllByLovId(lovId);
	}

	/**
	 * Finds all active by lovId
	 *
	 * @param lovId {@link Long}
	 * @return {@link List}&gt;T&lt;
	 */
	default List<T> findAllActiveByLovId(Long lovId) {
		return getRepo().findAllByLovIdAndIsActiveTrue(lovId);
	}

	/**
	 * Finds all active by lovName
	 *
	 * @param lovName {@link String}
	 * @return {@link List}&lt;T&gt;
	 */
	default List<T> findAllActiveByLovName(String lovName) {
		return getRepo().findAllByLovNameAndIsActiveTrue(lovName);
	}

}