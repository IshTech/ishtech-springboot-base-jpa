package fi.ishtech.springboot.base.mapper;

import org.mapstruct.Mapper;

import fi.ishtech.springboot.base.entity.BaseEntity;
import fi.ishtech.springboot.base.vo.BaseEntityVo;

/**
 * Base mapper for entities to/from vo
 *
 * @see BaseEntity
 * @see BaseEntityVo
 *
 * @author Muneer Ahmed Syed
 */
@Mapper
public interface BaseEntityMapper extends BaseMapper {

}