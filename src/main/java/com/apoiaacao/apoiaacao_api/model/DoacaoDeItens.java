package com.apoiaacao.apoiaacao_api.model;

public class DoacaoDeItens extends Doacao{
    private int quantidadeDeItens;
    private String enderecoAgenciaDosCorreios;
    private boolean entregue;

    public int getQuantidadeDeItens() {
        return quantidadeDeItens;
    }

    public void setQuantidadeDeItens(int quantidadeDeItens) {
        this.quantidadeDeItens = quantidadeDeItens;
    }

    public String getEnderecoAgenciaDosCorreios() {
        return enderecoAgenciaDosCorreios;
    }

    public void setEnderecoAgenciaDosCorreios(String enderecoAgenciaDosCorreios) {
        this.enderecoAgenciaDosCorreios = enderecoAgenciaDosCorreios;
    }

    public boolean isEntregue() {
        return entregue;
    }

    public void setEntregue(boolean entregue) {
        this.entregue = entregue;
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
