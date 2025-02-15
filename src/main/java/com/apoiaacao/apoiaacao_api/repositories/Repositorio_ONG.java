package com.apoiaacao.apoiaacao_api.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apoiaacao.apoiaacao_api.model.ONG;

@Repository
public interface Repositorio_ONG extends JpaRepository<ONG, Integer>{
    List<ONG> findByValidada(boolean validada);
    Optional<ONG> findByCnpj(String cnpj);
}
