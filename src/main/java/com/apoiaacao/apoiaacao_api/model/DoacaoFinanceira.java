package com.apoiaacao.apoiaacao_api.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "doacao_financeira")
public class DoacaoFinanceira{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doacao_financeira")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_doador")
    private Usuario doador;

    @ManyToOne
    @JoinColumn(name = "id_campanha_financeira")
    private CampanhaFinanceira campanhaFinanceira;

    @Column(name = "valor")
    private double valor;

    @Column(name = "data_doacao")
    private LocalDate dataDoacao;
    //private String formaDePagamento; //Novamente, passível de mudar para ENUM

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    /* 
    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }
    */

    public Usuario getDoador() {
        return doador;
    }

    public void setDoador(Doador doador) {
        this.doador = doador;
    }

    public CampanhaFinanceira getCampanha() {
        return campanhaFinanceira;
    }

    public void setCampanha(CampanhaFinanceira campanha) {
        this.campanhaFinanceira = campanha;
    }

    public LocalDate getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(LocalDate dataDoacao) {
        this.dataDoacao = dataDoacao;
    }

}
