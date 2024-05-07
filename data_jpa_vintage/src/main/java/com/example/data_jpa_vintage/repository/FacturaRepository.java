package com.example.data_jpa_vintage.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.data_jpa_vintage.model.Factura;

public interface FacturaRepository extends JpaRepository<Factura, Long> {

    @Query("select f from Factura f join fetch f.cliente c join fetch f.items l join fetch l.producto where f.id=?1")
    public Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long id);

}
