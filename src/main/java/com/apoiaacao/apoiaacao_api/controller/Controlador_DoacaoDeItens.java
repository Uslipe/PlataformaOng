package com.apoiaacao.apoiaacao_api.controller;

import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import com.apoiaacao.apoiaacao_api.service.DoacaoDeItensService;
import com.apoiaacao.apoiaacao_api.model.DoacaoDeItens;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_DoacaoDeItens;
import com.apoiaacao.apoiaacao_api.service.EmailService;
import com.apoiaacao.apoiaacao_api.model.CategoriaItens;

@CrossOrigin(origins = {"http://localhost:5173/", "http://localhost:3000"})
@RestController
public class Controlador_DoacaoDeItens {
  @Autowired
  private Repositorio_DoacaoDeItens Repositorio_DoacaoDeItens;

  @Autowired
  private DoacaoDeItensService doacaoDeItensService;

  @Autowired
    private EmailService emailService;

  public Controlador_DoacaoDeItens(Repositorio_DoacaoDeItens Repositorio_DoacaoDeItens) {
    this.Repositorio_DoacaoDeItens = Repositorio_DoacaoDeItens;
  }
  
  @PreAuthorize("hasRole('DOADOR')")
  @PostMapping("/salvarDoacaoDeItens")
  public ResponseEntity<DoacaoDeItens> salvarDoacaoDeItens(@RequestBody DoacaoDeItens doacaoDeItens) {
    int idCampanha = doacaoDeItens.getCampanhaDeItens().getIdCampanhaDeItens();
    int idUsuario = doacaoDeItens.getUsuario().getId();
    DoacaoDeItens doacao = doacaoDeItensService.criarDoacao(idCampanha, idUsuario, doacaoDeItens);
    String emailDoador = doacaoDeItens.getUsuario().getEmail();
    int quantidadeItens = doacaoDeItens.getQuantidadeDeItens();
    CategoriaItens categoriaItens = doacaoDeItens.getCategoriaItens();
    String nomeCampanha = doacao.getCampanhaDeItens().getNome();
    LocalDate dataDoacao = doacao.getDataDoacao();
    try {
      emailService.sendEmail(emailDoador, "ApoiaAção - Confirmação de doação de itens", "Doação de " + quantidadeItens + " itens do tipo " + categoriaItens + " para a campanha " + nomeCampanha + " realizada com sucesso!" + "\n"
      + "Data da Doação: " + dataDoacao);
    } catch (Exception e) {
      e.printStackTrace();
    }
    return ResponseEntity.status(HttpStatus.CREATED).body(doacao);
  }

  @GetMapping("/listarDoacoesDeItens")
  public Iterable<DoacaoDeItens> listarDoacoesDeItens() {
    return Repositorio_DoacaoDeItens.findAll();
  }

  @GetMapping("/listarDoacoesDeItensPorUsuario")
  public Iterable<DoacaoDeItens> listarDoacoesDeItensPorUsuario(@RequestBody Usuario usuario) {
    return Repositorio_DoacaoDeItens.findByIdUsuario(usuario);
  }

}
