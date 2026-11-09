package fi.ishtech.springboot.base.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fi.ishtech.springboot.base.entity.BaseStandardEntity;
import fi.ishtech.springboot.base.vo.BaseStandardEntityVo;

/**
 * Base mapper for entities to/from vo with standard columns and id
 *
 * @author Muneer Ahmed Syed
 */
@Mapper(componentModel = "spring")
public interface BaseStandardMapper extends BaseStandardNoIdMapper {

	/**
	 * Maps base fields.
	 *
	 * @param entity of type {@link BaseStandardEntity}
	 * @return Vo of type {@link BaseStandardEntityVo}
	 */
	@BeanMapping(ignoreByDefault = true)
	@InheritConfiguration(name = "toBaseStandardNoIdVo")
	@Mapping(source = "id", target = "id")
	BaseStandardEntityVo toBaseStandardVo(BaseStandardEntity entity);

}