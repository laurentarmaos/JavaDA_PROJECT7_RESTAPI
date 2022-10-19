package com.nnk.springboot.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;


public class PasswordValidationValidator implements ConstraintValidator<PasswordValidation, String>{

	@Override
	public boolean isValid(String password, ConstraintValidatorContext context) {


		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[-+_!@#$%^&*., ?]).+$";
		Pattern p = Pattern.compile(regex);

		Matcher m = p.matcher(password);

		return m.matches();

	    }

}
