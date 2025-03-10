package com.apoiaacao.apoiaacao_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apoiaacao.apoiaacao_api.model.CampanhaDeItens;
import com.apoiaacao.apoiaacao_api.model.DoacaoDeItens;
import com.apoiaacao.apoiaacao_api.model.Usuario;

import jakarta.transaction.Transactional;

@Repository
public interface Repositorio_DoacaoDeItens extends JpaRepository<DoacaoDeItens, Integer>{

  List<DoacaoDeItens> findByIdUsuario(Usuario idUsuario);

  List<DoacaoDeItens> findByCampanhaDeItens(CampanhaDeItens campanhaDeItens);

  //Método para deletar doações de uma determinada campanha
    @Transactional
    @Modifying
    @Query("DELETE FROM DoacaoDeItens d WHERE d.campanhaDeItens = :campanhaDeItens")
    void deleteByCampanhaDeItens(@Param("campanhaDeItens") CampanhaDeItens campanhaDeItens);
  
}
