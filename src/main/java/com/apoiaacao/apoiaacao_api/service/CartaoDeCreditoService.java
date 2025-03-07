package com.apoiaacao.apoiaacao_api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.apoiaacao.apoiaacao_api.model.CartaoDeCredito;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_CartaoDeCredito;

@Service
public class CartaoDeCreditoService {
    @Autowired
    Repositorio_CartaoDeCredito repositorio_CartaoDeCredito;

    public CartaoDeCredito criarCartao(CartaoDeCredito cartaoDeCredito, Usuario usuario){
        usuario.setIdCartaoDeCredito(cartaoDeCredito); //Relaciona o cartão com o usuário

        return repositorio_CartaoDeCredito.save(cartaoDeCredito);
    }

}
