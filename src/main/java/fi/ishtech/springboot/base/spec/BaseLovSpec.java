package fi.ishtech.springboot.base.spec;

import java.io.Serial;
import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import fi.ishtech.springboot.base.entity.BaseLovEntity;
import fi.ishtech.springboot.base.entity.BaseLovLangEntity;
import fi.ishtech.springboot.base.entity.BaseLovLangEntity_;
import fi.ishtech.springboot.base.payload.filter.BaseLovEntityFilterParams;

/**
 * {@link Specification} base class to build criteria
 *
 * @param <T> extends BaseLovEntity
 * @param <P> extends BaseAuditableEntityFilterParams
 *
 * @author Muneer Ahmed Syed
 */
public abstract class BaseLovSpec<T extends BaseLovEntity, P extends BaseLovEntityFilterParams>
		extends BaseLovWithoutLovLangSpec<T, P> {

	@Serial
	private static final long serialVersionUID = 924267496795483499L;

	/**
	 * Constructor with filter params
	 *
	 * @param params P
	 */
	public BaseLovSpec(P params) {
		super(params);
	}

	@Override
	protected List<Predicate> toPredicateList(Root<T> root, CriteriaBuilder cb) {
		var predicates = super.toPredicateList(root, cb);

		return predicates;
	}

	protected void addLovLangPredicates(List<Predicate> predicates,
			Join<? extends BaseLovEntity, ? extends BaseLovLangEntity<?>> joinLovLangs, CriteriaBuilder cb) {

		if (getParams().getLovLang() != null) {
			addPredicateLike(predicates, joinLovLangs, cb, getParams().getLovLang().getLovId(),
					BaseLovLangEntity_.LOV_ID);
			addPredicateLike(predicates, joinLovLangs, cb, getParams().getLovLang().getLabel(),
					BaseLovLangEntity_.LABEL);
			addPredicateEq(predicates, joinLovLangs, cb, getParams().getLovLang().getLang(), BaseLovLangEntity_.LANG);
			addPredicateIn(predicates, joinLovLangs, cb, getParams().getLovLang().getLangArr(),
					BaseLovLangEntity_.LANG);
		}
	}

}