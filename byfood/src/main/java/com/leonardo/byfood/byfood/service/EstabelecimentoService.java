package com.leonardo.byfood.byfood.service;

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

    public Estabelecimento salvar(Estabelecimento estabelecimento){
        return repository.save(estabelecimento);
    }

    public List<Estabelecimento> listarTodos(){
        return repository.findAll();
    }

    public Optional<Estabelecimento> buscarPorId(long id){
        return repository.findById(id);
    }

    public void deletar(long id){
        repository.deleteById(id);
    }
}
