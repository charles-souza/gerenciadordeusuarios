package br.com.charles.gerenciadordeusuarios.controller;

import br.com.charles.gerenciadordeusuarios.dto.UsuarioDTO;
import br.com.charles.gerenciadordeusuarios.service.UsuarioService;
import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioController {
  private final UsuarioService usuarioService;

  public UsuarioController(UsuarioService usuarioService) {
    this.usuarioService = usuarioService;
  }

  @GetMapping
  public List<UsuarioDTO> listarTodos() {
    return usuarioService.listarTodos();
  }

  @PostMapping
  public void inserir(@RequestBody UsuarioDTO usuario) {
    usuarioService.inserir(usuario);
  }

  @PutMapping
  public UsuarioDTO alterar(@RequestBody UsuarioDTO usuario) {
    return usuarioService.alterar(usuario);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> excluir(@PathVariable Long id) {
    usuarioService.excluir(id);
    return ResponseEntity.ok().build();
  }

}
