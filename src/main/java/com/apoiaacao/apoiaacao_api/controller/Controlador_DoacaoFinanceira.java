package com.apoiaacao.apoiaacao_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apoiaacao.apoiaacao_api.model.DoacaoFinanceira;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_DoacaoFinanceira;

@RestController
public class Controlador_DoacaoFinanceira {
    private Repositorio_DoacaoFinanceira Repositorio_DoacaoFinanceira;
    
    public Controlador_DoacaoFinanceira(Repositorio_DoacaoFinanceira Repositorio_DoacaoFinanceira) {
        this.Repositorio_DoacaoFinanceira = Repositorio_DoacaoFinanceira;
    }

    @PostMapping("/salvarDoacaoFinanceira")
    public void salvarDoacaoFinanceira(@RequestBody DoacaoFinanceira doacaoFinanceira) {
        Repositorio_DoacaoFinanceira.save(doacaoFinanceira);
    }

    @GetMapping ("/listarDoacoesFinanceiras")
    public Iterable<DoacaoFinanceira> listarDoacoesFinanceiras() {
        return Repositorio_DoacaoFinanceira.findAll();
    }

    @GetMapping("/listarDoacoesFinanceirasPorUsuario")
    public Iterable<DoacaoFinanceira> listarDoacoesFinanceirasPorUsuario(@RequestBody int idUsuario) {
        return Repositorio_DoacaoFinanceira.findByIdUsuario(idUsuario);
    }
}
