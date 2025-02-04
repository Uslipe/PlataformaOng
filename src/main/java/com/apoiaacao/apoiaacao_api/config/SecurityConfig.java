package com.apoiaacao.apoiaacao_api.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.apoiaacao.apoiaacao_api.service.MyUserDetailsService;
import com.apoiaacao.apoiaacao_api.util.BCryptEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private JWTFilter jwtFilter;
    
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        return http
                    .csrf(customizer -> customizer.disable()) //Desativamos o csrf pois isso é uma api, e os clientes enviam tokens de autenticação (JWT)
                    .authorizeHttpRequests(request -> request
                                                            .requestMatchers("/salvarUsuario", "/login").permitAll().anyRequest().authenticated())//Todas as requisições de HTTP precisam ser autenticadas, no nosso caso, com httpBasic
                    .httpBasic(Customizer.withDefaults()) //Aplica as configurações mais básicas de autenticação (no nosso caso, presente no application.properties)
                    .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)) //Define as sessões como stateless, comuns em API pelo trabalho de token
                    .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class) //Criação de un novo filtro que antecede a validação de login e senha (filtro de jwt token)
                    .build();
    }

    //UserDetailsService serve para configurar o usuário e senha responsáveis por entrar (não usar mais os locais no application.properties)
    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userDetailsService);
        return provider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception{
        return config.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new PasswordEncoder() {
            @Override
            public String encode(CharSequence rawPassword) {
                return BCryptEncoder.encoder(rawPassword.toString());
            }

            @Override
            public boolean matches(CharSequence rawPassword, String encodedPassword) {
                return new BCryptPasswordEncoder().matches(rawPassword, encodedPassword);
            }
        };
    }
}
