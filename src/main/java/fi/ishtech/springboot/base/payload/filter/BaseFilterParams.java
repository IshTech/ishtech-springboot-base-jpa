package fi.ishtech.springboot.base.payload.filter;

import java.io.Serial;

import fi.ishtech.springboot.base.vo.BaseVo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for filter parameters in request
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ToString(callSuper = true)
public abstract class BaseFilterParams implements BaseVo {

	@Serial
	private static final long serialVersionUID = 7672866470370773784L;

}