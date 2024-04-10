package com.example.springbootweb.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.springbootweb.model.Persona;
import com.example.springbootweb.model.dto.ParamDto;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/var")
public class PathVariableController {

    @Value("${config.name}")
    private String name;

    @Value("${config.listOfValues}")
    private List<String> listOfValues;

    @Value("#{'${config.listOfValues}'.split(',')}")
    private List<String> valueList;

    @Value("#{'${config.listOfValues}'.toUpperCase()}")
    private String valueString;

    @Value("#{${config.valuesMap}}")
    private Map<String, Object> valuesMap;

    @Value("#{${config.valuesMap}.email}")
    private String email;

    @Autowired
    private Environment environment;

    @GetMapping("/baz/{message}")
    public ParamDto baz(@PathVariable String message) {
        /*
         * if (!message.equals("")) {
         * message = "adios";
         * } else {
         * message = "coche";
         * }
         */
        ParamDto paramDto = new ParamDto();
        paramDto.setMessage(message);
        return paramDto;
    }

    @GetMapping("/mix/{product}/{id}")
    public Map<String, Object> mixPathVar(@PathVariable String product, @PathVariable Long id) {

        Map<String, Object> json = new HashMap<>();
        json.put("product", product);
        json.put("id", id);
        return json;
    }

    @PostMapping("/create")
    public Persona create(@RequestBody Persona persona) {

        Persona persona2 = new Persona();

        persona2.setName(persona.getName().toUpperCase());
        persona2.setLastname(persona.getLastname());
        persona2.setEmail(persona.getEmail().toUpperCase());

        return persona2;
    }

    @GetMapping("/values")
    public Map<String, Object> values(@Value("${config.lastname}") String lastname) {

        Map<String, Object> json = new HashMap<>();
        json.put("name", name);
        json.put("lastname", lastname);
        json.put("lastname2",
                environment.getProperty("config.lastname") + " environment: " + environment.getClass().getName()
                        + " environment profiles: "
                        + environment.getActiveProfiles());
        json.put("listOfValues", listOfValues);
        json.put("valueList", valueList);
        json.put("valueString", valueString);
        json.put("valuesMap", valuesMap);
        json.put("email", email);

        return json;
    }

}
