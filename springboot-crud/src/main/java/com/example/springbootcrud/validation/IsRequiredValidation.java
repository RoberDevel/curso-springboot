package com.example.springbootcrud.validation;

import org.springframework.util.StringUtils;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

//IsRequired es la anotación que creamos
//String es el tipo de dato que vamos a validar
public class IsRequiredValidation implements ConstraintValidator<IsRequired, String> {

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        // return value != null && !value.isBlank() && !value.isEmpty();
        return StringUtils.hasText(value);
    }

}
