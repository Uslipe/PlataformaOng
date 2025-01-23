package com.apoiaacao.apoiaacao_api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apoiaacao.apoiaacao_api.model.DoacaoDeItens;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_DoacaoDeItens;

@RestController
public class Controlador_DoacaoDeItens {
  private Repositorio_DoacaoDeItens Repositorio_DoacaoDeItens;

  public Controlador_DoacaoDeItens(Repositorio_DoacaoDeItens Repositorio_DoacaoDeItens) {
    this.Repositorio_DoacaoDeItens = Repositorio_DoacaoDeItens;
  }
  
  @PostMapping("/salvarDoacaoDeItens")
  public void salvarDoacaoDeItens(@RequestBody DoacaoDeItens doacaoDeItens) {
    Repositorio_DoacaoDeItens.save(doacaoDeItens);
  }
}
