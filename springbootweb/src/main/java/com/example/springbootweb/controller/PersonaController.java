package com.example.springbootweb.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.example.springbootweb.model.Persona;

@Controller
public class PersonaController {

    @GetMapping("/details2")
    public String details(Model model) {
        Persona persona = new Persona("roberto", "perez", null);
        model.addAttribute("title", "Hola mundo spring");
        model.addAttribute("name", "roberto");
        model.addAttribute("persona", persona);
        return "details";
    }

    @GetMapping("/list")
    public String list(ModelMap model) {

        // model.addAttribute("personas", personas);
        model.addAttribute("title", "Listado de personas");

        return "list";
    }

    @ModelAttribute("personas")
    public List<Persona> personasModel() {
        return Arrays.asList();
        /*
         * Arrays.asList(
         * new Persona("roberto", "perez", null),
         * new Persona("julio", "vazquez", "correo@correo.com"),
         * new Persona("mar", "doe", null));
         */

    }

    @ModelAttribute("personas2")
    public List<Persona> personasModel2() {
        return Arrays.asList();
        /*
         * Arrays.asList(
         * new Persona("roberto2", "perez2", null),
         * new Persona("julio2", "vazquez2", "correo@correo.com"),
         * new Persona("mar2", "doe2", null));
         */
    }

    @ModelAttribute("personas3")
    public List<Persona> personasModel3() {
        return Arrays.asList(
                new Persona("roberto3", "perez3", null),
                new Persona("julio3", "vazquez3", "correo@correo.com"),
                new Persona("mar3", "doe3", null));

    }

}
