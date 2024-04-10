package com.example.springbootdifactura.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Item {

    private Product product;
    private Integer quantity;

    public Double getImporte() {
        return quantity * product.getPrice();
    }
}
