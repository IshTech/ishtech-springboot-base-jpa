package fi.ishtech.springboot.base.entity;

import java.io.Serial;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for entities of list of values
 *
 * @author Muneer Ahmed Syed
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseLovEntity extends BaseStandardEntity {

	@Serial
	private static final long serialVersionUID = -7299891922991606083L;

	@Column(name = "name", nullable = false, insertable = true, updatable = false, unique = true)
	protected String name;

	@Column(name = "display_order", nullable = true, insertable = true, updatable = true)
	protected Long displayOrder;

}