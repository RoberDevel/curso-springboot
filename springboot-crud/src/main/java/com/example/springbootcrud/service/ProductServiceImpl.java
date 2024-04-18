package com.example.springbootcrud.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.springbootcrud.entity.Product;
import com.example.springbootcrud.repository.ProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    private ProductRepository productRepository;

    @Transactional(readOnly = true)
    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return productRepository.findById(id);
    }

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional
    @Override
    public Optional<Product> update(Long id, Product product) {
        Optional<Product> productOptional = productRepository.findById(id);

        if (productOptional.isPresent()) {
            Product productdb = productOptional.orElseThrow();
            productdb.setName(product.getName());
            productdb.setPrice(product.getPrice());
            productdb.setDescription(product.getDescription());
            productdb.setSku(product.getSku());
            return Optional.of(productRepository.save(productdb));
        }

        return productOptional;

    }

    @Transactional
    @Override
    public Optional<Product> delete(Long id) {
        Optional<Product> productOptional = productRepository.findById(id);
        productOptional.ifPresent(productRepository::delete);
        if (!productOptional.isPresent()) {
            System.out.println("Product not found");
        }

        return productOptional;

    }

    @Transactional(readOnly = true)
    @Override
    public boolean existsBySku(String sku) {
        return productRepository.existsBySku(sku);
    }

}
