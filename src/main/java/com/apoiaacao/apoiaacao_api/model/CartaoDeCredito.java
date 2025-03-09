package com.apoiaacao.apoiaacao_api.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "cartao_de_credito")
public class CartaoDeCredito {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cartao_de_credito")
    private int idCartaoDeCredito;

    @Column(name = "nome_titular")
    private String nomeTitular;

    @Column(name = "digitos_cartao")
    private String digitosCartao;

    @Column(name = "data_de_validade")
    private LocalDate dataDeValidade;

    @Column(name = "cvv")
    private String cvv;

    public int getIdCartaoDeCredito() {
        return idCartaoDeCredito;
    }

    public void setIdCartaoDeCredito(int idCartaoDeCredito) {
        this.idCartaoDeCredito = idCartaoDeCredito;
    }

    public String getNomeTitular() {
        return nomeTitular;
    }

    public void setNomeTitular(String nomeTitular) {
        this.nomeTitular = nomeTitular;
    }

    public String getDigitosCartao() {
        return digitosCartao;
    }

    public void setDigitosCartao(String digitosCartao) {
        this.digitosCartao = digitosCartao;
    }

    public LocalDate getDataDeValidade() {
        return dataDeValidade;
    }

    public void setDataDeValidade(LocalDate dataDeValidade) {
        this.dataDeValidade = dataDeValidade;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }
}
