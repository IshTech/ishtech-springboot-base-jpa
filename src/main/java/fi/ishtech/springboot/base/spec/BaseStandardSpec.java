package fi.ishtech.springboot.base.spec;

import static fi.ishtech.springboot.base.entity.BaseStandardEntity_.ID;

import java.io.Serial;
import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import fi.ishtech.springboot.base.entity.BaseStandardEntity;
import fi.ishtech.springboot.base.payload.filter.BaseStandardEntityFilterParams;

/**
 * {@link Specification} base class to build criteria
 *
 * @param <T> extends {@link BaseStandardEntity}
 * @param <P> extends {@link BaseStandardEntityFilterParams}
 *
 * @author Muneer Ahmed Syed
 */
public abstract class BaseStandardSpec<T extends BaseStandardEntity, P extends BaseStandardEntityFilterParams>
		extends BaseStandardNoIdSpec<T, P> {

	@Serial
	private static final long serialVersionUID = -8725936771922443033L;

	/**
	 * Constructor with filter params
	 *
	 * @param params P
	 */
	public BaseStandardSpec(P params) {
		super(params);
	}

	@Override
	protected List<Predicate> toPredicateList(Root<T> root, CriteriaBuilder cb) {
		List<Predicate> predicates = super.toPredicateList(root, cb);

		if (getParams().isIdLikeSearch()) {
			addPredicateLike(predicates, root, cb, getParams().getId(), ID);
		} else {
			addPredicateEq(predicates, root, cb, getParams().getId(), ID);
		}

		return predicates;
	}

}