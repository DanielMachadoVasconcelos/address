/**
 * 
 */
package br.com.fexco.address.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import br.com.fexco.address.validators.Postcode;

/**
 * 
 * <p>A PostCodeValidator check if the fragment is a postcode or a address
 * fragment.
 *
 * @TODO: Pattern from wikipedia, check if is correct.
 *O
 * @author daniel.vasconcelos
 * @see https://en.wikipedia.org/wiki/Postcodes_in_the_United_Kingdom#Validation
 */
public class PostCodeValidator implements ConstraintValidator<Postcode, String> {

	private Pattern pattern = Pattern.compile(
			"(?:[A-Za-z]\\d ?\\d[A-Za-z]{2})|(?:[A-Za-z][A-Za-z\\d]\\d ?\\d[A-Za-z]{2})|(?:[A-Za-z]{2}\\d{2} ?\\d[A-Za-z]{2})|(?:[A-Za-z]\\d[A-Za-z] ?\\d[A-Za-z]{2})|(?:[A-Za-z]{2}\\d[A-Za-z] ?\\d[A-Za-z]{2})");

	@Override
	public void initialize(Postcode arg0) {
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext arg1) {
		Matcher m = pattern.matcher(value);
		return m.matches();
	}

}
