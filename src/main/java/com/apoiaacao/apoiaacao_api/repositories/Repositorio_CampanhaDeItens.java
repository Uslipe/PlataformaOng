package com.apoiaacao.apoiaacao_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.apoiaacao.apoiaacao_api.model.CampanhaDeItens;

public interface Repositorio_CampanhaDeItens extends JpaRepository<CampanhaDeItens, Integer>{

  List<CampanhaDeItens> findByIdOng(int idOng);

}
