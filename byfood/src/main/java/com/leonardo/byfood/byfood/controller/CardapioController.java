package com.leonardo.byfood.byfood.controller;

import com.leonardo.byfood.byfood.dto.CardapioRequest;
import com.leonardo.byfood.byfood.dto.CardapioResponse;
import com.leonardo.byfood.byfood.service.CardapioService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cardapios")
public class CardapioController {
    private final CardapioService service;

    public CardapioController(CardapioService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<CardapioResponse> criar(@RequestBody CardapioRequest request) {
        return ResponseEntity.ok(service.salvar(request));
    }

    @GetMapping("/estabelecimento/{id}")
    public ResponseEntity<List<CardapioResponse>> listarPorEstabelecimento(@PathVariable Long id) {
        return ResponseEntity.ok(service.listarPorEstabelecimento(id));
    }
}

