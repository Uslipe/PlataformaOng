package com.apoiaacao.apoiaacao_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.apoiaacao.apoiaacao_api.model.DoacaoFinanceira;

public interface Repositorio_DoacaoFinanceira extends JpaRepository<DoacaoFinanceira, Integer>{

  @Query("SELECT d FROM DoacaoFinanceira d WHERE d.idUsuario = :idUsuario")
    Iterable<DoacaoFinanceira> findByIdUsuario(int idUsuario);

}
