package com.apoiaacao.apoiaacao_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doThrow;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import com.apoiaacao.apoiaacao_api.controller.Controlador_Usuario;
import com.apoiaacao.apoiaacao_api.model.TipoDeUsuario;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_Usuario;
import com.apoiaacao.apoiaacao_api.service.EmailService;
import com.apoiaacao.apoiaacao_api.service.UsuarioService;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;

//Testes do requisito de cadastrar novo doador
@SpringBootTest
@AutoConfigureMockMvc
public class ControladorUsuarioTest {

    @InjectMocks
    private Controlador_Usuario controladorUsuario;

    @Mock
    private Repositorio_Usuario repositorioUsuario;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private EmailService emailService;

    private Usuario usuario;

    private Usuario usuarioNovo;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        // Cria e configura o TipoDeUsuario
        TipoDeUsuario tipoDeUsuario = new TipoDeUsuario();
        tipoDeUsuario.setIdTipoDeUsuario(1);
        tipoDeUsuario.setRoleTipoDeUsuario("DOADOR");
        
        // Cria e configura o Usuario
        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Usuario Teste");
        usuario.setEmail("teste@example.com");
        usuario.setSenha("senha123");
        usuario.setTipoDeUsuario(tipoDeUsuario);
    }

    @Test
    public void testSalvarUsuario_EntradasCorretas() { //Esse aqui está retornando nada
        System.out.println("Executando testSalvarUsuario_EntradasCorretas");

        when(usuarioService.criarUsuario(any(Integer.class), any(Usuario.class))).thenReturn(usuario);

        ResponseEntity<Usuario> response = controladorUsuario.salvarUsuario(usuario);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        System.out.println("Response: " + response.getBody());
        // assert response.getStatusCode() == HttpStatus.CREATED;
        // System.out.println("Response: " + response.getBody());
        // assert response.getBody().getEmail().equals("teste@example.com");
    }

    // @Test
    // public void testSalvarUsuario_EmailJaEmUso() { //Como posso testar isso? Se o email já está em uso, ele retorna badrequest?
    //     System.out.println("Executando testSalvarUsuario_EmailJaEmUso");
    //     // Cria e configura o Usuario
    //     usuarioNovo = new Usuario();
    //     usuarioNovo.setId(2);
    //     usuarioNovo.setNome("Usuario Mesmo Email");
    //     usuarioNovo.setEmail("teste@example.com");
    //     usuarioNovo.setSenha("senha123");
    //     usuarioNovo.setTipoDeUsuario(usuario.getTipoDeUsuario());

    //     when(repositorioUsuario.findById(any(Integer.class))).thenReturn(Optional.of(usuarioNovo));

    //     ResponseEntity<Usuario> response = controladorUsuario.salvarUsuario(usuarioNovo);

    //     // assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
    //     // assert response.getBody() == null;
    //     assert response.getStatusCode() == HttpStatus.BAD_REQUEST;
    //     assert response.getBody() == null;
    // }

    @Test
    public void testSalvarUsuario_CampoVazio() { 
        //Se um usuario tentar criar uma conta com um dos campos vazios, ele retorna badrequest?
        System.out.println("Executando testSalvarUsuario_CampoVazio");
        // Teste para campo "Nome" obrigatório
        usuario.setNome(null);
        ResponseEntity<Usuario> response = controladorUsuario.salvarUsuario(usuario);
        System.out.println("Status Code (Nome): " + response.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        //assertNotEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        //assert response.getStatusCode().equals(HttpStatus.BAD_REQUEST);

        // Teste para campo "Email" obrigatório
        usuario.setNome("Usuario Teste");
        usuario.setEmail(null);
        response = controladorUsuario.salvarUsuario(usuario);
        System.out.println("Status Code (Email): " + response.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        //assert response.getStatusCode() == HttpStatus.BAD_REQUEST;

        // Teste para campo "Senha" obrigatório
        usuario.setEmail("teste@example.com");
        usuario.setSenha(null);
        response = controladorUsuario.salvarUsuario(usuario);
        System.out.println("Status Code (Senha): " + response.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        //assert response.getStatusCode() == HttpStatus.BAD_REQUEST;
  }
}