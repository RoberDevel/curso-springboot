package com.curso.springboot.inyeccion_dependencias.repository;

import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;

import com.curso.springboot.inyeccion_dependencias.model.Product;

@Repository
public class ProductRepositoryFoo implements IProductRepository {

    @Override
    public List<Product> findAll() {

        return Collections.singletonList(new Product(1L, "Product 1", 100));

    }

    @Override
    public Product findById(Long id) {
        return new Product(1L, "Product 1", 100);

    }

}
