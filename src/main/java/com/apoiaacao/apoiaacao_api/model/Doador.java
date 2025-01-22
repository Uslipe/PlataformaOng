package com.apoiaacao.apoiaacao_api.model;

public class Doador extends Usuario{

    private String cpf;
    //O atributo no diagrama de classe "lista de doações" não será implementado usando arraylist, mas sim com consultas personalizadas do banco

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
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
