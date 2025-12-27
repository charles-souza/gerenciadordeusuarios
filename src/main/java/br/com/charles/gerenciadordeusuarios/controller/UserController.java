package br.com.charles.gerenciadordeusuarios.controller;

import br.com.charles.gerenciadordeusuarios.dto.UserDto;
import br.com.charles.gerenciadordeusuarios.service.UserService;
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
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @GetMapping
  public List<UserDto> listAll() {
    return userService.listAll();
  }

  @PostMapping
  public void insert(@RequestBody UserDto user) {
    userService.inserir(user);
  }

  @PutMapping
  public UserDto update(@RequestBody UserDto user) {
    return userService.update(user);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> delete(@PathVariable Long id) {
    userService.delete(id);
    return ResponseEntity.ok().build();
  }

}
