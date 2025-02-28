package com.apoiaacao.apoiaacao_api.service;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.apoiaacao.apoiaacao_api.model.CampanhaFinanceira;
import com.apoiaacao.apoiaacao_api.model.ONG;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_CampanhaFinanceira;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_ONG;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

@Service
public class CampanhaFinanceiraService {

    @Autowired
    private Repositorio_ONG ongRepository;

    @Autowired
    private Repositorio_CampanhaFinanceira campanhaFinanceiraRepository;

    public CampanhaFinanceira criarCampanha(int idOng, String campanhaJson, MultipartFile imagem) throws IOException {
    // Criar ObjectMapper com suporte a LocalDate
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule()); // Suporte a LocalDate

    // Converter JSON para objeto
    CampanhaFinanceira campanha = objectMapper.readValue(campanhaJson, CampanhaFinanceira.class);
    

    // Buscar a ONG no banco de dados com o ID fornecido
    ONG ong = ongRepository.findById(idOng).orElseThrow(() -> new RuntimeException("ONG não encontrada"));

    campanha.setIdOng(ong);

    if (imagem != null && !imagem.isEmpty()) {
        campanha.setNomeDaImagem(imagem.getOriginalFilename());
        campanha.setTipoDaImagem(imagem.getContentType());

        byte[] dadosImagem = imagem.getBytes();
        campanha.setDadosDaImagem(dadosImagem);
    
        // Debug para verificar o tipo dos dados antes de salvar
        System.out.println("Tipo de dadosDaImagem: " + dadosImagem.getClass().getName());
        System.out.println("Tamanho da imagem: " + dadosImagem.length);    
    }

    return campanhaFinanceiraRepository.save(campanha);
    }
}

