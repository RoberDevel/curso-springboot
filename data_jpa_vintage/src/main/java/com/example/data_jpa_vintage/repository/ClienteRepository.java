package com.example.data_jpa_vintage.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.data_jpa_vintage.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

}
