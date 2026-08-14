package com.leonardo.byfood.byfood.service;

import com.leonardo.byfood.byfood.dto.CardapioRequest;
import com.leonardo.byfood.byfood.dto.CardapioResponse;
import com.leonardo.byfood.byfood.mapper.CardapioMapper;
import com.leonardo.byfood.byfood.model.Cardapio;
import com.leonardo.byfood.byfood.model.Estabelecimento;
import com.leonardo.byfood.byfood.repository.CardapioRepository;
import com.leonardo.byfood.byfood.repository.EstabelecimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CardapioService {
    private final CardapioRepository repository;
    private final EstabelecimentoRepository estabelecimentoRepository;

    public CardapioService(CardapioRepository repository, EstabelecimentoRepository estabelecimentoRepository) {
        this.repository = repository;
        this.estabelecimentoRepository = estabelecimentoRepository;
    }

    public CardapioResponse salvar(CardapioRequest request) {
        Estabelecimento estabelecimento = estabelecimentoRepository.findById(request.getEstabelecimentoId())
                .orElseThrow(() -> new RuntimeException("Estabelecimento não encontrado"));

        Cardapio cardapio = CardapioMapper.toEntity(request, estabelecimento);
        return CardapioMapper.toResponse(repository.save(cardapio));
    }

    public List<CardapioResponse> listarPorEstabelecimento(Long estabelecimentoId) {
        return repository.findByEstabelecimentoId(estabelecimentoId)
                .stream()
                .map(CardapioMapper::toResponse)
                .toList();
    }
}

