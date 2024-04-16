package com.example.springbootjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springbootjparelationships.entity.Client;

public interface ClientRepository extends JpaRepository<Client, Long> {

}
