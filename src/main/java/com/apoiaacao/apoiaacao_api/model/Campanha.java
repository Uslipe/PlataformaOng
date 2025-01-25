package com.apoiaacao.apoiaacao_api.model;

import java.time.LocalDate;

public abstract class Campanha {

    //O atributo "id" destacado no diagrama de classe será gerenciado pelo banco de dados de forma autoincremental
    private ONG ongFundadora;
    //Novamente, listas serão administradas pelo banco de dados
    private LocalDate dataLimite;
    private boolean encerrada;

    public Campanha() {
    }

    public ONG getOngFundadora() {
        return ongFundadora;
    }

    public void setOngFundadora(ONG ongFundadora) {
        this.ongFundadora = ongFundadora;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(LocalDate dataLimite) {
        this.dataLimite = dataLimite;
    }

    public boolean isEncerrada() {
        return encerrada;
    }
    
    public void setEncerrada(boolean encerrada) {
        this.encerrada = encerrada;
    }

}
