package com.curso.springboot.inyeccion_dependencias.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import com.curso.springboot.inyeccion_dependencias.model.Product;
import com.curso.springboot.inyeccion_dependencias.repository.IProductRepository;

@Service
public class ProductServiceImpl implements IProductService {

    @Value("${config.price.tax}")
    private Double tax;

    @Autowired
    // Con Environment se puede acceder a las propiedades de configuración de Spring
    // o de las configuraciones que hayas creado a traves de
    // environment.getProperty("xx", xx.class)
    private Environment environment;

    @Autowired
    // @Qualifier("productList") Sin el qualifier va al @Primary, si no hay
    // @Primary, se debe especificar el nombre del bean
    private IProductRepository productRepository;

    /*
     * @Autowired
     * public void setProductRepository(ProductRepositoryImpl productRepository) {
     * this.productRepository = productRepository;
     * }
     * 
     * public ProductServiceImpl(@Qualifier("productRepositoryImpl")
     * IProductRepository productRepository) {
     * this.productRepository = productRepository;
     * }
     */

    @Override
    public List<Product> findAll() {

        System.out.println(environment.getProperty("config.price.tax", Double.class));
        System.out.println(tax);// se obtiene el tax desde config.properties que creamos nosotros, a partir de
                                // environment como arriba, o usando @Value en la instancia de tax

        return productRepository.findAll()
                .stream().map(product -> {
                    Product newProduct = new Product(product.getId(), product.getName(),
                            (int) (product.getPrice() * tax));
                    return newProduct;

                }).toList();

    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(id);
    }

}
