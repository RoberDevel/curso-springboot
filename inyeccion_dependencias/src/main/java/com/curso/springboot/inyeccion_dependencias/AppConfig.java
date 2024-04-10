package com.curso.springboot.inyeccion_dependencias;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;

import com.curso.springboot.inyeccion_dependencias.repository.IProductRepository;
import com.curso.springboot.inyeccion_dependencias.repository.ProductRepositoryJson;

@Configuration
@PropertySource("classpath:config.properties")
public class AppConfig {

    @Value("classpath:json/product.json")
    private Resource resource;

    @Bean
    @Primary
    IProductRepository productRepository() {
        return new ProductRepositoryJson(resource);
        // return new ProductRepositoryJson(); // hay 2 constructores de
        // ProductRepositoryJson, uno se le pasa un Resource y el otro no
    }

}
