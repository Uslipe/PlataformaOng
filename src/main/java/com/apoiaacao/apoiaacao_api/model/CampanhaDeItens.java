package com.apoiaacao.apoiaacao_api.model;

import java.time.LocalDate;

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
@Table(name = "campanha_de_itens")
public class CampanhaDeItens{
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_campanha_de_itens")
    private int idCampanhaDeItens;

    @ManyToOne
    @JoinColumn(name = "id_ong")
    private ONG idOng;

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

    @Column(name = "categoria_itens")
    @Enumerated(EnumType.STRING)
    private CategoriaItens categoriaItens;

    @Column(name = "encerrada")
    private boolean encerrada;

    @Column(name = "itens_a_caminho")
    private int itensACaminho;

    @Column(name = "nome_imagem")
    private String nomeDaImagem;

    @Column(name = "tipo_imagem")
    private String tipoDaImagem;

    @Column(name = "dados_imagem", columnDefinition = "BYTEA")
    private byte[] dadosDaImagem;

    public CampanhaDeItens() {
    }

    public CampanhaDeItens(int id) {
        this.idCampanhaDeItens = id;
    }

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

    public CategoriaItens getCategoriaItens() {
        return categoriaItens;
    }

    public void setCategoriaItens(CategoriaItens categoriaItens) {
        this.categoriaItens = categoriaItens;
    }

    public boolean getEncerrada() {
        return encerrada;
    }

    public void setEncerrada(boolean encerrada) {
        this.encerrada = encerrada;
    }

    public int itensACaminho() {
        return itensACaminho;
    }

    public void setItensACaminho(int itensACaminho) {
        this.itensACaminho = itensACaminho;
    }

    public int getItensACaminho() {
        return itensACaminho;
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
    
}
