package com.example.springbootcrud.validation;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.example.springbootcrud.entity.Product;

@Component
public class ProductValidation implements Validator {

    @Override
    public boolean supports(Class<?> clazz) {
        return Product.class.isAssignableFrom(clazz);

    }

    @Override
    public void validate(Object target, Errors errors) {
        Product product = (Product) target;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", null, "es requerido");
        // ValidationUtils.rejectIfEmptyOrWhitespace(errors, "description",
        // "NotBlank.product.description");

        if (product.getDescription() == null || product.getDescription().isBlank()) {
            errors.rejectValue("description", null, "es requerido");
        }
        if (product.getPrice() == null) {
            errors.rejectValue("price", null, "es requerido");
        } else if (product.getPrice() < 100) {
            errors.rejectValue("price", null, "debe ser mayor o igual a 100");
        }

    }

}
