package com.apoiaacao.apoiaacao_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;
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
    public void testSalvarUsuario_EntradasCorretas() { 
        System.out.println("Executando testSalvarUsuario_EntradasCorretas");

        when(usuarioService.criarUsuario(any(Integer.class), anyInt(), any(Usuario.class))).thenReturn(usuario);

        ResponseEntity<Usuario> response = controladorUsuario.salvarUsuario(usuario);

        assertEquals(response.getStatusCode(), HttpStatus.CREATED);
        System.out.println("Response: " + response.getBody());
    }

    @Test
    public void testSalvarUsuario_EmailJaEmUso() { 
        System.out.println("Executando testSalvarUsuario_EmailJaEmUso");
        // Cria e configura o Usuario
        usuarioNovo = new Usuario();
        usuarioNovo.setId(2);
        usuarioNovo.setNome("Usuario Mesmo Email");
        usuarioNovo.setEmail("teste@example.com");
        usuarioNovo.setSenha("senha123");
        usuarioNovo.setTipoDeUsuario(usuario.getTipoDeUsuario());

        when(repositorioUsuario.findByEmail(any(String.class))).thenReturn(usuarioNovo);

        ResponseEntity<Usuario> response = controladorUsuario.salvarUsuario(usuarioNovo);

        assertEquals(response.getStatusCode(), HttpStatus.BAD_REQUEST);
        assertNull(response.getBody());
    }

    @Test
    public void testSalvarUsuario_CampoVazio() { 
        System.out.println("Executando testSalvarUsuario_CampoVazio");
        // Teste para campo "Nome" obrigatório
        usuario.setNome(null);
        ResponseEntity<Usuario> response = controladorUsuario.salvarUsuario(usuario);
        System.out.println("Status Code (Nome): " + response.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // Teste para campo "Email" obrigatório
        usuario.setNome("Usuario Teste");
        usuario.setEmail(null);
        response = controladorUsuario.salvarUsuario(usuario);
        System.out.println("Status Code (Email): " + response.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());

        // Teste para campo "Senha" obrigatório
        usuario.setEmail("teste@example.com");
        usuario.setSenha(null);
        response = controladorUsuario.salvarUsuario(usuario);
        System.out.println("Status Code (Senha): " + response.getStatusCode());
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
  }
}