package com.apoiaacao.apoiaacao_api.model;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "campanha_financeira")
public class CampanhaFinanceira{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campanha_financeira")
    private int idCampanhaFinanceira;

    @ManyToOne
    @JoinColumn(name = "id_ong")
    private ONG idOng;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @JsonDeserialize(using = LocalDateDeserializer.class)
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @Column(name = "data_fim")
    private LocalDate dataFim;

    @Column(name = "meta_valor")
    private double metaValor;

    @Column(name = "valor_arrecadado")
    private double valorArrecadado;

    @Column(name = "encerrada")
    private boolean encerrada;

    @Column(name = "nome_imagem")
    private String nomeDaImagem;

    @Column(name = "tipo_imagem")
    private String tipoDaImagem;

    
    @Column(name = "dados_imagem", columnDefinition = "BYTEA")
    private byte[] dadosDaImagem;

    public CampanhaFinanceira() {
    }

    public CampanhaFinanceira(int id){
        this.idCampanhaFinanceira = id;
    }

    public int getIdCampanhaFinanceira() {
        return idCampanhaFinanceira;
    }

    public void setIdCampanhaFinanceira(int idCampanhaFinanceira) {
        this.idCampanhaFinanceira = idCampanhaFinanceira;
    }

    public ONG getIdOng() {
        return idOng;
    }

    public void setIdOng(ONG idOng) {
        this.idOng = idOng;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFim() {
        return dataFim;
    }

    public void setDataFim(LocalDate dataFim) {
        this.dataFim = dataFim;
    }

    public double getMetaValor() {
        return metaValor;
    }

    public void setMetaValor(double metaValor) {
        this.metaValor = metaValor;
    }

    public double getValorArrecadado() {
        return valorArrecadado;
    }

    public void setValorArrecadado(double valorArrecadado) {
        this.valorArrecadado = valorArrecadado;
    }

    public boolean getEncerrada() {
        return encerrada;
    }

    public void setEncerrada(boolean encerrada) {
        this.encerrada = encerrada;
    }

    public String getNomeDaImagem() {
        return nomeDaImagem;
    }

    public void setNomeDaImagem(String nomeDaImagem) {
        this.nomeDaImagem = nomeDaImagem;
    }

    public String getTipoDaImagem() {
        return tipoDaImagem;
    }

    public void setTipoDaImagem(String tipoDaImagem) {
        this.tipoDaImagem = tipoDaImagem;
    }

    public byte[] getDadosDaImagem() {
        return dadosDaImagem;
    }

    public void setDadosDaImagem(byte[] dadosDaImagem) {
        this.dadosDaImagem = dadosDaImagem;
    }

    @Override
    public String toString() {
        return "CampanhaFinanceira [idCampanhaFinanceira=" + idCampanhaFinanceira + ", idOng=" + idOng + ", nome="
                + nome + ", descricao=" + descricao + ", dataInicio=" + dataInicio + ", dataFim=" + dataFim
                + ", metaValor=" + metaValor + ", valorArrecadado=" + valorArrecadado + "]";
    }

    
}
