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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.apoiaacao.apoiaacao_api.model.CampanhaFinanceira;
import com.apoiaacao.apoiaacao_api.model.ONG;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_CampanhaFinanceira;
import com.apoiaacao.apoiaacao_api.service.CampanhaFinanceiraService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@CrossOrigin
@RestController
public class Controlador_CampanhaFinanceira {
  @Autowired
  private CampanhaFinanceiraService campanhaFinanceiraService;
  @Autowired
  private Repositorio_CampanhaFinanceira Repositorio_CampanhaFinanceira;
    
  public Controlador_CampanhaFinanceira(CampanhaFinanceiraService campanhaFinanceiraService, Repositorio_CampanhaFinanceira Repositorio_CampanhaFinanceira) {
    this.campanhaFinanceiraService = campanhaFinanceiraService; 
    this.Repositorio_CampanhaFinanceira = Repositorio_CampanhaFinanceira;
  }

  @PostMapping("/salvarCampanhaFinanceira")                                                                               //Diz que a imagem não é obrigatória
  public ResponseEntity<?> salvarCampanhaFinanceira(@RequestPart("campanhaFinanceira") String campanhaFinanceira, @RequestPart(value = "imagem",required = false) MultipartFile imagem) {
    
    try {
      ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);;

      CampanhaFinanceira campanhaFinanceira2 = objectMapper.readValue(campanhaFinanceira, CampanhaFinanceira.class);
      int idOng = campanhaFinanceira2.getIdOng().getId();
      CampanhaFinanceira campanha = campanhaFinanceiraService.criarCampanha(idOng, campanhaFinanceira, imagem);
      return ResponseEntity.status(HttpStatus.CREATED).body(campanha);
    } 
    catch (Exception e) {
      return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());  
    }
    
  }

  @DeleteMapping("/deletarCampanhaFinanceira")
  public void deletarCampanhaFinanceira(@RequestBody CampanhaFinanceira campanhaFinanceira) {
    Repositorio_CampanhaFinanceira.delete(campanhaFinanceira);
  }
  
 @PutMapping("/editarCampanhaFinanceira/{id}")
  public ResponseEntity<CampanhaFinanceira> editarCampanhaFinanceira(@PathVariable int id, @RequestBody CampanhaFinanceira campanhaAtualizada) {
    Optional<CampanhaFinanceira> optionalCampanha = Repositorio_CampanhaFinanceira.findById(id);
    if (optionalCampanha.isPresent()) {
      CampanhaFinanceira campanha = optionalCampanha.get();
      campanha.setNome(campanhaAtualizada.getNome());
      campanha.setDescricao(campanhaAtualizada.getDescricao());
      campanha.setDataFim(campanhaAtualizada.getDataFim());
      campanha.setMetaValor(campanhaAtualizada.getMetaValor());
      // Atualizar outros atributos conforme necessário

      Repositorio_CampanhaFinanceira.save(campanha); // Atualiza a campanha existente
      return ResponseEntity.ok(campanha);
    } else {
      return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }
  }

  @GetMapping("/listarCampanhasFinanceiras")
  public Iterable<CampanhaFinanceira> listarCampanhasFinanceiras() {
    return Repositorio_CampanhaFinanceira.findAll();
  }

  @GetMapping("/listarCampanhasFinanceirasPorONG")
  public Iterable<CampanhaFinanceira> listarCampanhasFinanceirasPorONG(@RequestBody ONG idOng) {
    return Repositorio_CampanhaFinanceira.findByIdOng(idOng);
  }
  
// @DeleteMapping("/apagarCampanhaFinanceira")
    // public void apagarCampanha(@RequestBody Usuario usuario) {
    //     usuarioService.apagarCampanha(usuario);
    // }

  @GetMapping("/listarCampanhasFinanceirasEncerradas")
  public Iterable<CampanhaFinanceira> listarCampanhasFinanceirasEncerradas() {
    return Repositorio_CampanhaFinanceira.findByEncerrada(true);
  }

}
