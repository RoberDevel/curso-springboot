package com.curso.springboot.inyeccion_dependencias.repository;

import java.util.Arrays;
import java.util.List;

import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;
import org.springframework.web.context.annotation.SessionScope;

import com.curso.springboot.inyeccion_dependencias.model.Product;

@Repository("productList") // se le pone nombre para distinguirlo de otros beans, ya que hay 3 beans de
                           // tipo IProductRepository, uno como primario en AppConfig
// los datos se reinician en cada peticion, la dependencia inyectada se destruye
// al finalizar la peticion y se crea una nueva en la siguiente peticion
// @RequestScope
// los datos se reinician en cada sesion(cerrar navegador por ejemplo), la
// dependencia inyectada se destruye al finalizar la sesion y se crea una nueva
// en la siguiente sesion
// @SessionScope
public class ProductRepositoryImpl implements IProductRepository {

    List<Product> data;

    public ProductRepositoryImpl() {
        this.data = Arrays.asList(
                new Product(1L, "Product 1", 100),
                new Product(2L, "Product 2", 200),
                new Product(3L, "Product 3", 300));
    }

    @Override
    public List<Product> findAll() {
        return data;
    }

    @Override
    public Product findById(Long id) {

        return data.stream().filter(product -> product.getId().equals(id)).findFirst().orElse(null);
    }

}
