package com.apoiaacao.apoiaacao_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apoiaacao.apoiaacao_api.model.CampanhaFinanceira;

public interface Repositorio_CampanhaFinanceira extends JpaRepository<CampanhaFinanceira, Integer>{

  @Query("SELECT c FROM CampanhaFinanceira c WHERE c.idOng = :idOng")
    List<CampanhaFinanceira> findByIdOng(@Param("idOng") int idOng);

}
