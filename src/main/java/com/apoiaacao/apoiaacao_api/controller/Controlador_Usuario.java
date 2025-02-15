package com.apoiaacao.apoiaacao_api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apoiaacao.apoiaacao_api.model.DoacaoDeItens;
import com.apoiaacao.apoiaacao_api.model.DoacaoFinanceira;
import com.apoiaacao.apoiaacao_api.model.DoacaoWrapper;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_DoacaoFinanceira;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_DoacaoDeItens;
import com.apoiaacao.apoiaacao_api.service.UsuarioService;
import com.apoiaacao.apoiaacao_api.util.BCryptEncoder;

@CrossOrigin
@RestController
public class Controlador_Usuario {
    
    @Autowired
    private Repositorio_Usuario repositorio_Usuario;

    @Autowired 
    private UsuarioService usuarioService;

    @Autowired
    private Repositorio_DoacaoFinanceira repositorio_DoacaoFinanceira;

    @Autowired
    private Repositorio_DoacaoDeItens repositorio_DoacaoDeItens;

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

    @PreAuthorize("hasRole('ADMIN')")
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

    @DeleteMapping("/deletarUsuario/{email}")
    public ResponseEntity<Usuario> deletarUsuario(@PathVariable String email) {
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
        if (usuario != null) {
            repositorio_Usuario.delete(usuario);
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/verHistoricoDoacoes/{email}")
    public ResponseEntity<List<DoacaoWrapper>> verHistoricoDoacoes(@PathVariable String email) {
        Usuario usuario = usuarioService.buscarUsuarioPorEmail(email);
        if (usuario != null) {
            List<DoacaoFinanceira> doacoesFinanceiras = repositorio_DoacaoFinanceira.findByIdUsuario(usuario);
            List<DoacaoDeItens> doacoesDeItens = repositorio_DoacaoDeItens.findByIdUsuario(usuario);
            List<DoacaoWrapper> historicoDoacoes = usuarioService.juntarDoacoes(doacoesFinanceiras, doacoesDeItens);
            return ResponseEntity.ok(historicoDoacoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
