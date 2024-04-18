package com.example.springbootcrud.entity;

import com.example.springbootcrud.validation.IsExistsDB;
import com.example.springbootcrud.validation.IsRequired;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // @IsRequired creado en el paquete validation
    @IsRequired(message = "{IsRequired.product.name}")
    @Size(min = 3, max = 50, message = "{Size.product.name}")
    private String name;
    // @IsRequired creado en el paquete validation
    @IsRequired
    private String description;
    @Min(value = 100, message = "{Min.product.price}")
    @NotNull
    private Integer price;

    @IsRequired
    @IsExistsDB
    private String sku;

}
