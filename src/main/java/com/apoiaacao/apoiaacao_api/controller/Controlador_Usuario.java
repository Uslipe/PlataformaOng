package com.apoiaacao.apoiaacao_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_Usuario;
import com.apoiaacao.apoiaacao_api.util.BCryptEncoder;

@RestController
public class Controlador_Usuario {
    private Repositorio_Usuario repositorio_Usuario;

    public Controlador_Usuario(Repositorio_Usuario repositorio_Usuario) {
        this.repositorio_Usuario = repositorio_Usuario;
    }

    @PostMapping("/salvarUsuario")
    public void salvarUsuario(@RequestBody Usuario usuario) {
        //Encriptar a senha do usuário
        String hashSenha = BCryptEncoder.encoder(usuario.getSenha());
        usuario.setSenha(hashSenha);

        repositorio_Usuario.save(usuario);
    }

    @GetMapping("/listarTodosUsuarios")
    public Iterable<Usuario> listarTodosUsuarios() {
        return repositorio_Usuario.findAll();
    }

    @GetMapping("/listarDoadores")
    public Iterable<Usuario> listarDoadores() {
        return repositorio_Usuario.findByIdTipoDeUsuario(1);
    }

}
