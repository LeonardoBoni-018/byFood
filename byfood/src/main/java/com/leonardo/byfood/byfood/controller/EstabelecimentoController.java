package com.leonardo.byfood.byfood.controller;

import com.leonardo.byfood.byfood.model.Estabelecimento;
import com.leonardo.byfood.byfood.service.EstabelecimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {

    private final EstabelecimentoService service;

    public EstabelecimentoController(EstabelecimentoService service){
        this.service = service;
    }

    @PostMapping
    public Estabelecimento criar(@RequestBody Estabelecimento estabelecimento){
        return service.salvar(estabelecimento);
    }

    @GetMapping
    public List<Estabelecimento> listar(){
        return service.listarTodos();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Estabelecimento> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }
}
