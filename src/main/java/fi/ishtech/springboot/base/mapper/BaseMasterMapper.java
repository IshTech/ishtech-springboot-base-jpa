package fi.ishtech.springboot.base.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fi.ishtech.springboot.base.entity.BaseMasterEntity;
import fi.ishtech.springboot.base.vo.BaseMasterEntityVo;

/**
 * Base mapper for entities to/from vo for master data columns
 *
 * @author Muneer Ahmed Syed
 */
@Mapper(componentModel = "spring")
public interface BaseMasterMapper extends BaseStandardMapper {

	/**
	 * Maps base fields.
	 *
	 * @param entity of type {@link BaseMasterEntity}
	 * @return Vo of type {@link BaseMasterEntityVo}
	 */
	@BeanMapping(ignoreByDefault = true)
	@InheritConfiguration(name = "toBaseStandardVo")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "label", target = "label")
	@Mapping(source = "lang", target = "lang")
	BaseMasterEntityVo toBaseMasterVo(BaseMasterEntity entity);

}