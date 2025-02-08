package com.apoiaacao.apoiaacao_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apoiaacao.apoiaacao_api.model.CampanhaDeItens;
import com.apoiaacao.apoiaacao_api.model.ONG;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_CampanhaDeItens;
import com.apoiaacao.apoiaacao_api.service.CampanhaDeItensService;

@RestController
public class Controlador_CampanhaDeItens {
  @Autowired
  private CampanhaDeItensService campanhaDeItensService;
  @Autowired
  private Repositorio_CampanhaDeItens Repositorio_CampanhaDeItens;

  public Controlador_CampanhaDeItens(CampanhaDeItensService CampanhaDeItensService, Repositorio_CampanhaDeItens Repositorio_CampanhaDeItens) {
    this.campanhaDeItensService = CampanhaDeItensService;
    this.Repositorio_CampanhaDeItens = Repositorio_CampanhaDeItens;
  }

  @PostMapping("/salvarCampanhaDeItens")
  public ResponseEntity<CampanhaDeItens> salvarCampanhaDeItens(@RequestBody CampanhaDeItens campanhaDeItens) {
    int idOng = campanhaDeItens.getIdOng().getId();
    CampanhaDeItens campanha = campanhaDeItensService.criarCampanha(idOng, campanhaDeItens);
    return ResponseEntity.status(HttpStatus.CREATED).body(campanha);
  }

  @PostMapping("/deletarCampanhaDeItens")
  public void deletarCampanhaDeItens(@RequestBody CampanhaDeItens campanhaDeItens) {
    Repositorio_CampanhaDeItens.delete(campanhaDeItens);
  }

  @PostMapping("/atualizarCampanhaDeItens")
  public void atualizarCampanhaDeItens(@RequestBody CampanhaDeItens campanhaDeItens) {
    Repositorio_CampanhaDeItens.save(campanhaDeItens);
  }

  @GetMapping("/listarCampanhasDeItens")
  public Iterable<CampanhaDeItens> listarCampanhasDeItens() {
    return Repositorio_CampanhaDeItens.findAll();
  }

  @GetMapping("/listarCampanhasDeItensPorONG")
  public Iterable<CampanhaDeItens> listarCampanhasDeItensPorONG(@RequestBody ONG idOng) {
    return Repositorio_CampanhaDeItens.findByIdOng(idOng);
  }

  // @DeleteMapping("/apagarCampanhaDeItens")
    // public void apagarCampanha(@RequestBody Usuario usuario) {
    //     usuarioService.apagarCampanha(usuario);
  // }

}
