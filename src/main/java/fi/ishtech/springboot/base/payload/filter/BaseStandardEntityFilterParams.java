package fi.ishtech.springboot.base.payload.filter;

import java.io.Serial;

import jakarta.validation.constraints.PositiveOrZero;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for filter parameters for BaseStandardEntity in request
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseStandardEntityFilterParams extends BaseStandardNoIdEntityFilterParams {

	@Serial
	private static final long serialVersionUID = 6966264259842692602L;

	/**
	 * ID
	 */
	@PositiveOrZero
	protected Long id;

	/**
	 * To decide apply equal or like search for ID
	 */
	protected boolean idLikeSearch = false;

}