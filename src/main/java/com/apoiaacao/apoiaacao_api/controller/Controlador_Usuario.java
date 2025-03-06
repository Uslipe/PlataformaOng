package com.apoiaacao.apoiaacao_api.controller;

import java.util.List;
import java.util.Map;
import java.util.Optional;

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
import com.apoiaacao.apoiaacao_api.service.EmailService;
import com.apoiaacao.apoiaacao_api.service.UsuarioService;
import com.apoiaacao.apoiaacao_api.util.BCryptEncoder;
import com.apoiaacao.apoiaacao_api.dto.LoginResponse;

@CrossOrigin(origins = "http://localhost:5173/")
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

    @Autowired
    private EmailService emailService;

    @Autowired
    private final Repositorio_Usuario repositorioUsuario;

    // ✅ Usando injeção via construtor
    public Controlador_Usuario(UsuarioService usuarioService, Repositorio_Usuario repositorioUsuario) {
        this.usuarioService = usuarioService;
        this.repositorioUsuario = repositorioUsuario;
    }

    @PostMapping("/salvarUsuario")
    public ResponseEntity<Usuario> salvarUsuario(@RequestBody Usuario usuario) {
        // Verificar se algum campo obrigatório está nulo
        if (usuario.getNome() == null || usuario.getEmail() == null || usuario.getSenha() == null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        
        // Verificar se o email já está em uso
        Usuario usuarioExistente = repositorio_Usuario.findByEmail(usuario.getEmail());
        if (usuarioExistente != null) {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        // Adicionando log para verificar o ID do tipo de usuário
        System.out.println("TipoDeUsuario ID: " + usuario.getTipoDeUsuario().getIdTipoDeUsuario());

        int idTipoUsuario = usuario.getTipoDeUsuario().getIdTipoDeUsuario();
        System.out.println(idTipoUsuario);

        Usuario user = usuarioService.criarUsuario(idTipoUsuario, usuario);
        try {
            emailService.sendEmail(user.getEmail(), "ApoiaAção - Confirmação de Cadastro", "Seu cadastro foi realizado com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }


    @PostMapping("/login")
public ResponseEntity<LoginResponse> login(@RequestBody Map<String, String> loginData) {
    String email = loginData.get("email");
    String senha = loginData.get("senha");

    if (email == null || senha == null) {
        return ResponseEntity.badRequest().body(new LoginResponse(null, 0));
    }

    String token = usuarioService.verificarUsuario(email, senha);
    
    // 🔍 Evita NullPointerException
    Usuario usuario = repositorioUsuario.findByEmail(email);
    if (usuario == null || "Falha".equals(token)) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(new LoginResponse(null, 0));
    }

    return ResponseEntity.ok(new LoginResponse(token, usuario.getId()));
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
    
    @GetMapping("/buscarUsuario/{id}")
    public ResponseEntity<Usuario> buscarUsuario(@PathVariable int id) {
        Optional<Usuario> optionalUsuario = repositorio_Usuario.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PutMapping("/editarPerfil/{id}")
    public ResponseEntity<Usuario> editarPerfil(@PathVariable int id, @RequestBody Usuario usuarioAtualizado) {
        Optional<Usuario> optionalUsuario = repositorio_Usuario.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            usuario.setNome(usuarioAtualizado.getNome());
            usuario.setEmail(usuarioAtualizado.getEmail());
            if (usuarioAtualizado.getSenha() != null) {
                usuario.setSenha(BCryptEncoder.encoder(usuarioAtualizado.getSenha())); // Encriptar a nova senha
            }
            repositorio_Usuario.save(usuario); // Atualiza o usuário existente
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    
    @DeleteMapping("/deletarUsuario/{id}")
    public ResponseEntity<Usuario> deletarUsuario(@PathVariable int id) {
        Optional<Usuario> optionalUsuario = repositorio_Usuario.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            repositorio_Usuario.delete(usuario);
            try {
                emailService.sendEmail(usuario.getEmail(), "ApoiaAção - Conta Excluída", "Sua conta dentro da plataforma ApoiaAção foi excluída.");
            } catch (Exception e) {
                e.printStackTrace();
            }
            return ResponseEntity.ok(usuario);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }


    @GetMapping("/verHistoricoDoacoes/{id}")
    public ResponseEntity<List<DoacaoWrapper>> verHistoricoDoacoes(@PathVariable int id) {
        Optional<Usuario> optionalUsuario = repositorio_Usuario.findById(id);
        if (optionalUsuario.isPresent()) {
            Usuario usuario = optionalUsuario.get();
            List<DoacaoFinanceira> doacoesFinanceiras = repositorio_DoacaoFinanceira.findByIdUsuario(usuario);
            List<DoacaoDeItens> doacoesDeItens = repositorio_DoacaoDeItens.findByIdUsuario(usuario);
            List<DoacaoWrapper> historicoDoacoes = usuarioService.juntarDoacoes(doacoesFinanceiras, doacoesDeItens);
            return ResponseEntity.ok(historicoDoacoes);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
