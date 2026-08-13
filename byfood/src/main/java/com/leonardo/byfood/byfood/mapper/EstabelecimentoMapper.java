package com.leonardo.byfood.byfood.mapper;

import com.leonardo.byfood.byfood.dto.EstabelecimentoRequest;
import com.leonardo.byfood.byfood.dto.EstabelecimentoResponse;
import com.leonardo.byfood.byfood.model.Estabelecimento;

public class EstabelecimentoMapper {
    public static Estabelecimento toEntity(EstabelecimentoRequest request) {
        Estabelecimento e = new Estabelecimento();
        e.setNome(request.getNome());
        e.setDescrricao(request.getDescricao());
        e.setEndereco(request.getEndereco());
        e.setTelefone(request.getTelefone());
        return e;
    }

    public static EstabelecimentoResponse toResponse(Estabelecimento e) {
        EstabelecimentoResponse response = new EstabelecimentoResponse();
        response.setId(e.getId());
        response.setNome(e.getNome());
        response.setDescricao(e.getDescrricao());
        response.setEndereco(e.getEndereco());
        response.setTelefone(e.getTelefone());
        return response;
    }
}


