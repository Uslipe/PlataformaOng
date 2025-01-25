package com.apoiaacao.apoiaacao_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apoiaacao.apoiaacao_api.model.CampanhaFinanceira;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_CampanhaFinanceira;

@RestController
public class Controlador_CampanhaFinanceira {
  private Repositorio_CampanhaFinanceira Repositorio_CampanhaFinanceira;
    
  public Controlador_CampanhaFinanceira(Repositorio_CampanhaFinanceira Repositorio_CampanhaFinanceira) {
     this.Repositorio_CampanhaFinanceira = Repositorio_CampanhaFinanceira;
  }

  @PostMapping("/salvarCampanhaFinanceira")
  public void salvarCampanhaFinanceira(@RequestBody CampanhaFinanceira campanhaFinanceira) {
    Repositorio_CampanhaFinanceira.save(campanhaFinanceira);
  }

  @PostMapping("/deletarCampanhaFinanceira")
  public void deletarCampanhaFinanceira(@RequestBody CampanhaFinanceira campanhaFinanceira) {
    Repositorio_CampanhaFinanceira.delete(campanhaFinanceira);
  }
  
  @PostMapping("/atualizarCampanhaFinanceira")
  public void atualizarCampanhaFinanceira(@RequestBody CampanhaFinanceira campanhaFinanceira) {
    Repositorio_CampanhaFinanceira.save(campanhaFinanceira);
  }

  @GetMapping("/listarCampanhasFinanceiras")
  public Iterable<CampanhaFinanceira> listarCampanhasFinanceiras() {
    return Repositorio_CampanhaFinanceira.findAll();
  }

  @GetMapping("/listarCampanhasFinanceirasPorONG")
  public Iterable<CampanhaFinanceira> listarCampanhasFinanceirasPorONG(@RequestBody int idOng) {
    return Repositorio_CampanhaFinanceira.findByIdOng(idOng);
  }
  
}
