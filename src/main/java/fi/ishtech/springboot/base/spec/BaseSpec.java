package fi.ishtech.springboot.base.spec;

import java.io.Serial;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.persistence.criteria.CriteriaQuery;
import jakarta.persistence.criteria.Join;
import jakarta.persistence.criteria.JoinType;
import jakarta.persistence.criteria.Predicate;
import jakarta.persistence.criteria.Root;

import org.apache.commons.lang3.ArrayUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.util.StringUtils;

import fi.ishtech.springboot.base.entity.BaseEntity;
import fi.ishtech.springboot.base.payload.filter.BaseEntityFilterParams;
import fi.ishtech.springboot.base.payload.filter.BaseFilterParams;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

/**
 * {@link Specification} base class with util methods for {@link Predicate}s and {@link Join}s
 *
 * @param <T> extends {@link BaseEntity}
 * @param <P> extends {@link BaseEntityFilterParams}
 *
 * @author Muneer Ahmed Syed
 */
@RequiredArgsConstructor
public abstract class BaseSpec<T extends BaseEntity, P extends BaseFilterParams> implements Specification<T> {

	@Serial
	private static final long serialVersionUID = -3229114388128110817L;

	protected static final String PERCENT = "%";

	@Getter
	private final P params;

	/**
	 * Combines list of predicates with {@code and}
	 *
	 * @param root  {@link Root}&lt;T&gt;
	 * @param query {@link CriteriaQuery}
	 * @param cb    {@link CriteriaBuilder}
	 * @return {@link Predicate}
	 */
	@Override
	public Predicate toPredicate(Root<T> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
		List<Predicate> predicates = toPredicateList(root, cb);
		query.distinct(true);

		Predicate[] predicatesArray = new Predicate[predicates.size()];
		return cb.and(predicates.toArray(predicatesArray));
	}

	/**
	 * Makes list of predicates for attributes in entity
	 *
	 * @param root {@link Root}&lt;T&gt;
	 * @param cb   {@link CriteriaBuilder}
	 * @return {@link List}&lt;{@link Predicate}&gt;
	 */
	protected List<Predicate> toPredicateList(Root<T> root, CriteriaBuilder cb) {
		return new ArrayList<>();
	}

	/**
	 * Add {@link Predicate} for {@code like} for attribute in root
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param root        {@link Root}&lt;T&gt;
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link String}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateLike(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, String attribValue,
			String attribName) {
		if (StringUtils.hasText(attribValue)) {
			predicates.add(cb.like(root.get(attribName), padForSqlLike(attribValue)));
		}
	}

	/**
	 * Add {@link Predicate} for {@code like} for attribute in join
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param join        {@link Join}
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link String}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateLike(List<Predicate> predicates, Join<? extends BaseEntity, ? extends BaseEntity> join,
			CriteriaBuilder cb, String attribValue, String attribName) {
		if (StringUtils.hasText(attribValue)) {
			predicates.add(cb.like(join.get(attribName), padForSqlLike(attribValue)));
		}
	}

	/**
	 * Add {@link Predicate} for {@code like} for attribute in root
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param root        {@link Root}&lt;T&gt;
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link Number}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateLike(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Number attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.like(root.get(attribName).as(String.class), padForSqlLike(attribValue)));
		}
	}

	/**
	 * Add {@link Predicate} for {@code like} for attribute in join
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param join        {@link Join}
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link Number}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateLike(List<Predicate> predicates, Join<? extends BaseEntity, ? extends BaseEntity> join,
			CriteriaBuilder cb, Number attribValue, String attribName) {
		if (attribValue != null) {
			predicates.add(cb.like(join.get(attribName).as(String.class), padForSqlLike(attribValue)));
		}
	}

	/**
	 * Add {@link Predicate} for {@code equal} for attribute in root
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param root        {@link Root}&lt;T&gt;
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link String}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateEq(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, String attribValue,
			String attribName) {
		if (StringUtils.hasText(attribValue)) {
			predicates.add(cb.equal(root.get(attribName), attribValue));
		}
	}

	/**
	 * Add {@link Predicate} for {@code equal} for attribute in root
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param root        {@link Root}&lt;T&gt;
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link Number}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateEq(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Number attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.equal(root.get(attribName), attribValue));
		}
	}

	/**
	 * Add {@link Predicate} for {@code equal} for attribute in root
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param root        {@link Root}&lt;T&gt;
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link Boolean}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateEq(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Boolean attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.equal(root.get(attribName), attribValue));
		}
	}

	/**
	 * Add {@link Predicate} for {@code equal} for attribute in join
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param join        {@link Join}
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link String}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateEq(List<Predicate> predicates, Join<? extends BaseEntity, ? extends BaseEntity> join,
			CriteriaBuilder cb, String attribValue, String attribName) {
		if (attribValue != null) {
			predicates.add(cb.equal(join.get(attribName), attribValue));
		}
	}

	/**
	 * Add {@link Predicate} for {@code equal} for attribute in join
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param join        {@link Join}
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link Number}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateEq(List<Predicate> predicates, Join<? extends BaseEntity, ? extends BaseEntity> join,
			CriteriaBuilder cb, Number attribValue, String attribName) {
		if (attribValue != null) {
			predicates.add(cb.equal(join.get(attribName), attribValue));
		}
	}

	/**
	 * Add {@link Predicate} for {@code equal} for attribute in join
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param join        {@link Join}
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link Number}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateEq(List<Predicate> predicates, Join<? extends BaseEntity, ? extends BaseEntity> join,
			CriteriaBuilder cb, Boolean attribValue, String attribName) {
		if (attribValue != null) {
			predicates.add(cb.equal(join.get(attribName), attribValue));
		}
	}

	/**
	 * Add {@link Predicate} for {@code in} for attribute in root
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param root        {@link Root}&lt;T&gt;
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link Object}[]
	 * @param attribName  {@link String}
	 */
	protected void addPredicateIn(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Object[] attribValue,
			String attribName) {
		if (!ArrayUtils.isEmpty(attribValue)) {
			predicates.add(root.get(attribName).in(attribValue));
		}
	}

	/**
	 * Add {@link Predicate} for {@code in} for attribute in join
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param join        {@link Join}
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link Object}[]
	 * @param attribName  {@link String}
	 */
	protected void addPredicateIn(List<Predicate> predicates, Join<? extends BaseEntity, ? extends BaseEntity> join,
			CriteriaBuilder cb, Object[] attribValue, String attribName) {
		if (!ArrayUtils.isEmpty(attribValue)) {
			predicates.add(join.get(attribName).in(attribValue));
		}
	}

	/**
	 * Add {@link Predicate} for {@code ge} (greater than or equal) for attribute in root
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param root        {@link Root}&lt;T&gt;
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link BigDecimal}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateGE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, BigDecimal attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.ge(root.get(attribName), attribValue));
		}
	}

	/**
	 * Add {@link Predicate} for {@code le} (less than or equal) for attribute in root
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param root        {@link Root}&lt;T&gt;
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link BigDecimal}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateLE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, BigDecimal attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.le(root.get(attribName), attribValue));
		}
	}

	/**
	 * Add {@link Predicate} for {@code ge} (greater than or equal) for attribute in root
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param root        {@link Root}&lt;T&gt;
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link Integer}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateGE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Integer attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.ge(root.get(attribName), attribValue));
		}
	}

	/**
	 * Add {@link Predicate} for {@code le} (less than or equal) for attribute in root
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param root        {@link Root}&lt;T&gt;
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link Integer}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateLE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Integer attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.le(root.get(attribName), attribValue));
		}
	}

	/**
	 * Add {@link Predicate} for {@code ge} (greater than or equal) for attribute in root
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param root        {@link Root}&lt;T&gt;
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link Long}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateGE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Long attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.ge(root.get(attribName), attribValue));
		}
	}

	/**
	 * Add {@link Predicate} for {@code le} (less than or equal) for attribute in root
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param root        {@link Root}&lt;T&gt;
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link Long}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateLE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Long attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.le(root.get(attribName), attribValue));
		}
	}

	/**
	 * Add {@link Predicate} for {@code ge} (greater than or equal) for attribute in root
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param root        {@link Root}&lt;T&gt;
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link String}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateGE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Short attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.ge(root.get(attribName), attribValue));
		}
	}

	/**
	 * Add {@link Predicate} for {@code le} (less than or equal) for attribute in root
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param root        {@link Root}&lt;T&gt;
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link String}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateLE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb, Short attribValue,
			String attribName) {
		if (attribValue != null) {
			predicates.add(cb.le(root.get(attribName), attribValue));
		}
	}

	/**
	 * Add {@link Predicate} for {@code ge} (greater than or equal) for attribute in root
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param root        {@link Root}&lt;T&gt;
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link LocalDateTime}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateGE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb,
			LocalDateTime attribValue, String attribName) {
		if (attribValue != null) {
			predicates.add(cb.greaterThanOrEqualTo(root.get(attribName), attribValue));
		}
	}

	/**
	 * Add {@link Predicate} for {@code le} (less than or equal) for attribute in root
	 *
	 * @param predicates  {@link List}&lt;{@link Predicate}&gt;
	 * @param root        {@link Root}&lt;T&gt;
	 * @param cb          {@link CriteriaBuilder}
	 * @param attribValue {@link LocalDateTime}
	 * @param attribName  {@link String}
	 */
	protected void addPredicateLE(List<Predicate> predicates, Root<T> root, CriteriaBuilder cb,
			LocalDateTime attribValue, String attribName) {
		if (attribValue != null) {
			predicates.add(cb.lessThanOrEqualTo(root.get(attribName), attribValue));
		}
	}

	/**
	 * Get join (if not already present) from root and join type
	 *
	 * @param <X>        Entity to join
	 * @param join       {@link Join}&lt;X,Y&gt;
	 * @param root       {@link Root}&lt;T&gt;
	 * @param joinAttrib {@link String}
	 * @return {@link Join}&lt;X,Y&gt;
	 */
	@SuppressWarnings("hiding")
	protected <T, X> Join<T, X> getJoin(Join<T, X> join, Root<T> root, String joinAttrib) {
		if (join == null) {
			join = (Join<T, X>) root.<T, X>join(joinAttrib);
		}
		return join;
	}

	/**
	 * Get join (if not already present) from root and join type
	 *
	 * @param <X>        Entity to join
	 * @param join       {@link Join}&lt;X,Y&gt;
	 * @param root       {@link Root}&lt;T&gt;
	 * @param joinAttrib {@link String}
	 * @param joinType   {@link JoinType}
	 * @return {@link Join}&lt;X,Y&gt;
	 */
	@SuppressWarnings("hiding")
	protected <T, X> Join<T, X> getJoin(Join<T, X> join, Root<T> root, String joinAttrib, JoinType joinType) {
		if (join == null) {
			join = (Join<T, X>) root.<T, X>join(joinAttrib, joinType);
		}
		return join;
	}

	/**
	 * Get join (if not already present) from a parent join
	 *
	 * @param <X>        Entity to join
	 * @param <Y>        Entity to join
	 * @param join       {@link Join}&lt;X,Y&gt;
	 * @param parentJoin {@link Join}&lt;T,X&gt;
	 * @param joinAttrib {@link String}
	 * @return {@link Join}&lt;X,Y&gt;
	 */
	protected <X, Y> Join<X, Y> getDeepJoin(Join<X, Y> join, Join<T, X> parentJoin, String joinAttrib) {
		if (join == null) {
			join = (Join<X, Y>) parentJoin.<X, Y>join(joinAttrib);
		}
		return join;
	}

	/**
	 * Get join (if not already present) from a parent join and join type
	 *
	 * @param <X>        Entity to join
	 * @param <Y>        Entity to join
	 * @param join       {@link Join}&lt;X,Y&gt;
	 * @param parentJoin {@link Join}&lt;T,X&gt;
	 * @param joinAttrib {@link String}
	 * @param joinType   {@link JoinType}
	 * @return {@link Join}&lt;X,Y&gt;
	 */
	protected <X, Y> Join<X, Y> getDeepJoin(Join<X, Y> join, Join<T, X> parentJoin, String joinAttrib,
			JoinType joinType) {
		if (join == null) {
			join = (Join<X, Y>) parentJoin.<X, Y>join(joinAttrib, joinType);
		}
		return join;
	}

	/**
	 * TODO: move to some util class
	 *
	 * @param values
	 *
	 * @return {@code true} if {@code null} or empty else {@code false}
	 */
	@SuppressWarnings("unused")
	private boolean isArrayEmpty(Object[] values) {
		return values == null || values.length == 0;
	}

	/**
	 * TODO: move to some util class
	 *
	 * @param input {@link String}
	 *
	 * @return input padded with % on either side
	 */
	protected String padForSqlLike(String input) {
		return PERCENT + input + PERCENT;
	}

	/**
	 * TODO: move to some util class
	 *
	 * @param input {@link Number}
	 *
	 * @return input padded with % on either side
	 */
	protected String padForSqlLike(Number input) {
		return PERCENT + input + PERCENT;
	}

	/**
	 * TODO: move to some util class
	 *
	 * @param input {@link Integer}
	 *
	 * @return input padded with % on either side
	 */
	protected String padForSqlLike(Integer input) {
		return PERCENT + input + PERCENT;
	}

}