package com.curso.springboot.inyeccion_dependencias.service;

import java.util.List;

import com.curso.springboot.inyeccion_dependencias.model.Product;

public interface IProductService {

    public List<Product> findAll();

    public Product findById(Long id);
}
