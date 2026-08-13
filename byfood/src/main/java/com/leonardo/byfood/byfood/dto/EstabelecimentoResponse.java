package com.leonardo.byfood.byfood.dto;

import lombok.*;

@Builder
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EstabelecimentoResponse {
    private Long id;
    private String nome;
    private String descricao;
    private String endereco;
    private String telefone;
}
