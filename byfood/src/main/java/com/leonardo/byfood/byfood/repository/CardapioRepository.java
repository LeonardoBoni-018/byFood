package com.leonardo.byfood.byfood.repository;

import com.leonardo.byfood.byfood.model.Cardapio;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CardapioRepository extends JpaRepository<Cardapio, Long> {
    List<Cardapio> findByEstabelecimentoId(Long estabelecimentoId);
}

