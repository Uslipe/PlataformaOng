package com.apoiaacao.apoiaacao_api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apoiaacao.apoiaacao_api.model.ONG;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_ONG;

@RestController
public class Controlador_ONG {
  @Autowired
  private Repositorio_ONG Repositorio_ONG;
  
  public Controlador_ONG(Repositorio_ONG Repositorio_ONG) {
    this.Repositorio_ONG = Repositorio_ONG;
  }

  @PostMapping("/salvarONG")
  public void salvarONG(@RequestBody ONG ong) {
    Repositorio_ONG.save(ong);
  }

  @GetMapping("/listarONG")
  public Iterable<ONG> listarONG() {
    return Repositorio_ONG.findAll();
  }

  /*
  @PostMapping("/loginOng")
    public String login(@RequestBody ONG ong){
        return usuarioService.verificarUsuario(ong);
    }
  */
}
