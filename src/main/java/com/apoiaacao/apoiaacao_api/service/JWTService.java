package com.apoiaacao.apoiaacao_api.service;

import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;

import org.springframework.security.core.GrantedAuthority;
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
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }

    }

    public String gerarToken(UserDetails userDetails){
        Map<String, Object> claims = new HashMap<>();

        // Extrai as roles do usuário e adiciona ao payload do token
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        claims.put("role", roles);

        return Jwts.builder()
                    .claims()
                    .add(claims)
                    .subject(userDetails.getUsername())
                    .issuedAt(new Date(System.currentTimeMillis()))
                    .expiration(new Date(System.currentTimeMillis() + 24 * 60 * 60 * 1000)) // Token válido por 24 horas
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

    @SuppressWarnings("unchecked")
    public List<String> pegarRolesDoToken(String token) {
        return extrairClaim(token, claims -> claims.get("roles", List.class));
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
        return valido;
    }

    private boolean isTokenExpired(String token) {
        return extractExpirationToken(token).before(new Date());
    }

    private Date extractExpirationToken(String token) {
        return extrairClaim(token, Claims::getExpiration);
    }
}
