package com.apoiaacao.apoiaacao_api.model;

import java.time.LocalDate;

public class CampanhaDeItens extends Campanha{
    
    private String categoriaItem; //Passível de mudar para ENUM, mas teria que configurar pequenas mudanças no banco de dados
    private int quantidadeDeItens;
    private int quantidadeACaminho;
    private int quantidadeRecebida;

    public String getCategoriaItem() {
        return categoriaItem;
    }

    public void setCategoriaItem(String categoriaItem) {
        this.categoriaItem = categoriaItem;
    }

    public int getQuantidadeDeItens() {
        return quantidadeDeItens;
    }
    public void setQuantidadeDeItens(int quantidadeDeItens) {
        this.quantidadeDeItens = quantidadeDeItens;
    }

    public int getQuantidadeACaminho() {
        return quantidadeACaminho;
    }

    public void setQuantidadeACaminho(int quantidadeACaminho) {
        this.quantidadeACaminho = quantidadeACaminho;
    }

    public int getQuantidadeRecebida() {
        return quantidadeRecebida;
    }

    public void setQuantidadeRecebida(int quantidadeRecebida) {
        this.quantidadeRecebida = quantidadeRecebida;
    }

    public ONG getOngFundadora(){
        return super.getOngFundadora();
    }
    
    public void setOngFundadora(ONG ongFundadora){
        super.setOngFundadora(ongFundadora);
    }

    public LocalDate getDataLimite(){
        return super.getDataLimite();
    }

    public void setDataLimite(LocalDate dataLimite){
        super.setDataLimite(dataLimite);
    }

    public boolean isEncerrada(){
        return super.isEncerrada();
    }

    public void setEncerrada(boolean encerrada){
        super.setEncerrada(encerrada);
    }
}
