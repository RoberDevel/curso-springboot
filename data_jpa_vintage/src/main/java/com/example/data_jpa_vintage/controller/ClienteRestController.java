package com.example.data_jpa_vintage.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.data_jpa_vintage.service.IClienteService;
import com.example.data_jpa_vintage.view.xml.ClienteList;

@RestController
@RequestMapping("/api/clientes")
public class ClienteRestController {

    @Autowired
    IClienteService clienteService;

    // http://localhost:8080/api/clientes/listar
    // http://localhost:8080/api/clientes/listar?format=json
    // http://localhost:8080/api/clientes/listar?format=xml
    @GetMapping("/listar")
    public ClienteList listarRest() {
        return new ClienteList(clienteService.findAll());
    }

}
