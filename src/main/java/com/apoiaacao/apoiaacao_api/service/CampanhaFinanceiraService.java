package com.apoiaacao.apoiaacao_api.service;

import java.io.IOException;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.apoiaacao.apoiaacao_api.model.CampanhaFinanceira;
import com.apoiaacao.apoiaacao_api.model.ONG;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_CampanhaFinanceira;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_DoacaoFinanceira;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_ONG;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.transaction.Transactional;

@Service
public class CampanhaFinanceiraService {

    @Autowired
    private Repositorio_ONG ongRepository;

    @Autowired
    private Repositorio_CampanhaFinanceira campanhaFinanceiraRepository;

    @Autowired
    private Repositorio_DoacaoFinanceira repositorio_DoacaoFinanceira;

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
    }

    return campanhaFinanceiraRepository.save(campanha);
    }

    @Transactional
    public boolean deletarCampanhaFinanceira(int idCampanhaFinanceira) {
        Optional<CampanhaFinanceira> cFinanceira = campanhaFinanceiraRepository.findById(idCampanhaFinanceira);
    
        if (cFinanceira.isPresent()) {
            CampanhaFinanceira campanha = cFinanceira.get();
            
            if (Boolean.TRUE.equals(campanha.getEncerrada())) {
                //Deleta todas as doações relacionadas a essa campanha
                repositorio_DoacaoFinanceira.deleteByCampanhaFinanceira(campanha);
    
                //Agora pode deletar a campanha
                campanhaFinanceiraRepository.deleteById(idCampanhaFinanceira);
                return true;
            }
        }
        return false;
    }
    
}

