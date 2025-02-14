package com.apoiaacao.apoiaacao_api.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class UsuarioPrincipal implements UserDetails{

    private Usuario usuario;

    public UsuarioPrincipal(Usuario usuario){
        this.usuario = usuario;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = usuario.getTipoDeUsuario().getRoleTipoDeUsuario();
        String roleWithPrefix = "ROLE_" + role;
        System.out.println("Role atribuída ao usuário: " + roleWithPrefix);
        return Collections.singleton(new SimpleGrantedAuthority(roleWithPrefix));
    }


    @Override
    public String getPassword() {
        return usuario.getSenha();
    }

    //Esse método ao invés de retornar o username retornaŕa o email
    @Override
    public String getUsername() {
        return usuario.getEmail();
    }
    
}
