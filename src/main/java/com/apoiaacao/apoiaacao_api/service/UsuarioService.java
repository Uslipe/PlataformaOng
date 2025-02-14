package com.apoiaacao.apoiaacao_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.apoiaacao.apoiaacao_api.model.TipoDeUsuario;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_TipoDeUsuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_Usuario;
import com.apoiaacao.apoiaacao_api.util.BCryptEncoder;

@Service
public class UsuarioService {
    @Autowired
    private Repositorio_Usuario repositorioUsuario;

    @Autowired
    private Repositorio_TipoDeUsuario repositorio_TipoDeUsuario;
    
    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

     public String verificarUsuario(Usuario usuario){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha()));
        
        if(authentication.isAuthenticated()){
            return jwtService.gerarToken(usuario.getEmail());
        }

        return "Falha";
    }

    public Usuario criarUsuario(int idTipoDeUsuario, Usuario usuario) {
        System.out.println("ID Tipo de Usuario: " + idTipoDeUsuario);
        TipoDeUsuario tipoDeUsuario = repositorio_TipoDeUsuario.findById(idTipoDeUsuario)
            .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado")); // Tratamento de erro
    
        usuario.setTipoDeUsuario(tipoDeUsuario);

        String hashSenha = BCryptEncoder.encoder(usuario.getSenha());
        usuario.setSenha(hashSenha);
        
        return repositorioUsuario.save(usuario);
    }
    
}
