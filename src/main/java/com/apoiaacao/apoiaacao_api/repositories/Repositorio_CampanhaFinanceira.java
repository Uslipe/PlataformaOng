package com.apoiaacao.apoiaacao_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apoiaacao.apoiaacao_api.model.CampanhaFinanceira;

public interface Repositorio_CampanhaFinanceira extends JpaRepository<CampanhaFinanceira, Integer>{

  List<CampanhaFinanceira> findByIdOng(int idOng);

}
