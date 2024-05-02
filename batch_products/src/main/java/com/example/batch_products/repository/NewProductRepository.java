package com.example.batch_products.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.batch_products.model.NewProduct;

public interface NewProductRepository extends JpaRepository<NewProduct, Long> {

}
