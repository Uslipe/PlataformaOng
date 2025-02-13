package com.apoiaacao.apoiaacao_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apoiaacao.apoiaacao_api.model.DoacaoFinanceira;
import com.apoiaacao.apoiaacao_api.model.Usuario;

@Repository
public interface Repositorio_DoacaoFinanceira extends JpaRepository<DoacaoFinanceira, Integer>{

 List<DoacaoFinanceira> findByIdUsuario(Usuario idUsuario);

}
