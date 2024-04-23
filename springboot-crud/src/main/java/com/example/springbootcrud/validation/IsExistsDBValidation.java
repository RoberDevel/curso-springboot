package com.example.springbootcrud.validation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.springbootcrud.service.IProductService;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

@Component
public class IsExistsDBValidation implements ConstraintValidator<IsExistsDB, String> {

    @Autowired
    private IProductService productService;

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {

        if (productService == null)
            return true;

        return !productService.existsBySku(value);
    }

}
