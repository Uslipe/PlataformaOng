package com.apoiaacao.apoiaacao_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.model.UsuarioPrincipal;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_Usuario;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private Repositorio_Usuario repositorioUsuario;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        System.out.println("Tentando autenticar com o e-mail: " + email); // Log para verificar o e-mail
        Usuario usuario = repositorioUsuario.findByEmail(email);
        
        if(usuario == null) {
            System.out.println("Usuário não encontrado para o e-mail: " + email); // Log caso o usuário não seja encontrado
            throw new UsernameNotFoundException("Usuário não encontrado");
        }
        
        System.out.println("Usuário encontrado: " + usuario.getEmail()); // Log para verificar se o usuário foi encontrado
        return new UsuarioPrincipal(usuario);
    }

    
}
