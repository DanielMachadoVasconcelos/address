/**
 * 
 */
package br.com.fexco.address.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

import br.com.fexco.address.util.PostCodeValidator;

/**
 * 
 * PostCode validator just if need.  
 * 
 * @author daniel.vasconcelos
 *
 */
@Constraint(validatedBy = PostCodeValidator.class)
@Documented
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Postcode {

	String message() default "Invalid postcode.";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};

}
