package com.apoiaacao.apoiaacao_api.dto;

public class LoginResponse {
    private String token;
    private int idUsuario;

    public LoginResponse(String token, int idUsuario) {
        this.token = token;
        this.idUsuario = idUsuario;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }
}