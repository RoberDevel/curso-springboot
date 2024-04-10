package com.example.springbootdifactura;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.example.springbootdifactura.model.Item;
import com.example.springbootdifactura.model.Product;

@Configuration
@PropertySource(value = "classpath:data.properties", encoding = "UTF-8") // encoding = "UTF-8" para mostrar tildes y
                                                                         // caracteres especiales
public class AppConfig {

    @Bean
    // @Primary
    @Qualifier("storeItems")
    List<Item> itemInvoice() {
        Product product1 = new Product("Camera Sony", 800.00);
        Product product2 = new Product("Bike", 700.00);
        return Arrays.asList(new Item(product1, 2), new Item(product2, 3));
    }

    @Bean
    @Qualifier("officeItems")
    List<Item> itemInvoiceOficina() {
        Product product1 = new Product("Monitor ", 1000.00);
        Product product2 = new Product("NoteBook", 1200.00);
        return Arrays.asList(new Item(product1, 2), new Item(product2, 3),
                new Item(new Product("Impresora", 750.00), 6));
    }

}
