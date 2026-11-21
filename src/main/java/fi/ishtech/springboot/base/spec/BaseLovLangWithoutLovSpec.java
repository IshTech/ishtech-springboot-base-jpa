package fi.ishtech.springboot.base.spec;

import java.io.Serial;
import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import fi.ishtech.springboot.base.entity.BaseLovLangEntity;
import fi.ishtech.springboot.base.entity.BaseLovLangEntity_;
import fi.ishtech.springboot.base.payload.filter.BaseLovLangEntityWithoutLovFilterParams;

/**
 * {@link Specification} base class to build criteria
 *
 * @param <T> extends {@link BaseLovLangEntity}
 * @param <P> extends {@link BaseLovLangEntityWithoutLovFilterParams}
 *
 * @author Muneer Ahmed Syed
 */
public abstract class BaseLovLangWithoutLovSpec<T extends BaseLovLangEntity<?>, P extends BaseLovLangEntityWithoutLovFilterParams>
		extends BaseStandardSpec<T, P> {

	@Serial
	private static final long serialVersionUID = -5563368345031577148L;

	/**
	 * Constructor with filter params
	 *
	 * @param params P
	 */
	public BaseLovLangWithoutLovSpec(P params) {
		super(params);
	}

	@Override
	protected List<Predicate> toPredicateList(Root<T> root, CriteriaBuilder cb) {
		var predicates = super.toPredicateList(root, cb);

		addPredicateLike(predicates, root, cb, getParams().getLovId(), BaseLovLangEntity_.LOV_ID);
		addPredicateLike(predicates, root, cb, getParams().getLabel(), BaseLovLangEntity_.LABEL);
		addPredicateEq(predicates, root, cb, getParams().getLang(), BaseLovLangEntity_.LANG);
		addPredicateIn(predicates, root, cb, getParams().getLangArr(), BaseLovLangEntity_.LANG);

		return predicates;
	}

}