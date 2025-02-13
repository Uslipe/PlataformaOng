package com.apoiaacao.apoiaacao_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apoiaacao.apoiaacao_api.model.CampanhaDeItens;
import com.apoiaacao.apoiaacao_api.model.CategoriaItens;
import com.apoiaacao.apoiaacao_api.model.ONG;

@Repository
public interface Repositorio_CampanhaDeItens extends JpaRepository<CampanhaDeItens, Integer>{

  List<CampanhaDeItens> findByIdOng(ONG idOng);

  List<CampanhaDeItens> findByEncerrada(boolean encerrada);

  List<CampanhaDeItens> findByCategoriaItens(CategoriaItens categoriaItens);

}
