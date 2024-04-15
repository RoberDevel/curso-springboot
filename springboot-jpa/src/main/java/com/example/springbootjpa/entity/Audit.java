package com.example.springbootjpa.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Embeddable;
import jakarta.persistence.PostPersist;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Audit {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    public void prePersist() {
        System.out.println("Before saving the object");
        this.createdAt = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        System.out.println("Before updating the object");
        this.updatedAt = LocalDateTime.now();
    }

    @PostPersist
    public void postPersist() {
        System.out.println("After saving the object");
    }
}
