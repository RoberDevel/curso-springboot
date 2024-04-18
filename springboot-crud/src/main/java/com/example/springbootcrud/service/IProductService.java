package com.example.springbootcrud.service;

import java.util.List;
import java.util.Optional;

import com.example.springbootcrud.entity.Product;

public interface IProductService {
    List<Product> findAll();

    Optional<Product> findById(Long id);

    Product save(Product product);

    Optional<Product> update(Long id, Product product);

    Optional<Product> delete(Long id);

    boolean existsBySku(String sku);
}
