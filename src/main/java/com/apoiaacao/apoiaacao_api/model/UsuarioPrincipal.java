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
        // Obtendo a role do usuário a partir da tabela tipo_de_usuario
        String role = usuario.getTipoDeUsuario().getRoleTipoDeUsuario();
        return Collections.singleton(new SimpleGrantedAuthority("ROLE_" + role));
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
