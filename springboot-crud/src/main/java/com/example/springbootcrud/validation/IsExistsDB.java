package com.example.springbootcrud.validation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IsExistsDBValidation.class)
public @interface IsExistsDB {
    String message() default "ya existe con ese valor en la bbdd";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
