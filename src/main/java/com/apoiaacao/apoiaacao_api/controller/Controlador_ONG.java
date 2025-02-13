package com.apoiaacao.apoiaacao_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apoiaacao.apoiaacao_api.model.ONG;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_ONG;

@RestController
public class Controlador_ONG {

  @Autowired
  private Repositorio_ONG Repositorio_ONG;
  
  public Controlador_ONG(Repositorio_ONG Repositorio_ONG) {
    this.Repositorio_ONG = Repositorio_ONG;
  }

  @PostMapping("/salvarONG")
  public ResponseEntity<ONG> salvarONG(@RequestBody ONG ong) {
    ong.setValidada(false); // Define a ONG como não validada inicialmente
    ONG savedOng = Repositorio_ONG.save(ong);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedOng);
  }

  @GetMapping("/listarONG")
  public Iterable<ONG> listarONG() {
    return Repositorio_ONG.findAll();
  }

  @PutMapping("/validarONG")
    public ResponseEntity<ONG> validarONG(@RequestBody ONG ong) {
        ONG ongExistente = Repositorio_ONG.findById(ong.getId())
                .orElseThrow(() -> new RuntimeException("ONG não encontrada"));
        ongExistente.setValidada(true); // Define a ONG como validada
        Repositorio_ONG.save(ongExistente);
        return ResponseEntity.ok(ongExistente);
    }

  // @PutMapping("/validarOng")
    // public void validarOng(@RequestBody Usuario usuario) {
    //     usuarioService.validarOng(usuario);
    // }

}
