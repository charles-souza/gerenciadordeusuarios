package br.com.charles.gerenciadordeusuarios.service;

import br.com.charles.gerenciadordeusuarios.dto.UsuarioDto;
import br.com.charles.gerenciadordeusuarios.entity.UsuarioEntity;
import br.com.charles.gerenciadordeusuarios.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class UsuarioService {

  private final UsuarioRepository usuarioRepository;

  public UsuarioService(UsuarioRepository usuarioRepository) {
    this.usuarioRepository = usuarioRepository;
  }

  public List<UsuarioDto> listarTodos() {
    List<UsuarioEntity> usuarios = usuarioRepository.findAll();
    return usuarios.stream().map(UsuarioDto::new).toList();
  }

  public void inserir(UsuarioDto usuario) {
    UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
    usuarioRepository.save(usuarioEntity);
  }

  public UsuarioDto alterar(UsuarioDto usuario) {
    UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
    return new UsuarioDto(usuarioRepository.save(usuarioEntity));
  }

  public void excluir(Long id) {
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


  public UsuarioDto buscarporId(Long id) {
    Optional<UsuarioEntity> optionalUsuario = usuarioRepository.findById(id);

    if (optionalUsuario.isPresent()) {
      return new UsuarioDto(optionalUsuario.get());
    } else {
      // Lidando com o caso em que o usuário não é encontrado
      throw new EntityNotFoundException("Usuário com ID " + id + " não encontrado.");
    }
  }


}
