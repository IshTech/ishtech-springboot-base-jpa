package fi.ishtech.springboot.base.payload.filter;

import java.io.Serial;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * Base class for filter parameters for BaseLovEntity in request
 *
 * @author Muneer Ahmed Syed
 */
@Data
@EqualsAndHashCode(callSuper = true)
@ToString(callSuper = true)
public abstract class BaseLovEntityFilterParams extends BaseLovEntityWithoutLovLangFilterParams {

	@Serial
	private static final long serialVersionUID = -3507526864128524052L;

	protected BaseLovLangEntityWithoutLovFilterParams lovLang;

}