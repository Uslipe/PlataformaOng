package com.apoiaacao.apoiaacao_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.apoiaacao.apoiaacao_api.model.CartaoDeCredito;
import com.apoiaacao.apoiaacao_api.model.DoacaoDeItens;
import com.apoiaacao.apoiaacao_api.model.DoacaoFinanceira;
import com.apoiaacao.apoiaacao_api.model.DoacaoWrapper;
import com.apoiaacao.apoiaacao_api.model.TipoDeUsuario;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_CartaoDeCredito;
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
    private Repositorio_CartaoDeCredito repositorio_CartaoDeCredito;
    
    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private org.springframework.security.core.userdetails.UserDetailsService userDetailsService; //Para obter o UserDetails

    public String verificarUsuario(Usuario usuario) {
        Authentication authentication = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(usuario.getEmail(), usuario.getSenha())
        );
    
        if (authentication.isAuthenticated()) {
            UserDetails userDetails = userDetailsService.loadUserByUsername(usuario.getEmail());
            
            // Gera o token com o UserDetails
            return jwtService.gerarToken(userDetails);
        }
    
        return "Falha";
    }
    

    public Usuario buscarUsuarioPorEmail(String email){
        return repositorioUsuario.findByEmail(email);
    }

    public List<DoacaoWrapper> juntarDoacoes(List<DoacaoFinanceira> doacoesFinanceiras, List<DoacaoDeItens> doacoesDeItens) {
        List<DoacaoWrapper> todasDoacoes = new ArrayList<>();

        // Adiciona todas as doações financeiras na lista de todas as doações
        if (doacoesFinanceiras != null) {
            for (DoacaoFinanceira doacaoFinanceira : doacoesFinanceiras) {
                todasDoacoes.add(new DoacaoWrapper(doacaoFinanceira));
            }
        }
        // Adiciona todas as doações de itens na lista de todas as doações
        if (doacoesDeItens != null) {
            for (DoacaoDeItens doacaoDeItens : doacoesDeItens) {
                todasDoacoes.add(new DoacaoWrapper(doacaoDeItens));
            }
        }

        return todasDoacoes;
    }

    public Usuario criarUsuario(int idTipoDeUsuario, int idCartaoDeCredito ,Usuario usuario) {
        System.out.println("ID Tipo de Usuario: " + idTipoDeUsuario);
        TipoDeUsuario tipoDeUsuario = repositorio_TipoDeUsuario.findById(idTipoDeUsuario)
            .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado")); // Tratamento de erro

        CartaoDeCredito cartaoDeCredito = repositorio_CartaoDeCredito.findById(idCartaoDeCredito)
            .orElseThrow(() -> new RuntimeException("Tipo de usuário não encontrado")); // Tratamento de erro;
    
        usuario.setTipoDeUsuario(tipoDeUsuario);
        usuario.setIdCartaoDeCredito(cartaoDeCredito);

        String hashSenha = BCryptEncoder.encoder(usuario.getSenha());
        usuario.setSenha(hashSenha);
        
        return repositorioUsuario.save(usuario);
    }
    
}
