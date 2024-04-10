package com.curso.springboot.inyeccion_dependencias.controller;

import org.springframework.web.bind.annotation.RestController;

import com.curso.springboot.inyeccion_dependencias.model.Product;
import com.curso.springboot.inyeccion_dependencias.service.IProductService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    IProductService productService;

    @GetMapping()
    public List<Product> list() {

        return productService.findAll();

    }

    @GetMapping("/{id}")
    public Product show(@PathVariable Long id) {

        return productService.findById(id);
    }

}
