package com.leonardo.byfood.byfood.dto;

import lombok.Builder;
import lombok.Value;

@Builder
@Value
public class EstabelecimentoRequest {
    private String nome;
    private String descricao;
    private String endereco;
    private String Telefone;
}
