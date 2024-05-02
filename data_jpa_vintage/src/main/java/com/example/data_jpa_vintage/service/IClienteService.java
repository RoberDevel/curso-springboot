package com.example.data_jpa_vintage.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.data_jpa_vintage.model.Cliente;

public interface IClienteService {
    public void guardar(Cliente cliente);

    public Page<Cliente> findAll(Pageable page);

    public Cliente findOne(Long id);

    public void delete(Long id);
}
