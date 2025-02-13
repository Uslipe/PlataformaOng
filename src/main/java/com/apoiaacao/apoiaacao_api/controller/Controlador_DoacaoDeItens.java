package com.apoiaacao.apoiaacao_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.apoiaacao.apoiaacao_api.service.DoacaoDeItensService;
import com.apoiaacao.apoiaacao_api.model.DoacaoDeItens;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_DoacaoDeItens;

@RestController
public class Controlador_DoacaoDeItens {
  @Autowired
  private Repositorio_DoacaoDeItens Repositorio_DoacaoDeItens;

  @Autowired
  private DoacaoDeItensService doacaoDeItensService;

  public Controlador_DoacaoDeItens(Repositorio_DoacaoDeItens Repositorio_DoacaoDeItens) {
    this.Repositorio_DoacaoDeItens = Repositorio_DoacaoDeItens;
  }
  
  @PostMapping("/salvarDoacaoDeItens")
  public ResponseEntity<DoacaoDeItens> salvarDoacaoDeItens(@RequestBody DoacaoDeItens doacaoDeItens) {
    int idCampanha = doacaoDeItens.getCampanhaDeItens().getIdCampanhaDeItens();
    System.out.println(idCampanha);
    int idUsuario = doacaoDeItens.getUsuario().getId();
    System.out.println(idUsuario);
    DoacaoDeItens doacao = doacaoDeItensService.criarDoacao(idCampanha, idUsuario, doacaoDeItens);
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
