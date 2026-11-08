package fi.ishtech.springboot.base.validation;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

/**
 * Bean Validation groups that tell the write operations of a resource apart.
 * <p>
 * A constraint that declares no <code>groups</code> belongs to {@link jakarta.validation.groups.Default} and is
 * therefore checked on every validated request. Putting a constraint in one of the groups below restricts it to that
 * operation, so that a single DTO can serve the create, replace and partial-update endpoints of a resource without the
 * requirements of one endpoint being imposed on the others.
 * <p>
 * To validate against a group, name it together with <code>Default</code>, for example
 * <code>&#64;Validated({ Default.class, ValidationGroups.Update.class })</code> on a controller method parameter.
 * Naming a group on its own leaves every <code>Default</code> constraint unchecked.
 * <p>
 * The groups are marker interfaces and carry no constraints of their own.
 *
 * @author Muneer Ahmed Syed
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ValidationGroups {

	/**
	 * Validation group for creating a resource, that is an HTTP <code>POST</code>.
	 * <p>
	 * The request body describes a resource that does not exist yet, so constraints in this group require every
	 * property that the resource cannot be created without, and reject every property that only the server may set.
	 */
	public interface Create {
	}

	/**
	 * Validation group for a full replacement of a resource, that is an HTTP <code>PUT</code>.
	 * <p>
	 * The request body carries the complete resource, so a property that is absent from it is a deliberate instruction
	 * to clear it. Constraints in this group therefore require every property that the resource cannot be without, so
	 * that such a request is rejected with <code>400 Bad Request</code> instead of clearing stored data.
	 *
	 * @see Patch
	 */
	public interface Update {
	}

	/**
	 * Validation group for a partial update of a resource, that is an HTTP <code>PATCH</code>.
	 * <p>
	 * The request body may carry any subset of the resource, and a property that is absent from it is left as stored.
	 * Constraints in this group therefore apply only to the properties that are actually supplied; a constraint that
	 * makes a property mandatory belongs in {@link Update} instead.
	 *
	 * @see Update
	 */
	public interface Patch {
	}

}