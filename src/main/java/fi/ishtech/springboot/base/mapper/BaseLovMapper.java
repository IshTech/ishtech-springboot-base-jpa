package fi.ishtech.springboot.base.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fi.ishtech.springboot.base.annotations.mapstruct.BriefMapping;
import fi.ishtech.springboot.base.entity.BaseLovEntity;
import fi.ishtech.springboot.base.vo.BaseLovEntityVo;

/**
 * Base mapper for entities to/from vo with lov columns
 *
 * @author Muneer Ahmed Syed
 */
@Mapper(componentModel = "spring")
public interface BaseLovMapper extends BaseStandardMapper {

	@BriefMapping
	@BeanMapping(ignoreByDefault = true)
	@InheritConfiguration(name = "toBaseStandardVo")
	@Mapping(source = "name", target = "name")
	@Mapping(source = "displayOrder", target = "displayOrder")
	BaseLovEntityVo toBaseLovBriefVo(BaseLovEntity entity);

}