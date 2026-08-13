package com.leonardo.byfood.byfood.model;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "estabelecimento")
@Builder
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Estabelecimento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String nome;
    private String descrricao;
    private String endereco;
    private String telefone;
}
