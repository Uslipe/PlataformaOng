package com.apoiaacao.apoiaacao_api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import com.apoiaacao.apoiaacao_api.dto.LoginResponse;
import com.apoiaacao.apoiaacao_api.model.ONG;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_ONG;

@Service
public class ONGService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private org.springframework.security.core.userdetails.UserDetailsService userDetailsService; //Para obter o UserDetails

    @Autowired
    private Repositorio_ONG repositorio_ONG;

    public LoginResponse verificarOng(ONG ong){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(ong.getCnpj(), ong.getSenha()));

        if(authentication.isAuthenticated()){
            UserDetails userDetails = userDetailsService.loadUserByUsername(ong.getCnpj());
            String token = jwtService.gerarToken(userDetails);
            int idOng = getIdOngByCnpj(ong.getCnpj());
            // Retorna o token e o id da ONG
            return new LoginResponse(token, idOng);
        }

        return null;
    }

    public int getIdOngByCnpj(String cnpj) {
        ONG ong = repositorio_ONG.findByCnpj(cnpj)
                .orElseThrow(() -> new RuntimeException("ONG não encontrada"));
        return ong.getId();
    }

    public boolean isOngValidada(int id) {
        List<ONG> ongsValidadas = repositorio_ONG.findByValidada(true);
        return ongsValidadas.stream().anyMatch(ong -> ong.getId() == id);
    }
}
