package com.apoiaacao.apoiaacao_api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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

    @PostMapping("/registro")
    public void salvarUsuario(@RequestBody Usuario usuario) {
        //Encriptar a senha do usuário
        String hashSenha = BCryptEncoder.encoder(usuario.getSenha());
        usuario.setSenha(hashSenha);

        repositorio_Usuario.save(usuario);
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario){
        return usuarioService.verificarUsuario(usuario);
    }

    @GetMapping("/usuarios")
    public List<Usuario> listarTodosUsuarios() {
        return repositorio_Usuario.findAll();
    }

    @GetMapping("/listarDoadores")
    public Iterable<Usuario> listarDoadores() {
        return repositorio_Usuario.findByIdTipoDeUsuario(1);
    }

    @PutMapping("/editarPerfil/{email}")
    public ResponseEntity<Usuario> editarPerfil(@PathVariable String email, @RequestBody Usuario usuarioAtualizado) {
        Usuario usuario = repositorio_Usuario.findByEmail(email);
        if (usuario != null) {
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            usuario.setSenha(BCryptEncoder.encoder(usuarioAtualizado.getSenha())); // Encriptar a nova senha
            // Atualizar outros atributos conforme necessário
    
            repositorio_Usuario.save(usuario); // Atualiza o usuário existente
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
