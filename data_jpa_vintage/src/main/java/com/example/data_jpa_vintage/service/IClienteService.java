package com.example.data_jpa_vintage.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.example.data_jpa_vintage.model.Cliente;
import com.example.data_jpa_vintage.model.Factura;
import com.example.data_jpa_vintage.model.Producto;

public interface IClienteService {
    public void guardar(Cliente cliente);

    public Page<Cliente> findAll(Pageable page);

    public Cliente findOne(Long id);

    public Cliente fetchByIdWithFacturas(Long id);

    public void delete(Long id);

    public List<Producto> findByNombre(String term);

    public void saveFactura(Factura factura);

    public Producto findProductoById(Long id);

    public Factura findFacturaById(Long id);

    public void deleteFactura(Long id);

    public Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long id);
}
