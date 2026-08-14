package com.leonardo.byfood.byfood.dto;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardapioResponse {
    private Long id;
    private String nome;
    private String descricao;
    private Long estabelecimentoId;
}
