package fi.ishtech.springboot.base.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import fi.ishtech.springboot.base.entity.BaseAuditableEntity;
import fi.ishtech.springboot.base.vo.BaseAuditableEntityVo;

/**
 * Base mapper for entities to/from vo with audit columns
 *
 * @author Muneer Ahmed Syed
 */
@Mapper(componentModel = "spring")
public interface BaseAuditableMapper extends BaseEntityMapper {

	/**
	 * Maps base fields.
	 *
	 * @param entity of type {@link BaseAuditableEntity}
	 * @return Vo of type {@link BaseAuditableEntityVo}
	 */
	@BeanMapping(ignoreByDefault = true)
	@Mapping(source = "createdBy", target = "createdBy")
	@Mapping(source = "createdAt", target = "createdAt")
	@Mapping(source = "updatedBy", target = "updatedBy")
	@Mapping(source = "updatedAt", target = "updatedAt")
	BaseAuditableEntityVo toBaseAuditableVo(BaseAuditableEntity entity);

}