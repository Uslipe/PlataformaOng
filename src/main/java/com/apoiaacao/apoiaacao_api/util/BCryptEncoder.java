package com.apoiaacao.apoiaacao_api.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class BCryptEncoder {
    public static String encoder(String senha){
        BCryptPasswordEncoder encoderSenha = new BCryptPasswordEncoder();
        return encoderSenha.encode(senha);
    }
}
