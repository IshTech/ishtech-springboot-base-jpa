package fi.ishtech.springboot.base.vo;

import java.io.Serial;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for value objects (vo) of entities having standard columns
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BaseStandardNoIdEntityVo extends BaseEntityVo {

	@Serial
	private static final long serialVersionUID = -7333245854918653037L;

	/**
	 * {@code null} indicates that the value was not supplied in the incoming payload, allowing partial updates to leave
	 * the stored value unchanged.
	 */
	protected Boolean isActive;

	@JsonProperty
	protected String description;

	/**
	 * Setter for isActive.
	 *
	 * @param isActive
	 *
	 * @see #setIsActive(Boolean)
	 */
	public void setActive(Boolean isActive) {
		this.isActive = isActive;
	}

	/**
	 * Getter for isActive.
	 *
	 * @see #getIsActive()
	 *
	 * @return {@link Boolean}, {@code null} when not supplied
	 */
	@JsonIgnore
	public Boolean getActive() {
		return this.isActive;
	}

	/**
	 * To support noIsPrefix in dependent classes.
	 *
	 * @param isActive
	 *
	 * @see #setActive(Boolean)
	 */
	public void setIsActive(Boolean isActive) {
		this.setActive(isActive);
	}

	/**
	 * To support noIsPrefix in dependent classes.
	 *
	 * @see #getActive()
	 *
	 * @return {@link Boolean}, {@code null} when not supplied
	 */
	@JsonGetter
	public Boolean getIsActive() {
		return this.getActive();
	}

}