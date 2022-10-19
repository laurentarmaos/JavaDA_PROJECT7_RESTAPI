package com.nnk.springboot.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;



@Retention(RUNTIME)
@Target(FIELD)
@Constraint(validatedBy = {PasswordValidationValidator.class})
public @interface PasswordValidation {

	String message() default "Password must contains at least 1 lowercase, 1 uppercase, 1 digit and 1 special character";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
