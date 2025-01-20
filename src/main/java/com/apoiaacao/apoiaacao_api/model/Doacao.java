package com.apoiaacao.apoiaacao_api.model;

public abstract class Doacao {
    
    private Doador doador;
    private Campanha campanha;

    public Doador getDoador() {
        return doador;
    }
    public void setDoador(Doador doador) {
        this.doador = doador;
    }
    public Campanha getCampanha() {
        return campanha;
    }
    public void setCampanha(Campanha campanha) {
        this.campanha = campanha;
    }

}
