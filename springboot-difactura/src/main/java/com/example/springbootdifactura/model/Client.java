package com.example.springbootdifactura.model;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component
public class Client {

    @Value("${client.name}")
    private String name;
    @Value("${client.lastname}")
    private String lastname;
}
