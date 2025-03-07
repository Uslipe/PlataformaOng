package com.apoiaacao.apoiaacao_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apoiaacao.apoiaacao_api.model.CampanhaFinanceira;
import com.apoiaacao.apoiaacao_api.model.DoacaoFinanceira;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.model.MetodoPagamento;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_DoacaoFinanceira;
import com.apoiaacao.apoiaacao_api.service.DoacaoFinanceiraService;
import com.apoiaacao.apoiaacao_api.service.EmailService;

@CrossOrigin(origins = {"http://localhost:5173/", "http://localhost:3000"})
@RestController
public class Controlador_DoacaoFinanceira {

    @Autowired
    private DoacaoFinanceiraService doacaoFinanceiraService;

    @Autowired
    private Repositorio_DoacaoFinanceira Repositorio_DoacaoFinanceira;
    
    @Autowired
    private EmailService emailService;
    
    public Controlador_DoacaoFinanceira(DoacaoFinanceiraService DoacaoFinanceiraService ,Repositorio_DoacaoFinanceira Repositorio_DoacaoFinanceira) {
        this.doacaoFinanceiraService = DoacaoFinanceiraService;
        this.Repositorio_DoacaoFinanceira = Repositorio_DoacaoFinanceira;
    }

    @PreAuthorize("hasRole('DOADOR')")
    @PostMapping("/salvarDoacaoFinanceira")
    public ResponseEntity<DoacaoFinanceira> salvarDoacaoFinanceira(@RequestBody DoacaoFinanceira doacaoFinanceira) {
        int idCampanhaFinanceira = doacaoFinanceira.getCampanha().getIdCampanhaFinanceira();    
        int idUsuario = doacaoFinanceira.getIdUsuario().getId();
        DoacaoFinanceira doacao = doacaoFinanceiraService.criarDoacao(idCampanhaFinanceira, idUsuario, doacaoFinanceira);
        String emailDoador = doacao.getIdUsuario().getEmail();
        double valorDoacao = doacaoFinanceira.getValor();
        MetodoPagamento formPagamentoDoacao = doacaoFinanceira.getFormaPagamento();
        String nomeCampanha = doacao.getCampanha().getNome();
        try {
            emailService.sendEmail(emailDoador, "ApoiaAção - Confirmação de doação financeira", "Doação de R$" + valorDoacao + " no " + formPagamentoDoacao + " para a campanha " + nomeCampanha + " realizada com sucesso!");
        } catch (Exception e) {
            e.printStackTrace();
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(doacao);
    }

    @GetMapping("/Doacao")
    public CampanhaFinanceira getCampanhaFinanceira(DoacaoFinanceira doacao) {
        return doacao.getCampanha();
    }

    @GetMapping ("/listarDoacoesFinanceiras")
    public Iterable<DoacaoFinanceira> listarDoacoesFinanceiras() {
        return Repositorio_DoacaoFinanceira.findAll();
    }

    @GetMapping("/listarDoacoesFinanceirasPorUsuario")
    public Iterable<DoacaoFinanceira> listarDoacoesFinanceirasPorUsuario(@RequestBody Usuario usuario) {
        return Repositorio_DoacaoFinanceira.findByIdUsuario(usuario);
    }
}
