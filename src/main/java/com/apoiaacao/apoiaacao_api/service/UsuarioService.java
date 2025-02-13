package com.apoiaacao.apoiaacao_api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.apoiaacao.apoiaacao_api.model.DoacaoDeItens;
import com.apoiaacao.apoiaacao_api.model.DoacaoFinanceira;
import com.apoiaacao.apoiaacao_api.model.DoacaoWrapper;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_Usuario;

@Service
public class UsuarioService {
    @Autowired
    private Repositorio_Usuario repositorioUsuario;
    
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

}
