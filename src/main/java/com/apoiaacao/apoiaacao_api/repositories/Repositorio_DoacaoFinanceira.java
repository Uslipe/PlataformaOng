package com.apoiaacao.apoiaacao_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.apoiaacao.apoiaacao_api.model.CampanhaFinanceira;
import com.apoiaacao.apoiaacao_api.model.DoacaoFinanceira;
import com.apoiaacao.apoiaacao_api.model.Usuario;

import jakarta.transaction.Transactional;

@Repository
public interface Repositorio_DoacaoFinanceira extends JpaRepository<DoacaoFinanceira, Integer>{

    List<DoacaoFinanceira> findByIdUsuario(Usuario idUsuario);

    //Método para deletar doações de uma determinada campanha
    @Transactional
    @Modifying
    @Query("DELETE FROM DoacaoFinanceira d WHERE d.idCampanhaFinanceira = :campanhaFinanceira")
    void deleteByCampanhaFinanceira(@Param("campanhaFinanceira") CampanhaFinanceira campanhaFinanceira);
 

}
