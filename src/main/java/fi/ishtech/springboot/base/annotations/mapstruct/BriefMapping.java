package fi.ishtech.springboot.base.annotations.mapstruct;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.mapstruct.Qualifier;

/**
 * Qualifier for mapstruct mappers.<br>
 * Used on entities that do not need nested entities.
 *
 * @author Muneer Ahmed Syed
 */
@Qualifier
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.CLASS)
public @interface BriefMapping {

}