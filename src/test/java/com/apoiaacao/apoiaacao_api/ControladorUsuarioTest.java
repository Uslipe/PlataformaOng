package com.apoiaacao.apoiaacao_api;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import com.apoiaacao.apoiaacao_api.controller.Controlador_Usuario;
import com.apoiaacao.apoiaacao_api.model.TipoDeUsuario;
import com.apoiaacao.apoiaacao_api.model.Usuario;
import com.apoiaacao.apoiaacao_api.repositories.Repositorio_Usuario;
import com.apoiaacao.apoiaacao_api.service.EmailService;
import com.apoiaacao.apoiaacao_api.service.UsuarioService;

@SpringBootTest
public class ControladorUsuarioTest {

    @Mock
    private Repositorio_Usuario repositorioUsuario;

    @Mock
    private UsuarioService usuarioService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private Controlador_Usuario controladorUsuario;

    private Usuario usuario;

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
    public void testBuscarUsuario() {
        when(repositorioUsuario.findById(anyInt())).thenReturn(Optional.of(usuario));

        Usuario resultado = controladorUsuario.buscarUsuario(1).getBody();

        assertNotNull(resultado);
        assertEquals(usuario.getId(), resultado.getId());
        assertEquals(usuario.getNome(), resultado.getNome());
        assertEquals(usuario.getEmail(), resultado.getEmail());
    }

    @Test
    public void testEditarPerfil() {
        when(repositorioUsuario.findById(anyInt())).thenReturn(Optional.of(usuario));
        when(repositorioUsuario.save(any(Usuario.class))).thenReturn(usuario);

        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNome("Usuario Atualizado");
        usuarioAtualizado.setEmail("atualizado@example.com");

        Usuario resultado = controladorUsuario.editarPerfil(1, usuarioAtualizado).getBody();

        assertNotNull(resultado);
        assertEquals(usuarioAtualizado.getNome(), resultado.getNome());
        assertEquals(usuarioAtualizado.getEmail(), resultado.getEmail());
    }

    @Test
    public void testDeletarUsuario() {
        when(repositorioUsuario.findById(anyInt())).thenReturn(Optional.of(usuario));

        Usuario resultado = controladorUsuario.deletarUsuario(1).getBody();

        assertNotNull(resultado);
        assertEquals(usuario.getId(), resultado.getId());
        verify(repositorioUsuario).delete(usuario);
    }

}