package com.example.springbootcrud.validation;

import java.lang.annotation.*;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = ExistsByUsernameValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistsByUsername {
    String message() default "ya existe en la base de datos, escoja otro username";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
