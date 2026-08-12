package com.leonardo.byfood.byfood.model;

import jakarta.persistence.*;

@Entity
@Table(name = "estabelecimento")
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String descrricao;
    private String endereco;
    private String telefone;
}
