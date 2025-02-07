package com.apoiaacao.apoiaacao_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apoiaacao.apoiaacao_api.model.CampanhaFinanceira;
import com.apoiaacao.apoiaacao_api.model.DoacaoFinanceira;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_CampanhaFinanceira;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_DoacaoFinanceira;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_Usuario;

@Service
public class DoacaoFinanceiraService {
    @Autowired
    private Repositorio_Usuario repositorio_Usuario;

    @Autowired
    private Repositorio_CampanhaFinanceira repositorio_CampanhaFinanceira;

    @Autowired
    private Repositorio_DoacaoFinanceira repositorio_DoacaoFinanceira;

    public DoacaoFinanceira criarDoacao(int idCampanhaFinanceira, int idUsuario, DoacaoFinanceira doacao) {
        //Buscar usuário e campanha pelo id
        Usuario usuario = repositorio_Usuario.findById(idUsuario)
                               .orElseThrow(() -> new RuntimeException("Usuario não encontrada"));
        CampanhaFinanceira campanha = repositorio_CampanhaFinanceira.findById(idCampanhaFinanceira)
                               .orElseThrow(() -> new RuntimeException("Campanha não encontrada"));
        System.out.println(campanha);

        
        //Atribuir atributos acima a doação
        doacao.setCampanha(campanha);
        doacao.setIdUsuario(usuario);
        campanha.setValorArrecadado(doacao.getValor() + campanha.getValorArrecadado());
        // Salvar a campanha financeira
        return repositorio_DoacaoFinanceira.save(doacao);
    }
}
