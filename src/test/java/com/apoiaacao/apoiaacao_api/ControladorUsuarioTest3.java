
package com.apoiaacao.apoiaacao_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

import java.util.HashMap;
import java.util.Map;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.apoiaacao.apoiaacao_api.controller.Controlador_Usuario;
import com.apoiaacao.apoiaacao_api.dto.LoginResponse;
import com.apoiaacao.apoiaacao_api.model.TipoDeUsuario;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_Usuario;
import com.apoiaacao.apoiaacao_api.service.EmailService;
import com.apoiaacao.apoiaacao_api.service.UsuarioService;

// Testes dos métodos relacionados ao Gerenciamento de Perfil dos Doadores
@ExtendWith(MockitoExtension.class)
public class ControladorUsuarioTest3 {

    @Mock
    private Repositorio_Usuario repositorioUsuario;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private EmailService emailService;

    private Controlador_Usuario controladorUsuario;

    private Usuario usuario;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);

        // ✅ Criando uma instância real do controlador com os mocks injetados corretamente
        controladorUsuario = new Controlador_Usuario(usuarioService, repositorioUsuario);

        // Criando um usuário de teste
        TipoDeUsuario tipoDeUsuario = new TipoDeUsuario();
        tipoDeUsuario.setIdTipoDeUsuario(1);
        tipoDeUsuario.setRoleTipoDeUsuario("DOADOR");

        usuario = new Usuario();
        usuario.setId(1);
        usuario.setNome("Usuario Teste");
        usuario.setEmail("teste@example.com");
        usuario.setSenha("senha123");
        usuario.setTipoDeUsuario(tipoDeUsuario);
    }

    @SuppressWarnings("null")
    @Test
public void login() {
    System.out.println("\n-------- Executando loginTest --------");

    // 🔍 Adicionando verificações antes de mockar
    assertNotNull(usuarioService, "usuarioService está nulo!");
    assertNotNull(repositorioUsuario, "repositorioUsuario está nulo!");
    assertNotNull(controladorUsuario, "controladorUsuario está nulo!");

    // 🔍 Garantindo que `usuarioService` retorne um token válido
    when(usuarioService.verificarUsuario(anyString(), anyString())).thenReturn("token");

    // 🔍 Garantindo que `repositorioUsuario.findByEmail()` não retorne null
    when(repositorioUsuario.findByEmail(anyString())).thenReturn(usuario);
    assertNotNull(repositorioUsuario.findByEmail("teste@example.com"), "Usuário retornado pelo repositório está nulo!");

    Map<String, String> loginData = new HashMap<>();
    loginData.put("email", "teste@example.com");
    loginData.put("senha", "senha123");

    // Chamada real do método
    ResponseEntity<LoginResponse> loginResponse = controladorUsuario.login(loginData);

    // 🔍 Depuração extra
    System.out.println("Resposta HTTP: " + loginResponse.getStatusCode());
    if (loginResponse.getBody() != null) {
        System.out.println("Token recebido: " + loginResponse.getBody().getToken());
        System.out.println("ID do Usuário: " + loginResponse.getBody().getIdUsuario());
    } else {
        System.out.println("Resposta sem corpo!");
    }

    // ✅ Verificações
    assertNotNull(loginResponse);
    assertEquals(200, loginResponse.getStatusCode().value());
    assertNotNull(loginResponse.getBody());
    assertNotNull(loginResponse.getBody().getToken());
    assertEquals("token", loginResponse.getBody().getToken());
    assertEquals(1, loginResponse.getBody().getIdUsuario());
}


@SuppressWarnings("null")
@Test
public void loginComEmailESenhaVazios() {
    System.out.println("\n-------- Executando loginComEmailESenhaVaziosTest --------");

    // 🔍 Verificando se os serviços e repositórios não são nulos antes do mock
    assertNotNull(usuarioService, "usuarioService está nulo!");
    assertNotNull(repositorioUsuario, "repositorioUsuario está nulo!");
    assertNotNull(controladorUsuario, "controladorUsuario está nulo!");

    // 🔍 Mockando o comportamento esperado para email e senha vazios
    when(usuarioService.verificarUsuario("", "")).thenReturn(null);
    when(repositorioUsuario.findByEmail("")).thenReturn(null);

    // Criando os dados de login vazios
    Map<String, String> loginData = new HashMap<>();
    loginData.put("email", "");
    loginData.put("senha", "");

    // 📌 Chamada real do método de login
    ResponseEntity<LoginResponse> loginResponse = controladorUsuario.login(loginData);

    // 🔍 Depuração extra
    System.out.println("Resposta HTTP: " + loginResponse.getStatusCode());
    if (loginResponse.getBody() != null) {
        System.out.println("Token recebido: " + loginResponse.getBody().getToken());
        System.out.println("ID do Usuário: " + loginResponse.getBody().getIdUsuario());
    } else {
        System.out.println("Resposta sem corpo!");
    }

    // ✅ Verificações
    assertNotNull(loginResponse, "A resposta HTTP está nula!");
    assertEquals(401, loginResponse.getStatusCode().value(), "Código de status incorreto!");

    // 🔍 Verificando se o corpo da resposta não é nulo antes de acessar seus métodos
    assertNotNull(loginResponse.getBody(), "O corpo da resposta está nulo!");
    
    // ✅ Validações com `assertNull()`
    assertNull(loginResponse.getBody().getToken(), "O token deveria ser nulo!");
    assertEquals(0, loginResponse.getBody().getIdUsuario(), "O ID do usuário deveria ser 0!");
}

}



