package fi.ishtech.springboot.base.payload.filter;

import java.io.Serial;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for filter parameters for BaseAuditableEntity in request
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = false)
public abstract class BaseAuditableEntityFilterParams extends BaseEntityFilterParams {

	@Serial
	private static final long serialVersionUID = 8218319420897955842L;

	private Long createdBy;
	private Long updatedBy;

	private LocalDateTime createdAtMin;
	private LocalDateTime createdAtMax;

	private LocalDateTime updatedAtMin;
	private LocalDateTime updatedAtMax;

}