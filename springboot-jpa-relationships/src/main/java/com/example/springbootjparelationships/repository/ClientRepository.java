package com.example.springbootjparelationships.repository;

import java.util.Optional;

import javax.swing.text.html.Option;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.springbootjparelationships.entity.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query("select c from Client c left join fetch c.invoices where c.id = ?1")
    Optional<Client> findOneWithInvoices(Long id);

    @Query("select c from Client c left join fetch c.addresses where c.id = ?1")
    Optional<Client> findOneWithAddresses(Long id);

    @Query("select c from Client c left join fetch c.invoices left join fetch c.addresses where c.id = ?1")
    Optional<Client> findOne(Long id);

}
