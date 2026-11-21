package fi.ishtech.springboot.base.vo;

import java.io.Serial;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for value objects (vo) of entities of master data tables
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BaseMasterEntityVo extends BaseStandardEntityVo {

	@Serial
	private static final long serialVersionUID = -6678121111551066535L;

	@NotBlank
	protected String name;

	@NotBlank
	protected String label;

	@NotBlank
	@Pattern(regexp = "[a-z]{2}")
	protected String lang;

}