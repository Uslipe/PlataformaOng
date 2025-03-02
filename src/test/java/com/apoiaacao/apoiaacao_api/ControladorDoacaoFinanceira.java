package com.apoiaacao.apoiaacao_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.apoiaacao.apoiaacao_api.controller.Controlador_DoacaoFinanceira;
import com.apoiaacao.apoiaacao_api.model.CampanhaFinanceira;
import com.apoiaacao.apoiaacao_api.model.DoacaoFinanceira;
import com.apoiaacao.apoiaacao_api.model.MetodoPagamento;
import com.apoiaacao.apoiaacao_api.model.ONG;
import com.apoiaacao.apoiaacao_api.model.TipoDeUsuario;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_DoacaoFinanceira;
import com.apoiaacao.apoiaacao_api.service.DoacaoFinanceiraService;

@ExtendWith(MockitoExtension.class)
public class ControladorDoacaoFinanceira {
    
    @Mock
    private DoacaoFinanceiraService doacaoFinanceiraService;

    @Mock
    private Repositorio_DoacaoFinanceira Repositorio_DoacaoFinanceira;

    @InjectMocks
    private Controlador_DoacaoFinanceira controladorDoacaoFinanceira;

    @InjectMocks
    private Usuario usuario;

    @InjectMocks
    private ONG ong;

    @InjectMocks
    private CampanhaFinanceira campanhaFinanceira;

    @InjectMocks
    private DoacaoFinanceira doacaoFinanceira;

    @BeforeEach
    public void setUp(){
        //Intanciar o tipo de usuário para o usuário
        TipoDeUsuario tipoDeUsuarioUsuario = new TipoDeUsuario();
        tipoDeUsuarioUsuario.setIdTipoDeUsuario(1);
        tipoDeUsuarioUsuario.setRoleTipoDeUsuario("DOADOR");
        
        //Instanciar o usuário responsável pela doação
        this.usuario.setId(1);
        this.usuario.setNome("Usuario Teste");
        this.usuario.setEmail("teste@example.com");
        this.usuario.setSenha("senha123");
        this.usuario.setTipoDeUsuario(tipoDeUsuarioUsuario);

        //Intanciar o tipo de usuário para a ONG
        TipoDeUsuario tipoDeUsuarioONG = new TipoDeUsuario();
        tipoDeUsuarioONG.setIdTipoDeUsuario(3);
        tipoDeUsuarioONG.setRoleTipoDeUsuario("ONG");

        //Instanciar ONG para campanha financeira
        this.ong.setId(1);
        this.ong.setNome("Ong teste");
        this.ong.setEndereco("Rua teste");
        this.ong.setContaBancaria("BR 1234 5678 9123 456");
        this.ong.setChavePix("+558199999-9999");
        this.ong.setCnpj("12.345.678/0000-01");
        this.ong.setValidada(false);
        this.ong.setSenha("senhateste");
        this.ong.setTipoDeUsuario(tipoDeUsuarioONG);

        //Instanciar campanha financeira para doação
        this.campanhaFinanceira.setIdCampanhaFinanceira(1);
        this.campanhaFinanceira.setIdOng(ong);
        this.campanhaFinanceira.setNome("Campanha teste");
        this.campanhaFinanceira.setDescricao("Descrição teste");
        this.campanhaFinanceira.setDataInicio(LocalDate.now());
        this.campanhaFinanceira.setDataFim(LocalDate.of(2025, 3, 10));
        this.campanhaFinanceira.setMetaValor(10000);
        this.campanhaFinanceira.setValorArrecadado(0);
        this.campanhaFinanceira.setEncerrada(false);
        this.campanhaFinanceira.setNomeDaImagem("campanha_teste.jpg"); 
        this.campanhaFinanceira.setTipoDaImagem("image/jpeg"); 
        this.campanhaFinanceira.setDadosDaImagem(new byte[]{1, 2, 3, 4, 5}); //É possível pegar uma imagem real aqui

        //Instanciar finalmente a doação financeira
        this.doacaoFinanceira.setId(1);
        this.doacaoFinanceira.setIdUsuario(usuario);
        this.doacaoFinanceira.setCampanha(campanhaFinanceira);
        this.doacaoFinanceira.setValor(350.0);
        this.doacaoFinanceira.setDataDoacao(LocalDate.now());
        this.doacaoFinanceira.setFormaPagamento(MetodoPagamento.PIX);
    }

    @SuppressWarnings("null")
    @Test
    public void testSalvarDoacao(){
        System.out.println("\n-------- Executando testSalvarDoacao --------");

        when(doacaoFinanceiraService.criarDoacao(anyInt(), anyInt(), any(DoacaoFinanceira.class))).thenReturn(doacaoFinanceira);  

        ResponseEntity<DoacaoFinanceira> response = controladorDoacaoFinanceira.salvarDoacaoFinanceira(doacaoFinanceira);
        System.out.println(response);

        assertNotNull(response);
        assertNotNull(response.getBody());
        assertEquals(350.0, response.getBody().getValor());
        assertEquals(MetodoPagamento.PIX, response.getBody().getFormaPagamento());
        assertEquals(usuario.getId(), response.getBody().getIdUsuario().getId());
        assertEquals(campanhaFinanceira.getIdCampanhaFinanceira(), response.getBody().getCampanha().getIdCampanhaFinanceira());

    }
}
