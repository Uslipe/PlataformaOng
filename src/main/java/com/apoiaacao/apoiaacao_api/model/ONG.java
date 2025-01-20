package com.apoiaacao.apoiaacao_api.model;

public class ONG extends Usuario{

    private String cnpj;
    private String endereco;
    //O atributo lista de campanhas descrito no diagrama de classe será montado a partir do banco, sem necessidade de uma lista
    private String contaBancaria; //O atributo num da conta foi alterado para String para seguir o padrão do IBAN
    private String chavePix;

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
        return super.getNome();
    }

    public void setNome(String nome){
        super.setNome(nome);
    }

    public String getEmail(){
        return super.getEmail();
    }

    public void setEmail(String email){
        super.setNome(email);
    }
    
}
