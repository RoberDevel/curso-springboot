package com.curso.springboot.inyeccion_dependencias.repository;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import com.curso.springboot.inyeccion_dependencias.model.Product;
import com.fasterxml.jackson.databind.ObjectMapper;

public class ProductRepositoryJson implements IProductRepository {

    List<Product> products = null;

    // @Value("classpath:json/product.json")
    // private Resource resource;// se puede implementar de esta manera, pero solo
    // si ProductRepositoryJson es un
    // componente de Spring
    public ProductRepositoryJson() {
        Resource resource = new ClassPathResource("json/product.json");
        readValueJson(resource);
    }

    public ProductRepositoryJson(Resource resource) {

        readValueJson(resource);
    }

    private void readValueJson(Resource resource) {

        // Resource resource = new ClassPathResource("json/product.json");
        ObjectMapper mapper = new ObjectMapper();
        try {
            products = Arrays.asList(mapper.readValue(resource.getFile(), Product[].class));

        } catch (IOException e) {

            e.printStackTrace();
        }
    }

    public ProductRepositoryJson(List<Product> products) {
        this.products = products;
    }

    @Override
    public List<Product> findAll() {

        return products;
    }

    @Override
    public Product findById(Long id) {

        return products.stream().filter(product -> product.getId().equals(id)).findFirst().orElse(null);

    }

}
