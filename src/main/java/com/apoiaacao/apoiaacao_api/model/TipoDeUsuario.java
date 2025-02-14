package com.apoiaacao.apoiaacao_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tipo_de_usuario")
public class TipoDeUsuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tipo_de_usuario")
    private int idTipoDeUsuario;

    @Column(name = "role_tipo_de_usuario")
    private String roleTipoDeUsuario;

    public int getIdTipoDeUsuario() {
        return idTipoDeUsuario;
    }

    public void setIdTipoDeUsuario(int idTipoDeUsuario) {
        this.idTipoDeUsuario = idTipoDeUsuario;
    }

    public String getRoleTipoDeUsuario() {
        return roleTipoDeUsuario;
    }

    public void setRoleTipoDeUsuario(String roleTipoDeUsuario) {
        this.roleTipoDeUsuario = roleTipoDeUsuario;
    }
}
