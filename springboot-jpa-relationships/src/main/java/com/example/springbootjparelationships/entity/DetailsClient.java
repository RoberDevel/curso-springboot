package com.example.springbootjparelationships.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString(exclude = { "client" })
public class DetailsClient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean vip;
    private Integer points;

    @OneToOne
    @JoinColumn(name = "id_details_client")
    private Client client;

    public DetailsClient(Boolean vip, Integer points) {
        this.vip = vip;
        this.points = points;
    }

}
