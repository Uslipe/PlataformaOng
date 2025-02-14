package com.apoiaacao.apoiaacao_api.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.apoiaacao.apoiaacao_api.model.TipoDeUsuario;

@Repository
public interface Repositorio_TipoDeUsuario extends JpaRepository<TipoDeUsuario, Integer>{
}
