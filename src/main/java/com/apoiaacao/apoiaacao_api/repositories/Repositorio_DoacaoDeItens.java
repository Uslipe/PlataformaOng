package com.apoiaacao.apoiaacao_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apoiaacao.apoiaacao_api.model.DoacaoDeItens;

public interface Repositorio_DoacaoDeItens extends JpaRepository<DoacaoDeItens, Integer>{

  @Query("SELECT d FROM DoacaoDeItens d WHERE d.idUsuario = :idUsuario")
    Iterable<DoacaoDeItens> findByIdUsuario(int idUsuario);

}
