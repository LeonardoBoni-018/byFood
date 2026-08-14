package com.leonardo.byfood.byfood.mapper;

import com.leonardo.byfood.byfood.dto.CardapioRequest;
import com.leonardo.byfood.byfood.dto.CardapioResponse;
import com.leonardo.byfood.byfood.model.Cardapio;
import com.leonardo.byfood.byfood.model.Estabelecimento;

public class CardapioMapper {
    public static Cardapio toEntity(CardapioRequest request, Estabelecimento estabelecimento) {
        return Cardapio.builder()
                .nome(request.getNome())
                .descricao(request.getDescricao())
                .estabelecimento(estabelecimento)
                .build();
    }

    public static CardapioResponse toResponse(Cardapio cardapio) {
        return CardapioResponse.builder()
                .id(cardapio.getId())
                .nome(cardapio.getNome())
                .descricao(cardapio.getDescricao())
                .estabelecimentoId(cardapio.getEstabelecimento().getId())
                .build();
    }
}

