package com.example.springbootdifactura.model;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Invoice {

    @Autowired
    private Client client;

    @Value("${invoice.description.office}")
    private String description;

    @Autowired
    @Qualifier("officeItems")
    private List<Item> items;

    @PostConstruct
    public void init() {
        System.out.println("Creando la factura para: " + client.getName() + " " + client.getLastname());

    }

    @PreDestroy
    public void destroy() {
        System.out.println("Destruyendo la factura para: " + client.getName() + " " + client.getLastname());
    }

    public Double getTotal() {
        return items.stream().mapToDouble(Item::getImporte).sum();
    }

}
