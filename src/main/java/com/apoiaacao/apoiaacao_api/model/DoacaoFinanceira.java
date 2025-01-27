package com.apoiaacao.apoiaacao_api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
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
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_campanha_financeira")
    private CampanhaFinanceira idCampanhaFinanceira;

    @Column(name = "valor")
    private double valor;

    @Column(name = "data_doacao")
    private LocalDate dataDoacao;
    
    @Column(name = "forma_pagamento")
    @Enumerated(EnumType.STRING)
    private MetodoPagamento formaPagamento;

    public DoacaoFinanceira() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Usuario usuario) {
        this.idUsuario = usuario;
    }

    public CampanhaFinanceira getCampanha() {
        return idCampanhaFinanceira;
    }

    public void setCampanha(CampanhaFinanceira campanha) {
        this.idCampanhaFinanceira = campanha;
    }

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public LocalDate getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(LocalDate dataDoacao) {
        this.dataDoacao = dataDoacao;
    }

    public MetodoPagamento getFormaPagamento() {
        return formaPagamento;
    }

    public void setFormaPagamento(MetodoPagamento formaPagamento) {
        this.formaPagamento = formaPagamento;
    }

}
