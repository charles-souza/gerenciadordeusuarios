package br.com.charles.gerenciadordeusuarios.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import br.com.charles.gerenciadordeusuarios.dto.UsuarioDTO;
import br.com.charles.gerenciadordeusuarios.entity.UsuarioEntity;
import br.com.charles.gerenciadordeusuarios.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

class UsuarioServiceTest {

    @Mock
    private UsuarioRepository usuarioRepository;

    @InjectMocks
    private UsuarioService usuarioService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this); // Inicializa os mocks
    }

    @Test
    void listarTodos_DeveRetornarListaDeUsuarios() {
        // Configuração dos mocks
        UsuarioEntity usuario1 = new UsuarioEntity();
        UsuarioEntity usuario2 = new UsuarioEntity();
        when(usuarioRepository.findAll()).thenReturn(List.of(usuario1, usuario2));

        // Chamada do método
        List<UsuarioDTO> resultado = usuarioService.listarTodos();

        // Verificações
        assertEquals(2, resultado.size());
        verify(usuarioRepository, times(1)).findAll();
    }

    @Test
    void inserir_DeveSalvarUsuario() {
        // Configuração do mock
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuarioDTO);
        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        // Chamada do método
        usuarioService.inserir(usuarioDTO);

        // Verificação
        verify(usuarioRepository, times(1)).save(any(UsuarioEntity.class));
    }

    @Test
    void alterar_DeveAtualizarUsuario() {
        // Configuração do mock
        UsuarioDTO usuarioDTO = new UsuarioDTO();
        UsuarioEntity usuarioEntity = new UsuarioEntity(usuarioDTO);
        when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

        // Chamada do método
        UsuarioDTO resultado = usuarioService.alterar(usuarioDTO);

        // Verificações
        assertNotNull(resultado);
        verify(usuarioRepository, times(1)).save(any(UsuarioEntity.class));
    }

    @Test
    void excluir_DeveExcluirUsuario() {
        // Configuração do mock
        Long id = 1L;
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioEntity));

        // Chamada do método
        usuarioService.excluir(id);

        // Verificações
        verify(usuarioRepository, times(1)).delete(usuarioEntity);
    }

    @Test
    void excluir_DeveLancarExcecaoQuandoUsuarioNaoExistir() {
        // Configuração do mock
        Long id = 1L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        // Chamada do método e verificação
        assertThrows(EntityNotFoundException.class, () -> usuarioService.excluir(id));
        verify(usuarioRepository, never()).delete(any(UsuarioEntity.class));
    }

    @Test
    void buscarPorId_DeveRetornarUsuario() {
        // Configuração do mock
        Long id = 1L;
        UsuarioEntity usuarioEntity = new UsuarioEntity();
        when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioEntity));

        // Chamada do método
        UsuarioDTO resultado = usuarioService.buscarporId(id);

        // Verificações
        assertNotNull(resultado);
        verify(usuarioRepository, times(1)).findById(id);
    }

    @Test
    void buscarPorId_DeveLancarExcecaoQuandoUsuarioNaoExistir() {
        // Configuração do mock
        Long id = 1L;
        when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

        // Chamada do método e verificação
        assertThrows(EntityNotFoundException.class, () -> usuarioService.buscarporId(id));
        verify(usuarioRepository, times(1)).findById(id);
    }
}
