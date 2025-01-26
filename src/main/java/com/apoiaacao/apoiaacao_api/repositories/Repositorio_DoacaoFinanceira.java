package com.apoiaacao.apoiaacao_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apoiaacao.apoiaacao_api.model.DoacaoFinanceira;

public interface Repositorio_DoacaoFinanceira extends JpaRepository<DoacaoFinanceira, Integer>{

 Iterable<DoacaoFinanceira> findByIdUsuario(int idUsuario);

}
