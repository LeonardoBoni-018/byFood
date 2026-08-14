package com.leonardo.byfood.byfood.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardapioRequest {
    private String nome;
    private String descricao;
    private Long estabelecimentoId;
}
