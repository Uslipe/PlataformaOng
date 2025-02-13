package com.apoiaacao.apoiaacao_api.model;

public class DoacaoWrapper {
    private DoacaoFinanceira doacaoFinanceira;
    private DoacaoDeItens doacaoDeItens;

    public DoacaoWrapper(DoacaoFinanceira doacaoFinanceira) {
        this.doacaoFinanceira = doacaoFinanceira;
    }

    public DoacaoWrapper(DoacaoDeItens doacaoDeItens) {
        this.doacaoDeItens = doacaoDeItens;
    }

    public DoacaoFinanceira getDoacaoFinanceira() {
        return doacaoFinanceira;
    }

    public void setDoacaoFinanceira(DoacaoFinanceira doacaoFinanceira) {
        this.doacaoFinanceira = doacaoFinanceira;
    }

    public DoacaoDeItens getDoacaoDeItens() {
        return doacaoDeItens;
    }

    public void setDoacaoDeItens(DoacaoDeItens doacaoDeItens) {
        this.doacaoDeItens = doacaoDeItens;
    }
}