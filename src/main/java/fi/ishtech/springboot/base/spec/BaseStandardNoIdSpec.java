package fi.ishtech.springboot.base.spec;

import static fi.ishtech.springboot.base.entity.BaseStandardNoIdEntity_.DESCRIPTION;
import static fi.ishtech.springboot.base.entity.BaseStandardNoIdEntity_.IS_ACTIVE;

import java.io.Serial;
import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import fi.ishtech.springboot.base.entity.BaseStandardNoIdEntity;
import fi.ishtech.springboot.base.payload.filter.BaseStandardNoIdEntityFilterParams;

/**
 * {@link Specification} base class to build criteria
 *
 * @param <T> extends {@link BaseStandardNoIdEntity}
 * @param <P> extends {@link BaseStandardNoIdEntityFilterParams}
 *
 * @author Muneer Ahmed Syed
 */
public abstract class BaseStandardNoIdSpec<T extends BaseStandardNoIdEntity, P extends BaseStandardNoIdEntityFilterParams>
		extends BaseSpec<T, P> {

	@Serial
	private static final long serialVersionUID = -5813791296499595189L;

	/**
	 * Constructor with filter params
	 *
	 * @param params P
	 */
	public BaseStandardNoIdSpec(P params) {
		super(params);
	}

	@Override
	protected List<Predicate> toPredicateList(Root<T> root, CriteriaBuilder cb) {
		List<Predicate> predicates = super.toPredicateList(root, cb);

		addPredicateEq(predicates, root, cb, getParams().getIsActive(), IS_ACTIVE);
		addPredicateLike(predicates, root, cb, getParams().getDescription(), DESCRIPTION);

		return predicates;
	}

}