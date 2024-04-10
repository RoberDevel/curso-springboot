package com.example.springbootweb.model.dto;

import java.time.LocalDateTime;

import com.example.springbootweb.model.Persona;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PersonaDto {

    private LocalDateTime now;
    private Persona persona;
    private String title;
}
