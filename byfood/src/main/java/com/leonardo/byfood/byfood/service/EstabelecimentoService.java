package com.leonardo.byfood.byfood.service;

import com.leonardo.byfood.byfood.dto.EstabelecimentoRequest;
import com.leonardo.byfood.byfood.dto.EstabelecimentoResponse;
import com.leonardo.byfood.byfood.mapper.EstabelecimentoMapper;
import com.leonardo.byfood.byfood.model.Estabelecimento;
import com.leonardo.byfood.byfood.repository.EstabelecimentoRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstabelecimentoService {

    private final EstabelecimentoRepository repository;

    public EstabelecimentoService(EstabelecimentoRepository repository){
        this.repository = repository;
    }

    public EstabelecimentoResponse salvar(EstabelecimentoRequest request) {
        Estabelecimento e = EstabelecimentoMapper.toEntity(request);
        Estabelecimento salvo = repository.save(e);
        return EstabelecimentoMapper.toResponse(salvo);
    }

    public List<EstabelecimentoResponse> listarTodos() {
        return repository.findAll()
                .stream()
                .map(EstabelecimentoMapper::toResponse)
                .toList();
    }

    public Optional<EstabelecimentoResponse> buscarPorId(Long id) {
        return repository.findById(id)
                .map(EstabelecimentoMapper::toResponse);
    }

    public void deletar(Long id) {
        repository.deleteById(id);
    }
}
