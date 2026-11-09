package fi.ishtech.springboot.base.vo;

import java.io.Serial;

import jakarta.validation.constraints.Pattern;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for value objects (vo) of entities of list of values in different languages
 *
 * @param <T> extends {@link BaseLovEntityVo}
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public class BaseLovLangEntityVo<T extends BaseLovEntityVo> extends BaseStandardEntityVo {

	@Serial
	private static final long serialVersionUID = -6143956753977250377L;

	protected Long lovId;

	protected String label;

	@Pattern(regexp = "[a-z]{2}")
	protected String lang;

	@EqualsAndHashCode.Exclude
	@ToString.Exclude
	protected T lov;

}