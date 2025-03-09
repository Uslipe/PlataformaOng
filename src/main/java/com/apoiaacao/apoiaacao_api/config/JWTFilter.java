package com.apoiaacao.apoiaacao_api.config;

import java.io.IOException;

import org.springframework.context.ApplicationContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.filter.OncePerRequestFilter;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;

import com.apoiaacao.apoiaacao_api.service.JWTService;
import com.apoiaacao.apoiaacao_api.service.MyUserDetailsService;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JWTFilter extends OncePerRequestFilter{//Faz com que esse filtro de segurança seja executado apenas uma vez

    @Autowired
    private JWTService jwtService;

    @Autowired
    private ApplicationContext context;

    @SuppressWarnings("null")
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String cabecalhoAuth = request.getHeader("Authorization");
        String token = null;
        String email = null;

        //
        if (cabecalhoAuth != null && cabecalhoAuth.startsWith("Bearer ")) {
            token = cabecalhoAuth.substring(7); //Remove "Bearer " e pega o token
            email = jwtService.pegarSubjectDoToken(token);
        }

        if(email != null && SecurityContextHolder.getContext().getAuthentication() == null){

            UserDetails userDetails = context.getBean(MyUserDetailsService.class).loadUserByUsername(email);

            if(jwtService.validarToken(token, userDetails)){
                UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authToken);
            } else {
                System.out.println("Falha na validação do token"); // Log da falha na validação do token
            }
        }
        filterChain.doFilter(request, response);
    }   
}
