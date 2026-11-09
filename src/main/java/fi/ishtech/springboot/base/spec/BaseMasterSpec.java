package fi.ishtech.springboot.base.spec;

import java.io.Serial;
import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.springframework.data.jpa.domain.Specification;

import fi.ishtech.springboot.base.entity.BaseMasterEntity;
import fi.ishtech.springboot.base.entity.BaseMasterEntity_;
import fi.ishtech.springboot.base.payload.filter.BaseMasterEntityFilterParams;

/**
 * {@link Specification} base class to build criteria
 *
 * @param <T> extends {@link BaseMasterEntity}
 * @param <P> extends {@link BaseMasterEntityFilterParams}
 *
 * @author Muneer Ahmed Syed
 */
public abstract class BaseMasterSpec<T extends BaseMasterEntity, P extends BaseMasterEntityFilterParams>
		extends BaseStandardSpec<T, P> {

	@Serial
	private static final long serialVersionUID = -7703698994763402281L;

	/**
	 * Constructor with filter params
	 *
	 * @param params P
	 */
	public BaseMasterSpec(P params) {
		super(params);
	}

	@Override
	protected List<Predicate> toPredicateList(Root<T> root, CriteriaBuilder cb) {
		var predicates = super.toPredicateList(root, cb);

		addPredicateLike(predicates, root, cb, getParams().getName(), BaseMasterEntity_.NAME);
		addPredicateLike(predicates, root, cb, getParams().getLabel(), BaseMasterEntity_.LABEL);
		addPredicateLike(predicates, root, cb, getParams().getLang(), BaseMasterEntity_.LANG);

		return predicates;
	}

}