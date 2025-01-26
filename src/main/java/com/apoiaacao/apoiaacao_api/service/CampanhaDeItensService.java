package com.apoiaacao.apoiaacao_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apoiaacao.apoiaacao_api.model.CampanhaDeItens;
import com.apoiaacao.apoiaacao_api.model.ONG;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_CampanhaDeItens;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_ONG;

@Service
public class CampanhaDeItensService {

    @Autowired
    private Repositorio_ONG ongRepository;

    @Autowired
    private Repositorio_CampanhaDeItens campanhaDeItensRepository;

    public CampanhaDeItens criarCampanha(int idOng, CampanhaDeItens campanha) {
        // Buscar a ONG no banco de dados com o ID fornecido
        ONG ong = ongRepository.findById(idOng)
                               .orElseThrow(() -> new RuntimeException("ONG não encontrada"));

        // Atribuir a ONG à campanha
        campanha.setIdOng(ong);

        // Salvar a campanha financeira
        return campanhaDeItensRepository.save(campanha);
    }
}

