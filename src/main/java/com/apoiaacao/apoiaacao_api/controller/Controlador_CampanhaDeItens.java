package com.apoiaacao.apoiaacao_api.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
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

import com.apoiaacao.apoiaacao_api.model.CampanhaDeItens;
import com.apoiaacao.apoiaacao_api.model.ONG;
import com.apoiaacao.apoiaacao_api.model.CategoriaItens;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_CampanhaDeItens;
import com.apoiaacao.apoiaacao_api.service.CampanhaDeItensService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

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
  public ResponseEntity<?> salvarCampanhaDeItens(@RequestPart("campanhaDeItens") String campanhaDeItens, @RequestPart(value = "imagem",required = false) MultipartFile imagem) {

    try {
      ObjectMapper objectMapper = new ObjectMapper().registerModule(new JavaTimeModule()).configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);;
      CampanhaDeItens campanhaDeItens2 = objectMapper.readValue(campanhaDeItens, CampanhaDeItens.class);

      //Pegar o id da ong
      int idOng = campanhaDeItens2.getIdOng().getId();
      CampanhaDeItens campanha = campanhaDeItensService.criarCampanha(idOng, campanhaDeItens, imagem);
      return ResponseEntity.status(HttpStatus.CREATED).body(campanha);

    } catch (Exception e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
    }
  }

  @DeleteMapping("/deletarCampanhaDeItens/{id}")
  public ResponseEntity<Void> deletarCampanhaDeItens(@PathVariable int id) {
    if(campanhaDeItensService.deletarCampanhaFinanceira(id)){
      return ResponseEntity.noContent().build();
    }
    return ResponseEntity.notFound().build();
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
      campanha.setCategoriaItens(campanhaAtualizada.getCategoriaItens());
      
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

  @GetMapping("/listarCampanhasDeItensPorONG/{idOng}")
  public Iterable<CampanhaDeItens> listarCampanhasDeItensPorONG(@PathVariable ONG idOng) {
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

  @GetMapping("/imagem/campanhaDeItens/{idCampanhaDeItens}")
  public ResponseEntity<ByteArrayResource> getImagemCampanha(@PathVariable int idCampanhaDeItens) {
      Optional<CampanhaDeItens> campanhaOptional = Repositorio_CampanhaDeItens.findById(idCampanhaDeItens);
      
      if (campanhaOptional.isPresent()) {
        CampanhaDeItens campanha = campanhaOptional.get();
          
          // Recupera os dados binários da imagem e o tipo da imagem
          byte[] dadosImagem = campanha.getDadosDaImagem();
          String tipoImagem = campanha.getTipoDaImagem();
          
          if (dadosImagem != null && tipoImagem != null) {
              // Converte os dados binários para um recurso
              ByteArrayResource resource = new ByteArrayResource(dadosImagem);

              // Retorna a imagem com o tipo correto
              return ResponseEntity.ok()
                      .header(HttpHeaders.CONTENT_TYPE, tipoImagem)  // Define o tipo de conteúdo da imagem
                      .body(resource);
          } else {
              return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);  // Caso a imagem não exista
          }
      } else {
          return ResponseEntity.status(HttpStatus.NOT_FOUND).build();  // Campanha não encontrada
      }
  }

}
