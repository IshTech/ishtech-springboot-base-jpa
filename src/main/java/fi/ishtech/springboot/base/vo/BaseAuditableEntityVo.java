package fi.ishtech.springboot.base.vo;

import java.io.Serial;
import java.time.LocalDateTime;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for value objects (vo) of entities having audit columns
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BaseAuditableEntityVo extends BaseEntityVo {

	@Serial
	private static final long serialVersionUID = 1400234507679207900L;

	private Long createdBy;

	private LocalDateTime createdAt;

	private Long updatedBy;

	private LocalDateTime updatedAt;

}