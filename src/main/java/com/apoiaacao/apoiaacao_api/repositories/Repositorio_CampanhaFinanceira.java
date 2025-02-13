package com.apoiaacao.apoiaacao_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apoiaacao.apoiaacao_api.model.CampanhaFinanceira;
import com.apoiaacao.apoiaacao_api.model.ONG;

@Repository
public interface Repositorio_CampanhaFinanceira extends JpaRepository<CampanhaFinanceira, Integer>{

  List<CampanhaFinanceira> findByIdOng(ONG idOng);

  List<CampanhaFinanceira> findByEncerrada(boolean encerrada);

}
