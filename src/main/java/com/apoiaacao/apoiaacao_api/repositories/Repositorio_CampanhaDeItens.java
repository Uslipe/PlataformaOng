package com.apoiaacao.apoiaacao_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apoiaacao.apoiaacao_api.model.CampanhaDeItens;

public interface Repositorio_CampanhaDeItens extends JpaRepository<CampanhaDeItens, Integer>{

  @Query("SELECT c FROM CampanhaDeItens c WHERE c.idOng = :idOng")
    List<CampanhaDeItens> findByIdOng(@Param("idOng") int idOng);

}
