package com.apoiaacao.apoiaacao_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_Usuario;

@Service
public class UsuarioService {
    @Autowired
    private Repositorio_Usuario repositorioUsuario;

    @Autowired
    AuthenticationManager authenticationManager;

     public String verificarUsuario(Usuario usuario){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha()));
        
        if(authentication.isAuthenticated()){
            return "Sucesso!";
        }

        return "Falha";
    }
}
