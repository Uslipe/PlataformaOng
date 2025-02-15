package com.apoiaacao.apoiaacao_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apoiaacao.apoiaacao_api.model.DoacaoDeItens;
import com.apoiaacao.apoiaacao_api.model.Usuario;

@Repository
public interface Repositorio_DoacaoDeItens extends JpaRepository<DoacaoDeItens, Integer>{

  List<DoacaoDeItens> findByIdUsuario(Usuario idUsuario);
  
}
