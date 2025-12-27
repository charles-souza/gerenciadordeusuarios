package br.com.charles.gerenciadordeusuarios.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.charles.gerenciadordeusuarios.dto.UserDto;
import br.com.charles.gerenciadordeusuarios.entity.UsuarioEntity;
import br.com.charles.gerenciadordeusuarios.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class UserServiceTest {

  @Mock
  private UsuarioRepository usuarioRepository;

  @InjectMocks
  private UserService userService;

  @BeforeEach
  void setUp() {
    MockitoAnnotations.openMocks(this); // Inicializa os mocks
  }

  @Test
  void listAllDeveRetornarListaDeUsuarios() {
    // Configuração dos mocks
    UsuarioEntity usuario1 = new UsuarioEntity();
    UsuarioEntity usuario2 = new UsuarioEntity();
    when(usuarioRepository.findAll()).thenReturn(List.of(usuario1, usuario2));

    // Chamada do método
    List<UserDto> resultado = userService.listAll();

    // Verificações
    assertEquals(2, resultado.size());
    verify(usuarioRepository, times(1)).findAll();
  }

  @Test
  void inserirDeveSalvarUsuario() {
    // Configuração do mock
    UserDto userDto = new UserDto();
    UsuarioEntity usuarioEntity = new UsuarioEntity(userDto);
    when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

    // Chamada do método
    userService.inserir(userDto);

    // Verificação
    verify(usuarioRepository, times(1)).save(any(UsuarioEntity.class));
  }

  @Test
  void updateDeveAtualizarUsuario() {
    // Configuração do mock
    UserDto userDto = new UserDto();
    UsuarioEntity usuarioEntity = new UsuarioEntity(userDto);
    when(usuarioRepository.save(any(UsuarioEntity.class))).thenReturn(usuarioEntity);

    // Chamada do método
    UserDto resultado = userService.update(userDto);

    // Verificações
    assertNotNull(resultado);
    verify(usuarioRepository, times(1)).save(any(UsuarioEntity.class));
  }

  @Test
  void excluirDeveDeleteUsuario() {
    // Configuração do mock
    Long id = 1L;
    UsuarioEntity usuarioEntity = new UsuarioEntity();
    when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioEntity));

    // Chamada do método
    userService.delete(id);

    // Verificações
    verify(usuarioRepository, times(1)).delete(usuarioEntity);
  }

  @Test
  void deleteDeveLancarExcecaoQuandoUsuarioNaoExistir() {
    // Configuração do mock
    Long id = 1L;
    when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

    // Chamada do método e verificação
    assertThrows(EntityNotFoundException.class, () -> userService.delete(id));
    verify(usuarioRepository, never()).delete(any(UsuarioEntity.class));
  }

  @Test
  void buscarPorIdDeveRetornarUsuario() {
    // Configuração do mock
    Long id = 1L;
    UsuarioEntity usuarioEntity = new UsuarioEntity();
    when(usuarioRepository.findById(id)).thenReturn(Optional.of(usuarioEntity));

    // Chamada do método
    UserDto resultado = userService.buscarporId(id);

    // Verificações
    assertNotNull(resultado);
    verify(usuarioRepository, times(1)).findById(id);
  }

  @Test
  void buscarPorIdDeveLancarExcecaoQuandoUsuarioNaoExistir() {
    // Configuração do mock
    Long id = 1L;
    when(usuarioRepository.findById(id)).thenReturn(Optional.empty());

    // Chamada do método e verificação
    assertThrows(EntityNotFoundException.class, () -> userService.buscarporId(id));
    verify(usuarioRepository, times(1)).findById(id);
  }
}
