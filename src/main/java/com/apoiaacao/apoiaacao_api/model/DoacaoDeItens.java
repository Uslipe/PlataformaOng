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
@Table(name = "doacao_de_itens")
public class DoacaoDeItens{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_doacao_itens")
    private int id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario idUsuario;

    @ManyToOne
    @JoinColumn(name = "id_campanha_de_itens")
    private CampanhaDeItens campanhaDeItens;

    @Column(name = "qnt_itens")
    private int quantidadeDeItens;

    @Column(name = "data_doacao")
    private LocalDate dataDoacao;

    @Column(name = "categoria_itens")
    @Enumerated(EnumType.STRING)
    private CategoriaItens categoriaItens;

    public DoacaoDeItens() {
    }

    public int getQuantidadeDeItens() {
        return quantidadeDeItens;
    }

    public void setQuantidadeDeItens(int quantidadeDeItens) {
        this.quantidadeDeItens = quantidadeDeItens;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return idUsuario;
    }

    public void setUsuario(Usuario usuario) {
        this.idUsuario = usuario;
    }

    public CampanhaDeItens getCampanhaDeItens() {
        return campanhaDeItens;
    }

    public void setCampanhaDeItens(CampanhaDeItens campanhaDeItens) {
        this.campanhaDeItens = campanhaDeItens;
    }

    public LocalDate getDataDoacao() {
        return dataDoacao;
    }

    public void setDataDoacao(LocalDate dataDoacao) {
        this.dataDoacao = dataDoacao;
    }

    public CategoriaItens getCategoriaItens() {
        return categoriaItens;
    }

    public void setCategoriaItens(CategoriaItens categoriaItens) {
        this.categoriaItens = categoriaItens;
    }

}
