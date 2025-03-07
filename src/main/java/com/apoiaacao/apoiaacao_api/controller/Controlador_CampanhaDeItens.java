package com.apoiaacao.apoiaacao_api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apoiaacao.apoiaacao_api.model.CampanhaDeItens;
import com.apoiaacao.apoiaacao_api.model.ONG;
import com.apoiaacao.apoiaacao_api.model.CategoriaItens;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_CampanhaDeItens;
import com.apoiaacao.apoiaacao_api.service.CampanhaDeItensService;

@CrossOrigin(origins = {"http://localhost:5173/", "http://localhost:3000"})
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

  @DeleteMapping("/deletarCampanhaDeItens")
  public void deletarCampanhaDeItens(@RequestBody CampanhaDeItens campanhaDeItens) {
    Repositorio_CampanhaDeItens.delete(campanhaDeItens);
  }

  @PutMapping("/editarCampanhaDeItens/{id}")
  public ResponseEntity<CampanhaDeItens> editarCampanhaDeItens(@PathVariable int id, @RequestBody CampanhaDeItens campanhaAtualizada) {
    Optional<CampanhaDeItens> optionalCampanha = Repositorio_CampanhaDeItens.findById(id);
    if (optionalCampanha.isPresent()) {
      CampanhaDeItens campanha = optionalCampanha.get();
      campanha.setNome(campanhaAtualizada.getNome());
      campanha.setDescricao(campanhaAtualizada.getDescricao());
      campanha.setDataFim(campanhaAtualizada.getDataFim());
      campanha.setEndereco(campanhaAtualizada.getEndereco());

      Repositorio_CampanhaDeItens.save(campanha);
      return ResponseEntity.ok(campanha);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
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

  @GetMapping("/listarCampanhasDeItensEncerradas")
  public Iterable<CampanhaDeItens> listarCampanhasDeItensEncerradas() {
    return Repositorio_CampanhaDeItens.findByEncerrada(true);
  }

  @GetMapping("/listarCampanhasDeItensPorCategoriaItem")
  public Iterable<CampanhaDeItens> listarCampanhasDeItensPorCategoriaItem(@RequestBody CategoriaItens categoriaItens) {
    return Repositorio_CampanhaDeItens.findByCategoriaItens(categoriaItens);
  }

}
