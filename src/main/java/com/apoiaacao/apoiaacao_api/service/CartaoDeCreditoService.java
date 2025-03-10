package com.apoiaacao.apoiaacao_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apoiaacao.apoiaacao_api.model.CartaoDeCredito;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_CartaoDeCredito;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_Usuario;
import com.apoiaacao.apoiaacao_api.util.BCryptEncoder;

@Service
public class CartaoDeCreditoService {
    @Autowired
    Repositorio_CartaoDeCredito repositorio_CartaoDeCredito;

    @Autowired
    Repositorio_Usuario repositorio_Usuario;

    public CartaoDeCredito criarCartao(CartaoDeCredito cartaoDeCredito, Usuario usuario) {
        // Verifica se o cartão já existe
        CartaoDeCredito cartaoExistente = repositorio_CartaoDeCredito.findByDigitosCartaoAndNomeTitularAndDataDeValidadeAndCvv(
            cartaoDeCredito.getDigitosCartao(), cartaoDeCredito.getNomeTitular(), cartaoDeCredito.getDataDeValidade(), cartaoDeCredito.getCvv());

        if (cartaoExistente != null) {
            // Se o cartão já existe, associa o cartão existente ao usuário
            usuario.setIdCartaoDeCredito(cartaoExistente);
            return cartaoExistente;
        } else {
            // Se o cartão não existe, cria um novo cartão e associa ao usuário
            usuario.setIdCartaoDeCredito(cartaoDeCredito);

            String hashCvv = BCryptEncoder.encoder(cartaoDeCredito.getCvv());
            cartaoDeCredito.setCvv(hashCvv);

            return repositorio_CartaoDeCredito.save(cartaoDeCredito);
        }
    }

    public CartaoDeCredito getUltimoCartao(Usuario usuario) {
        Usuario usuario1 = repositorio_Usuario.findByEmail(usuario.getEmail());
        if (usuario1 != null) {
            return usuario1.getIdCartaoDeCredito();
        }
        return null;
    }

}
