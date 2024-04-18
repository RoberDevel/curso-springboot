package com.example.springbootcrud.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = IsRequiredValidation.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD, ElementType.METHOD })
public @interface IsRequired {

    String message() default "es requerido (usando anotaciones del paquete validation)";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

}
