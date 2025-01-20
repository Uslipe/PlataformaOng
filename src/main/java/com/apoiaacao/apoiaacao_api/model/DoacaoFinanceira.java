package com.apoiaacao.apoiaacao_api.model;

public class DoacaoFinanceira extends Doacao{
    private double valor;
    private String formaDePagamento; //Novamente, passível de mudar para ENUM

    public double getValor() {
        return valor;
    }

    public void setValor(double valor) {
        this.valor = valor;
    }

    public String getFormaDePagamento() {
        return formaDePagamento;
    }

    public void setFormaDePagamento(String formaDePagamento) {
        this.formaDePagamento = formaDePagamento;
    }

    public Doador getDoador(){
        return super.getDoador();
    }

    public void setDoador(Doador doador){
        super.setDoador(doador);
    }

    public Campanha getCampanha(){
        return super.getCampanha();
    }

    public void setCampanha(Campanha campanha){
        super.setCampanha(campanha);
    }
}
