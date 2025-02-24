package com.apoiaacao.apoiaacao_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.apoiaacao.apoiaacao_api.model.ONG;

@Service
public class ONGService {

    @Autowired
    private JWTService jwtService;

    @Autowired
    private AuthenticationManager authenticationManager;

    public String verificarOng(ONG ong){
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(ong.getCnpj(), ong.getSenha()));

        if(authentication.isAuthenticated()){
            return jwtService.gerarToken(ong.getCnpj());
        }

        return "Falha!";
    }
}
