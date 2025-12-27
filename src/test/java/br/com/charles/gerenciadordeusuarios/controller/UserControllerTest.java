package br.com.charles.gerenciadordeusuarios.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.charles.gerenciadordeusuarios.dto.UserDto;
import br.com.charles.gerenciadordeusuarios.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;


@WebMvcTest(UserController.class)
class UserControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockitoBean
  private UserService userService;

  @Autowired
  private ObjectMapper objectMapper;

  private UserDto userDto;

  @BeforeEach
  void setup() {
    userDto = new UserDto();
    userDto.setId(1L);
    userDto.setNome("Charles");
    userDto.setEmail("charles@email.com");
  }

  @Test
  void testListAll() throws Exception {
    List<UserDto> usuarios = Collections.singletonList(userDto);
    Mockito.when(userService.listAll()).thenReturn(usuarios);

    mockMvc.perform(get("/usuario"))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$[0].id").value(userDto.getId()))
        .andExpect(jsonPath("$[0].nome").value(userDto.getNome()))
        .andExpect(jsonPath("$[0].email").value(userDto.getEmail()));
  }

  @Test
  void testInsert() throws Exception {
    doNothing().when(userService).inserir(any(UserDto.class));

    mockMvc.perform(post("/usuario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userDto)))
        .andExpect(status().isOk());
  }

  @Test
  void testUpdate() throws Exception {
    Mockito.when(userService.update(any(UserDto.class))).thenReturn(userDto);

    mockMvc.perform(put("/usuario")
            .contentType(MediaType.APPLICATION_JSON)
            .content(objectMapper.writeValueAsString(userDto)))
        .andExpect(status().isOk())
        .andExpect(jsonPath("$.id").value(userDto.getId()))
        .andExpect(jsonPath("$.nome").value(userDto.getNome()))
        .andExpect(jsonPath("$.email").value(userDto.getEmail()));
  }

  @Test
  void testDelete() throws Exception {
    doNothing().when(userService).delete(1L);

    mockMvc.perform(delete("/usuario/1"))
        .andExpect(status().isOk());
  }
}
