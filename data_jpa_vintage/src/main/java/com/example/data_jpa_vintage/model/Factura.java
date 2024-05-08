package com.example.data_jpa_vintage.model;

import java.io.Serial;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.xml.bind.annotation.XmlTransient;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Factura implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotEmpty
    private String descripcion;

    private String observacion;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date createAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Cliente cliente;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "factura_id")
    private List<ItemFactura> items = new ArrayList<>();

    @Serial
    private static final long serialVersionUID = 189L;

    @XmlTransient
    public Cliente getCliente() {
        return cliente;
    }

    @PrePersist
    public void prePersist() {
        createAt = new Date();
    }

    public void addItemFactura(ItemFactura itemFactura) {
        items.add(itemFactura);
    }

    public Double getTotal() {
        Double total = 0.0;
        for (ItemFactura itemFactura : items) {
            total += itemFactura.calcularImporte();
        }
        return total;
    }

    @Override
    public String toString() {
        return "Factura [id=" + id + ", descripcion=" + descripcion + ", observacion=" + observacion + ", createAt="
                + createAt + ", items=" + items + "]";
    }

}
