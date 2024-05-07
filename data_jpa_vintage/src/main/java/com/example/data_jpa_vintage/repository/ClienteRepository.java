package com.example.data_jpa_vintage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.data_jpa_vintage.model.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Long> {

    @Query("select c from Cliente c left join fetch c.facturas f where c.id=?1")
    public Cliente fetchByIdWithFacturas(Long id);

}
