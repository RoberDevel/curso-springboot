package com.example.data_jpa_vintage.view.xml;

import java.util.List;

import com.example.data_jpa_vintage.model.Cliente;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@XmlRootElement(name = "clientes")
public class ClienteList {

    @XmlElement(name = "cliente")
    public List<Cliente> clientes;

}
