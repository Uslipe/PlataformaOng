package com.apoiaacao.apoiaacao_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apoiaacao.apoiaacao_api.model.CartaoDeCredito;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_Usuario;
import com.apoiaacao.apoiaacao_api.service.CartaoDeCreditoService;

@CrossOrigin(origins = {"http://localhost:5173/", "http://localhost:3000"})
@RestController
public class Controlador_CartaoDeCredito {
    
    @Autowired
    private CartaoDeCreditoService cartaoDeCreditoService;

    @Autowired
    private Repositorio_Usuario repositorioUsuario;

    public Controlador_CartaoDeCredito(CartaoDeCreditoService cartaoDeCreditoService){
        this.cartaoDeCreditoService = cartaoDeCreditoService;
    }

    @PreAuthorize("hasRole('DOADOR')")
    @PostMapping("/cadastrarCartaoDeCredito")
    public ResponseEntity<CartaoDeCredito> cadastrarCartaoDeCredito(@RequestBody CartaoDeCredito cartaoDeCredito){
        // Obtém o nome do usuário autenticado
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String emailUsuario = authentication.getName(); // Assume que o email é o identificador do usuário

        // Busca o usuário no banco de dados
        Usuario usuario = repositorioUsuario.findByEmail(emailUsuario);

        // Associa o cartão ao usuário
        CartaoDeCredito cartao = cartaoDeCreditoService.criarCartao(cartaoDeCredito, usuario);

        return ResponseEntity.ok(cartao);
    }
}
