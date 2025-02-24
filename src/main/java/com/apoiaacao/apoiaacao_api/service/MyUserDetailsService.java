package com.apoiaacao.apoiaacao_api.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apoiaacao.apoiaacao_api.model.ONG;
import com.apoiaacao.apoiaacao_api.model.ONGPrincipal;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.model.UsuarioPrincipal;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_ONG;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_Usuario;

@Service
public class MyUserDetailsService implements UserDetailsService{

    @Autowired
    private Repositorio_Usuario repositorioUsuario;

    @Autowired
    private Repositorio_ONG repositorioOng;

    @Override
    public UserDetails loadUserByUsername(String parametro) throws UsernameNotFoundException {
        if(isEmail(parametro)){
            Usuario usuarioEncontrado = repositorioUsuario.findByEmail(parametro);
            if(usuarioEncontrado != null){
            return new UsuarioPrincipal(usuarioEncontrado);
            }
        }
        else{
            Optional<ONG> ongEncontrada = repositorioOng.findByCnpj(parametro);
            if(ongEncontrada.isPresent()){
            return new ONGPrincipal(ongEncontrada.get());
            }
        }
        throw new UsernameNotFoundException("Usuário ou ONG não encontrado com o identificador: " + parametro);
    }
    
    private boolean isEmail(String parametro){
        return parametro.contains("@");
      }
}
