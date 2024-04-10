package com.example.springbootweb.controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.springbootweb.model.Persona;
import com.example.springbootweb.model.dto.PersonaDto;

@RestController
@RequestMapping("/api")
public class PersonaRestController {

    @GetMapping("/details")
    public Map<String, Object> details() {

        Persona persona = new Persona("roberto", "perez", null);

        Map<String, Object> body = new HashMap<>();
        body.put("title", "hola mundo");
        body.put("persona", persona);

        return body;
    }

    @GetMapping("/details_dto")
    public PersonaDto detailsDto() {

        Persona persona = new Persona("roberto", "perez", null);
        PersonaDto personaDto = new PersonaDto();
        personaDto.setPersona(persona);
        personaDto.setTitle("hola mundo");
        personaDto.setNow(LocalDateTime.now());

        return personaDto;
    }

}
