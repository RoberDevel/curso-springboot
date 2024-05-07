package com.example.data_jpa_vintage.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.data_jpa_vintage.model.Cliente;
import com.example.data_jpa_vintage.model.Factura;
import com.example.data_jpa_vintage.model.Producto;
import com.example.data_jpa_vintage.repository.ClienteRepository;
import com.example.data_jpa_vintage.repository.FacturaRepository;
import com.example.data_jpa_vintage.repository.ProductoRepository;

@Service
public class ClienteServiceImpl implements IClienteService {

    @Autowired
    ClienteRepository clienteRepository;

    @Autowired
    ProductoRepository productoRepository;

    @Autowired
    FacturaRepository facturaRepository;

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
    @Transactional(readOnly = true)
    public Cliente findOne(Long id) {
        return clienteRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Cliente fetchByIdWithFacturas(Long id) {
        return clienteRepository.fetchByIdWithFacturas(id);
    }

    @Override
    public void delete(Long id) {
        clienteRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByNombre(String term) {
        return productoRepository.findByNombre(term);
    }

    @Override
    @Transactional
    public void saveFactura(Factura factura) {
        facturaRepository.save(factura);

    }

    @Override
    @Transactional(readOnly = true)
    public Producto findProductoById(Long id) {
        return productoRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional(readOnly = true)
    public Factura findFacturaById(Long id) {
        return facturaRepository.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public void deleteFactura(Long id) {
        facturaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Factura fetchByIdWithClienteWithItemFacturaWithProducto(Long id) {
        return facturaRepository.fetchByIdWithClienteWithItemFacturaWithProducto(id);
    }

}
