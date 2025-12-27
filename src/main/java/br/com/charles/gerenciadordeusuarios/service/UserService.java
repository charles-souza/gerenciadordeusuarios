package br.com.charles.gerenciadordeusuarios.service;

import br.com.charles.gerenciadordeusuarios.dto.UserDto;
import br.com.charles.gerenciadordeusuarios.entity.UsuarioEntity;
import br.com.charles.gerenciadordeusuarios.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UsuarioRepository usuarioRepository;

  public UserService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public List<UserDto> listAll() {
    List<UsuarioEntity> usuarios = usuarioRepository.findAll();
    return usuarios.stream().map(UserDto::new).toList();
  }

  public void inserir(UserDto usuario) {
    UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
    usuarioRepository.save(usuarioEntity);
  }

  public UserDto update(UserDto usuario) {
    UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
    return new UserDto(usuarioRepository.save(usuarioEntity));
  }

  public void delete(Long id) {
    Optional<UsuarioEntity> optionalUsuario = usuarioRepository.findById(id);

    if (optionalUsuario.isPresent()) {
      UsuarioEntity usuario = optionalUsuario.get();
      // Lógica para exclusão do usuário
      usuarioRepository.delete(usuario);
    } else {
      // Lidando com o caso em que o usuário não é encontrado
      throw new EntityNotFoundException("Usuário com ID " + id + " não encontrado.");
    }
  }


  public UserDto buscarporId(Long id) {
    Optional<UsuarioEntity> optionalUsuario = usuarioRepository.findById(id);

    if (optionalUsuario.isPresent()) {
      return new UserDto(optionalUsuario.get());
    } else {
      // Lidando com o caso em que o usuário não é encontrado
      throw new EntityNotFoundException("Usuário com ID " + id + " não encontrado.");
    }
  }


}
