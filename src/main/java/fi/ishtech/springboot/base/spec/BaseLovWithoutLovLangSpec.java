package fi.ishtech.springboot.base.spec;

import java.io.Serial;
import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import fi.ishtech.springboot.base.entity.BaseLovEntity;
import fi.ishtech.springboot.base.entity.BaseLovEntity_;
import fi.ishtech.springboot.base.payload.filter.BaseLovEntityWithoutLovLangFilterParams;

/**
 * {@link Specification} base class to build criteria
 *
 * @param <T> extends BaseLovEntity
 * @param <P> extends BaseLovEntityWithoutLovLangFilterParams
 *
 * @author Muneer Ahmed Syed
 */
public abstract class BaseLovWithoutLovLangSpec<T extends BaseLovEntity, P extends BaseLovEntityWithoutLovLangFilterParams>
		extends BaseStandardSpec<T, P> {

	@Serial
	private static final long serialVersionUID = 250453381737732289L;

	/**
	 * Constructor with filter params
	 *
	 * @param params P
	 */
	public BaseLovWithoutLovLangSpec(P params) {
		super(params);
	}

	@Override
	protected List<Predicate> toPredicateList(Root<T> root, CriteriaBuilder cb) {
		var predicates = super.toPredicateList(root, cb);

		addPredicateLike(predicates, root, cb, getParams().getName(), BaseLovEntity_.NAME);

		return predicates;
	}

}