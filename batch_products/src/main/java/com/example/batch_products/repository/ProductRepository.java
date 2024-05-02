package com.example.batch_products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.batch_products.model.Product;

public interface ProductRepository extends JpaRepository<Product, Long> {

}
