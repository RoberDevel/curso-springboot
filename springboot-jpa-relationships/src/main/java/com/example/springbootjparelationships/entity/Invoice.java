package com.example.springbootjparelationships.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(exclude = { "client" })
public class Invoice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String description;
    private Long total;

    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    public Invoice(String description, Long total) {
        this.description = description;
        this.total = total;
    }

    @Override
    public String toString() {
        return "Invoice [id=" + id + ", description=" + description + ", total=" + total + "]";
    }

}
