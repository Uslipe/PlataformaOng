package com.apoiaacao.apoiaacao_api.model;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "campanha_financeira")
public class CampanhaFinanceira extends Campanha {

    private double valorObjetivo;
    private double totalArrecadado;

    public double getValorObjetivo() {
        return valorObjetivo;
    }

    public void setValorObjetivo(double valorObjetivo) {
        this.valorObjetivo = valorObjetivo;
    }

    public double getTotalArrecadado() {
        return totalArrecadado;
    }

    public void setTotalArrecadado(double totalArrecadado) {
        this.totalArrecadado = totalArrecadado;
    }

    public ONG getOngFundadora(){
        return super.getOngFundadora();
    }
    
    public void setOngFundadora(ONG ongFundadora){
        super.setOngFundadora(ongFundadora);
    }

    public LocalDate getDataLimite(){
        return super.getDataLimite();
    }

    public void setDataLimite(LocalDate dataLimite){
        super.setDataLimite(dataLimite);
    }

    public boolean isEncerrada(){
        return super.isEncerrada();
    }

    public void setEncerrada(boolean encerrada){
        super.setEncerrada(encerrada);
    }
}
