package com.apoiaacao.apoiaacao_api.repositories;

import java.time.LocalDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.apoiaacao.apoiaacao_api.model.CartaoDeCredito;

@Repository
public interface Repositorio_CartaoDeCredito extends JpaRepository<CartaoDeCredito, Integer>{
    CartaoDeCredito findByDigitosCartaoAndNomeTitularAndDataDeValidadeAndCvv(
        String digitosCartao, String nomeTitular, LocalDate dataDeValidade, String cvv);
}
