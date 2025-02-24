package com.apoiaacao.apoiaacao_api.model;

import java.util.Collection;
import java.util.Collections;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

public class ONGPrincipal implements UserDetails{
    
    private ONG ong;

    public ONGPrincipal(ONG ong){
        this.ong = ong;
    }

    @Override
    public String getPassword() {
        return ong.getSenha();
    }

    //Esse método ao invés de retornar o username retornaŕa o cnpj da ong
    @Override
    public String getUsername() {
        return ong.getCnpj();
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        String role = ong.getTipoDeUsuario().getRoleTipoDeUsuario();
        String roleWithPrefix = "ROLE_" + role;
        System.out.println("Role atribuída a ONG: " + roleWithPrefix);
        return Collections.singleton(new SimpleGrantedAuthority(roleWithPrefix));
    }
}
