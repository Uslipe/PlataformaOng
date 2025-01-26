package com.apoiaacao.apoiaacao_api.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.apoiaacao.apoiaacao_api.model.Usuario;

public interface Repositorio_Usuario extends JpaRepository<Usuario, Integer>{
  Usuario findByEmail(String email);
  List<Usuario> findByIdTipoDeUsuario(int idTipoDeUsuario);

 
}
