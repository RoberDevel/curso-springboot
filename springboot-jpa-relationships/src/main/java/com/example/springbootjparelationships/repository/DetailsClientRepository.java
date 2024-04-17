package com.example.springbootjparelationships.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.springbootjparelationships.entity.DetailsClient;

public interface DetailsClientRepository extends JpaRepository<DetailsClient, Long> {

}
