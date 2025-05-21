package br.com.charles.gerenciadordeusuarios.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import br.com.charles.gerenciadordeusuarios.dto.UsuarioDTO;
import br.com.charles.gerenciadordeusuarios.entity.UsuarioEntity;
import br.com.charles.gerenciadordeusuarios.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;

	public UsuarioService(UsuarioRepository usuarioRepository) {
	    this.usuarioRepository = usuarioRepository;
	}
	
	public List<UsuarioDTO> listarTodos() {
		List<UsuarioEntity> usuarios = usuarioRepository.findAll();
		return usuarios.stream().map(UsuarioDTO::new).toList();		
	}
	
	public void inserir(UsuarioDTO usuario) {
		UsuarioEntity usuarioEntity = new UsuarioEntity(usuario);
		usuarioRepository.save(usuarioEntity);
	}
	
	public UsuarioDTO alterar(UsuarioDTO usuario) {
		UsuarioEntity usuarioEntity =  new UsuarioEntity(usuario);
		return new UsuarioDTO(usuarioRepository.save(usuarioEntity));		
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

	
	public UsuarioDTO buscarporId(Long id) {
	    Optional<UsuarioEntity> optionalUsuario = usuarioRepository.findById(id);

	    if (optionalUsuario.isPresent()) {
	        return new UsuarioDTO(optionalUsuario.get());
	    } else {
	        // Lidando com o caso em que o usuário não é encontrado
	        throw new EntityNotFoundException("Usuário com ID " + id + " não encontrado.");
	    }
	}


}
