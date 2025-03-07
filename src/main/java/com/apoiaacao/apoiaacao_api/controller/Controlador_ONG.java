package com.apoiaacao.apoiaacao_api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.apoiaacao.apoiaacao_api.model.ONG;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_ONG;
import com.apoiaacao.apoiaacao_api.service.ONGService;
import com.apoiaacao.apoiaacao_api.util.BCryptEncoder;

@CrossOrigin(origins = {"http://localhost:5173/", "http://localhost:3000"})
@RestController
public class Controlador_ONG {

  @Autowired
  private Repositorio_ONG Repositorio_ONG;

  @Autowired
  private ONGService ongService;
  
  public Controlador_ONG(Repositorio_ONG Repositorio_ONG) {
    this.Repositorio_ONG = Repositorio_ONG;
  }

  @PostMapping("/salvarONG")
  public ResponseEntity<ONG> salvarONG(@RequestBody ONG ong) {
    ong.setValidada(false); // Define a ONG como não validada inicialmente
    String hashSenha = BCryptEncoder.encoder(ong.getSenha());
    ong.setSenha(hashSenha);
    ONG savedOng = Repositorio_ONG.save(ong);
    return ResponseEntity.status(HttpStatus.CREATED).body(savedOng);
  }

  @PostMapping("/loginOng")
  public String login(@RequestBody ONG ong) {
    return ongService.verificarOng(ong);
  }

  @PreAuthorize("hasRole('ADMIN')")
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

@PutMapping("/atualizarONG/{cnpj}")
    public ResponseEntity<ONG> atualizarONG(@PathVariable String cnpj, @RequestBody ONG ongAtualizada) {
        Optional<ONG> optionalOng = Repositorio_ONG.findByCnpj(cnpj);
        if (optionalOng.isPresent()) {
            ONG ongExistente = optionalOng.get();
            ongExistente.setNome(ongAtualizada.getNome());
            ongExistente.setEndereco(ongAtualizada.getEndereco());
            ongExistente.setContaBancaria(ongAtualizada.getContaBancaria());
            ongExistente.setChavePix(ongAtualizada.getChavePix());

            Repositorio_ONG.save(ongExistente);
            
            return ResponseEntity.ok(ongExistente);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

}
