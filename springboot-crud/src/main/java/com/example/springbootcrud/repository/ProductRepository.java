package com.example.springbootcrud.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootcrud.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsBySku(String sku);

}
