package fi.ishtech.springboot.base.entity;

import java.io.Serial;
import java.util.Objects;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;

import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

import lombok.Data;
import lombok.ToString;

/**
 * Base class for entities with auto generated id
 *
 * @author Muneer Ahmed Syed
 */
@MappedSuperclass
@DynamicInsert
@DynamicUpdate
@Data
@ToString(callSuper = true)
public abstract class BaseStandardEntity extends BaseStandardNoIdEntity {

	@Serial
	private static final long serialVersionUID = -8743230493639803507L;

	@Id
	@Column(insertable = false, updatable = false)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	protected Long id;

	@Override
	public int hashCode() {
		if (id == null) {
			return super.hashCode();
		}
		return id.hashCode();
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) {
			return true;
		}

		if (o == null) {
			return false;
		}

		if (o.getClass() != getClass()) {
			return false;
		}

		BaseStandardEntity that = (BaseStandardEntity) o;
		return Objects.equals(hashCode(), that.hashCode());
	}

}