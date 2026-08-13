package com.leonardo.byfood.byfood.controller;

import com.leonardo.byfood.byfood.dto.EstabelecimentoRequest;
import com.leonardo.byfood.byfood.dto.EstabelecimentoResponse;
import com.leonardo.byfood.byfood.model.Estabelecimento;
import com.leonardo.byfood.byfood.service.EstabelecimentoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/estabelecimentos")
public class EstabelecimentoController {
    private final EstabelecimentoService service;

    public EstabelecimentoController(EstabelecimentoService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<EstabelecimentoResponse> criar(@RequestBody EstabelecimentoRequest request) {
        return ResponseEntity.ok(service.salvar(request));
    }

    @GetMapping
    public ResponseEntity<List<EstabelecimentoResponse>> listar() {
        return ResponseEntity.ok(service.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<EstabelecimentoResponse> buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        service.deletar(id);
        return ResponseEntity.noContent().build();
    }
}
