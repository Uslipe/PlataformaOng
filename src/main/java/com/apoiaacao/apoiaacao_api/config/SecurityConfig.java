package com.apoiaacao.apoiaacao_api.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                    .csrf(customizer -> customizer.disable()) //Desativamos o csrf pois isso é uma api, e os clientes enviam tokens de autenticação (JWT)
                    .authorizeHttpRequests(request -> request.anyRequest().authenticated()) //Todas as requisições de HTTP precisam ser autenticadas, no nosso caso, com httpBasic
                    .httpBasic(Customizer.withDefaults()) //Aplica as configurações mais básicas de autenticação (no nosso caso, presente no application.properties)
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Define as sessões como stateless, comuns em API pelo trabalho de token
                    .build();
    }
}
