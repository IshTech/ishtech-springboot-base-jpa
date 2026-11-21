package fi.ishtech.springboot.base.payload.filter;

import java.io.Serial;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for filter parameters for BaseStandardNoIdEntity in request
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseStandardNoIdEntityFilterParams extends BaseEntityFilterParams {

	@Serial
	private static final long serialVersionUID = 5094236893124339566L;

	protected Boolean isActive;

	protected String description;

}