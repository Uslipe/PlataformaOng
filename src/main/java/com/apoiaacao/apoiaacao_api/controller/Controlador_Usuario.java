package com.apoiaacao.apoiaacao_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apoiaacao.apoiaacao_api.model.TipoDeUsuario;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_Usuario;
import com.apoiaacao.apoiaacao_api.service.UsuarioService;
import com.apoiaacao.apoiaacao_api.util.BCryptEncoder;

@RestController
public class Controlador_Usuario {
    
    @Autowired
    private Repositorio_Usuario repositorio_Usuario;

    @Autowired UsuarioService usuarioService;

    public Controlador_Usuario(Repositorio_Usuario repositorio_Usuario) {
        this.repositorio_Usuario = repositorio_Usuario;
    }

    @PostMapping("/salvarUsuario")
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) {
        // Adicionando log para verificar o ID do tipo de usuário
        System.out.println("TipoDeUsuario ID: " + usuario.getTipoDeUsuario().getIdTipoDeUsuario());

        int idTipoUsuario = usuario.getTipoDeUsuario().getIdTipoDeUsuario();
        System.out.println(idTipoUsuario);

        Usuario user = usuarioService.criarUsuario(idTipoUsuario, usuario);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario){
        return usuarioService.verificarUsuario(usuario);
    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping("/usuarios")
    public List<Usuario> listarTodosUsuarios() {
        return repositorio_Usuario.findAll();
    }

    /* 
    @GetMapping("/listarDoadores")
    public Iterable<Usuario> listarDoadores() {
        return repositorio_Usuario.findByIdTipoDeUsuario(1);
    }
        */

}
