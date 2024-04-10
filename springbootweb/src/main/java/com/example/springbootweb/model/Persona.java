package com.example.springbootweb.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Persona {

    private String name;
    private String lastname;
    private String email;

}
