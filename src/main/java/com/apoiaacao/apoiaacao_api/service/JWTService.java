package com.apoiaacao.apoiaacao_api.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;

@Service
public class JWTService {

    private String key = "";

    //Construtor que gerará uma chave
    public JWTService(){
        try {
            KeyGenerator keyGenerator = KeyGenerator.getInstance("HmacSHA256");
            SecretKey secretKey = keyGenerator.generateKey();
            key = Base64.getEncoder().encodeToString(secretKey.getEncoded());
            System.out.println("Chave gerada: " + key);
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    public String gerarToken(String subjetc){
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                    .claims()
                    .add(claims)
                    .subject(subjetc)
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) //Diz que o token é aplicável por 30 min
                    .and()
                    .signWith(getKey())
                    .compact();
    }

    //Gerar a chave que será usada pelo método signWith
    private SecretKey getKey(){
        byte[] keyBytes = Decoders.BASE64.decode(key);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String pegarSubjectDoToken(String token) {
        return extrairClaim(token, Claims::getSubject);
    }

    private <T> T extrairClaim(String token, Function<Claims, T> claimResolver) {
        final Claims claims = extractAllClaims(token);
        return claimResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
       return Jwts.parser()
                    .verifyWith(getKey())
                    .build()
                    .parseSignedClaims(token)
                    .getPayload();
    }
        
    public boolean validarToken(String token, UserDetails userDetails) {
        final String email = pegarSubjectDoToken(token);
        boolean valido = (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
        System.out.println("Token válido: " + valido); // Log da validação do token
        return valido;
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationToken(token).before(new Date());
    }

    private Date extractExpirationToken(String token) {
        return extrairClaim(token, Claims::getExpiration);
    }
}
