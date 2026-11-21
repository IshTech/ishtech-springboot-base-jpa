package fi.ishtech.springboot.base.payload.filter;

import java.io.Serial;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for filter parameters for BaseEntity in request
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = false)
public abstract class BaseEntityFilterParams extends BaseFilterParams {

	@Serial
	private static final long serialVersionUID = -8569707593782893584L;

}