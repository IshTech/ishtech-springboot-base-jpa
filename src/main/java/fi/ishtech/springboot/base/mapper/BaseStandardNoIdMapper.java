package fi.ishtech.springboot.base.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fi.ishtech.springboot.base.entity.BaseStandardNoIdEntity;
import fi.ishtech.springboot.base.vo.BaseStandardNoIdEntityVo;

/**
 * Base mapper for entities to/from vo with standard columns
 *
 * @author Muneer Ahmed Syed
 */
@Mapper(componentModel = "spring")
public interface BaseStandardNoIdMapper extends BaseEntityMapper {

	/**
	 * Maps base fields.
	 *
	 * @param entity of type {@link BaseStandardNoIdEntity}
	 * @return Vo of type {@link BaseStandardNoIdEntityVo}
	 */
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "active", target = "active")
	@Mapping(source = "description", target = "description")
	BaseStandardNoIdEntityVo toBaseStandardNoIdVo(BaseStandardNoIdEntity entity);

}