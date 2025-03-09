package com.apoiaacao.apoiaacao_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apoiaacao.apoiaacao_api.repositories.Repositorio_CampanhaDeItens;
import com.apoiaacao.apoiaacao_api.model.CampanhaDeItens;
import com.apoiaacao.apoiaacao_api.model.DoacaoDeItens;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_DoacaoDeItens;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_Usuario;

@Service
public class DoacaoDeItensService {
    @Autowired
    private Repositorio_Usuario repositorio_Usuario;

    @Autowired
    private Repositorio_CampanhaDeItens repositorio_CampanhaDeItens;

    @Autowired
    private Repositorio_DoacaoDeItens repositorio_DoacaoDeItens;

    public Usuario retornarUsuario(int idUsuario){
        return repositorio_Usuario.findById(idUsuario)
                                  .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
    }

    public DoacaoDeItens criarDoacao(int idCampanhaDeItens, int idUsuario, DoacaoDeItens doacao){
        //Buscar usuário e campanha pelo id
        Usuario usuario = repositorio_Usuario.findById(idUsuario)
                               .orElseThrow(() -> new RuntimeException("Usuario não encontrado"));
        CampanhaDeItens campanha = repositorio_CampanhaDeItens.findById(idCampanhaDeItens)
                               .orElseThrow(() -> new RuntimeException("Campanha não encontrada"));

        //Atribuir atributos acima a doação
        doacao.setCampanhaDeItens(campanha);
        doacao.setUsuario(usuario);
        doacao.setDataDoacao(java.time.LocalDate.now());
        campanha.setItensACaminho(doacao.getQuantidadeDeItens() + campanha.getItensACaminho());

        // Salvar a campanha financeira
        return repositorio_DoacaoDeItens.save(doacao);
    }
}
