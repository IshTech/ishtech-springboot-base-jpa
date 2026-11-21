package fi.ishtech.springboot.base.vo;

import java.io.Serial;

import com.fasterxml.jackson.annotation.JsonAutoDetect;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for value objects (vo) of entities
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
@JsonAutoDetect
public class BaseEntityVo implements BaseVo {

	@Serial
	private static final long serialVersionUID = -1227556520257089092L;

}