package fi.ishtech.springboot.base.entity;

import java.io.Serial;

import jakarta.persistence.Column;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MappedSuperclass;

import fi.ishtech.common.i18n.enums.LangEnum;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for entities of list of values in different languages
 *
 * @param <T> extends {@link BaseLovEntity}
 *
 * @author Muneer Ahmed Syed
 */
@MappedSuperclass
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseLovLangEntity<T extends BaseLovEntity> extends BaseStandardEntity {

	@Serial
	private static final long serialVersionUID = 6838611459522981673L;

	@Column(name = "lov_id", nullable = false, insertable = true, updatable = false)
	protected Long lovId;

	@Column(name = "label", nullable = false, insertable = true, updatable = true)
	protected String label;

	@Column(name = "lang", length = 2, nullable = false, insertable = true, updatable = false)
	protected String lang;

	public LangEnum getLangEnum() {
		return LangEnum.fromNameIgnoreCase(this.lang);
	}

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	@ManyToOne(optional = false, fetch = FetchType.EAGER)
	@JoinColumn(name = "lov_id", referencedColumnName = "id", nullable = true, insertable = false, updatable = false)
	protected T lov;

}