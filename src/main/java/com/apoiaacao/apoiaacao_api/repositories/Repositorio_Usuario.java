package com.apoiaacao.apoiaacao_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.apoiaacao.apoiaacao_api.model.Usuario;

public interface Repositorio_Usuario extends JpaRepository<Usuario, Integer>{

 @Query("SELECT u FROM Usuario u WHERE u.idTipoDeUsuario = :idTipoDeUsuario")
    List<Usuario> findByIdTipoDeUsuario(@Param("idTipoDeUsuario") int idTipoDeUsuario);

 
}
