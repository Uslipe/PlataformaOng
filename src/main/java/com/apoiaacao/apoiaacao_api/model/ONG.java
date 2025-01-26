package com.apoiaacao.apoiaacao_api.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "ong")
public class ONG{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_ong")
    private int id;

    @Column(name = "nome")
    private String nome;

    @Column(name = "endereco")
    private String endereco;
    //O atributo lista de campanhas descrito no diagrama de classe será montado a partir do banco, sem necessidade de uma lista

    @Column(name = "conta_bancaria")
    private String contaBancaria; //O atributo num da conta foi alterado para String para seguir o padrão do IBAN

    @Column(name = "chave_pix")
    private String chavePix;

    @Column(name = "cnpj")
    private String cnpj;

    public ONG() {
    }

    public ONG(int id){
        this.id = id;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getContaBancaria() {
        return contaBancaria;
    }

    public void setContaBancaria(String contaBancaria) {
        this.contaBancaria = contaBancaria;
    }

    public String getChavePix() {
        return chavePix;
    }

    public void setChavePix(String chavePix) {
        this.chavePix = chavePix;
    }

    public String getNome(){
        return nome;
    }

    public void setNome(String nome){
        this.nome = nome;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
}
