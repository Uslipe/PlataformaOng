package com.apoiaacao.apoiaacao_api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apoiaacao.apoiaacao_api.model.CampanhaDeItens;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_CampanhaDeItens;

@RestController
public class Controlador_CampanhaDeItens {
  private Repositorio_CampanhaDeItens Repositorio_CampanhaDeItens;

  public Controlador_CampanhaDeItens(Repositorio_CampanhaDeItens Repositorio_CampanhaDeItens) {
    this.Repositorio_CampanhaDeItens = Repositorio_CampanhaDeItens;
  }

  @PostMapping("/salvarCampanhaDeItens")
  public void salvarCampanhaDeItens(@RequestBody CampanhaDeItens campanhaDeItens) {
    Repositorio_CampanhaDeItens.save(campanhaDeItens);
  }

  @PostMapping("/deletarCampanhaDeItens")
  public void deletarCampanhaDeItens(@RequestBody CampanhaDeItens campanhaDeItens) {
    Repositorio_CampanhaDeItens.delete(campanhaDeItens);
  }
}
