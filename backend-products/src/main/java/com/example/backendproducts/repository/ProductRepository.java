package com.example.backendproducts.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.example.backendproducts.entity.Product;

//Junto con la dependencia spring-boot-starter-data-rest ya no es necesario crear un controlador, pero solo para los metodos CRUD basicos, el endpoint es /products
@RepositoryRestResource(path = "products")
@CrossOrigin(origins = { "http://localhost:5173","http://localhost:4200"}) 
public interface ProductRepository extends CrudRepository<Product, Long> {

}
