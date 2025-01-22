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
@Table(name = "campanha_de_itens")
public class CampanhaDeItens{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campanha_de_itens")
    private int idCampanhaDeItens;

    @ManyToOne
    @JoinColumn(name = "id_ong")
    private int idOng;

    @Column(name = "nome")
    private String nome;

    @Column(name = "descricao")
    private String descricao;

    @Column(name = "data_inicio")
    private LocalDate dataInicio;

    @Column(name = "data_fim ")
    private LocalDate dataFim;

    @Column(name = "endereco")
    private String endereco;

    @Column(name = "quantidade_itens")
    private int quantidadeDeItens;

    @Column(name = "quantidade_itens_entregues")
    private int quantidadeDeItensEntregues;


    public int getQuantidadeDeItens() {
        return quantidadeDeItens;
    }

    public void setQuantidadeDeItens(int quantidadeDeItens) {
        this.quantidadeDeItens = quantidadeDeItens;
    }

    public int getIdCampanhaDeItens() {
        return idCampanhaDeItens;
    }

    public void setIdCampanhaDeItens(int idCampanhaDeItens) {
        this.idCampanhaDeItens = idCampanhaDeItens;
    }

    public int getIdOng() {
        return idOng;
    }

    public void setIdOng(int idOng) {
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

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public int getQuantidadeDeItensEntregues() {
        return quantidadeDeItensEntregues;
    }

    public void setQuantidadeDeItensEntregues(int quantidadeDeItensEntregues) {
        this.quantidadeDeItensEntregues = quantidadeDeItensEntregues;
    }
    
}
