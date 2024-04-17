package com.example.springbootjparelationships.entity;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.OneToMany;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.*;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = { "invoices" })
// @ToString(exclude = { "invoices" })
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String lastname;

    // @JoinColumn(name = "client_id")
    // orphanRemoval = true: si eliminamos una dirección de la lista, también se
    // eliminará de la base de datos. sino se quedará en la base de datos y solo se
    // eliminará de la tabla de relación.
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinTable(name = "tabla_cliente_addresses", joinColumns = @JoinColumn(name = "id_cliente"), inverseJoinColumns = @JoinColumn(name = "id_address"), uniqueConstraints = @UniqueConstraint(columnNames = {
            "id_address" }))
    private Set<Address> addresses = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "client")
    private Set<Invoice> invoices = new HashSet<>();

    public Client(String name, String lastname) {
        this.name = name;
        this.lastname = lastname;
    }

    public Client addInvoice(Invoice invoice) {
        invoices.add(invoice);
        invoice.setClient(this);
        return this;
    }

    public void removeInvoice(Invoice invoice) {
        invoices.remove(invoice);
        invoice.setClient(null);

    }

}
