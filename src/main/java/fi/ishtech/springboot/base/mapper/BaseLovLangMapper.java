package fi.ishtech.springboot.base.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fi.ishtech.springboot.base.entity.BaseLovLangEntity;
import fi.ishtech.springboot.base.vo.BaseLovLangEntityVo;

/**
 * Base mapper for entities to/from vo with lov and lang columns
 *
 * @author Muneer Ahmed Syed
 */
@SuppressWarnings("rawtypes")
@Mapper(componentModel = "spring")
public interface BaseLovLangMapper extends BaseStandardMapper {

	/**
	 * Maps base fields.
	 *
	 * @param entity of type {@link BaseLovLangEntity}
	 * @return Vo of type {@link BaseLovLangEntityVo}
	 */
	@BeanMapping(ignoreByDefault = true)
	@InheritConfiguration(name = "toBaseStandardVo")
	@Mapping(source = "lovId", target = "lovId")
	@Mapping(source = "label", target = "label")
	@Mapping(source = "lang", target = "lang")
	BaseLovLangEntityVo toBaseLovLangBriefVo(BaseLovLangEntity entity);

}