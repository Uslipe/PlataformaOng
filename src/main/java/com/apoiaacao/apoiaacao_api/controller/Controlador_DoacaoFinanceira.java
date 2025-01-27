package com.apoiaacao.apoiaacao_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apoiaacao.apoiaacao_api.model.DoacaoFinanceira;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_DoacaoFinanceira;
import com.apoiaacao.apoiaacao_api.service.DoacaoFinanceiraService;

@RestController
public class Controlador_DoacaoFinanceira {

    @Autowired
    private DoacaoFinanceiraService doacaoFinanceiraService;

    private Repositorio_DoacaoFinanceira Repositorio_DoacaoFinanceira;
    
    public Controlador_DoacaoFinanceira(Repositorio_DoacaoFinanceira Repositorio_DoacaoFinanceira) {
        this.Repositorio_DoacaoFinanceira = Repositorio_DoacaoFinanceira;
    }

    @PostMapping("/salvarDoacaoFinanceira")
    public ResponseEntity<DoacaoFinanceira> salvarDoacaoFinanceira(@RequestBody DoacaoFinanceira doacaoFinanceira) {
        int idCampanhaFinanceira = doacaoFinanceira.getCampanha().getIdCampanhaFinanceira();
        int idUsuario = doacaoFinanceira.getIdUsuario().getId();
        DoacaoFinanceira doacao = doacaoFinanceiraService.criarDoacao(idCampanhaFinanceira, idUsuario, doacaoFinanceira);
        return ResponseEntity.status(HttpStatus.CREATED).body(doacao);
    }

    @GetMapping ("/listarDoacoesFinanceiras")
    public Iterable<DoacaoFinanceira> listarDoacoesFinanceiras() {
        return Repositorio_DoacaoFinanceira.findAll();
    }

    @GetMapping("/listarDoacoesFinanceirasPorUsuario")
    public Iterable<DoacaoFinanceira> listarDoacoesFinanceirasPorUsuario(@RequestBody Usuario idUsuario) {
        return Repositorio_DoacaoFinanceira.findByIdUsuario(idUsuario);
    }
}
