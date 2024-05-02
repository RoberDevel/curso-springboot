package com.example.data_jpa_vintage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.example.data_jpa_vintage.model.Cliente;
import com.example.data_jpa_vintage.repository.ClienteRepository;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Override
    public void guardar(Cliente cliente) {
        // if (cliente.getId() != null && cliente.getId() > 0) {
        // System.out.println();
        // clienteRepository.save(cliente);
        // }
        clienteRepository.save(cliente);
    }

    @Override
    public Page<Cliente> findAll(Pageable page) {
        return clienteRepository.findAll(page);
    }

    @Override
    public Cliente findOne(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }
}
