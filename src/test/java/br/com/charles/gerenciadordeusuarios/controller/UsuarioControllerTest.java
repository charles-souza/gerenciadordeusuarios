package br.com.charles.gerenciadordeusuarios.controller;

import br.com.charles.gerenciadordeusuarios.dto.UsuarioDTO;
import br.com.charles.gerenciadordeusuarios.service.UsuarioService;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;

import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@WebMvcTest(UsuarioController.class)
class UsuarioControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private UsuarioService usuarioService;

    @Autowired
    private ObjectMapper objectMapper;

    private UsuarioDTO usuarioDTO;

    @BeforeEach
    void setup() {
        usuarioDTO = new UsuarioDTO();
        usuarioDTO.setId(1L);
        usuarioDTO.setNome("Charles");
        usuarioDTO.setEmail("charles@email.com");
    }

    @Test
    void testListarTodos() throws Exception {
        List<UsuarioDTO> usuarios = Arrays.asList(usuarioDTO);
        Mockito.when(usuarioService.listarTodos()).thenReturn(usuarios);

        mockMvc.perform(get("/usuario"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(usuarioDTO.getId()))
                .andExpect(jsonPath("$[0].nome").value(usuarioDTO.getNome()))
                .andExpect(jsonPath("$[0].email").value(usuarioDTO.getEmail()));
    }

    @Test
    void testInserir() throws Exception {
        doNothing().when(usuarioService).inserir(any(UsuarioDTO.class));

        mockMvc.perform(post("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioDTO)))
                .andExpect(status().isOk());
    }

    @Test
    void testAlterar() throws Exception {
        Mockito.when(usuarioService.alterar(any(UsuarioDTO.class))).thenReturn(usuarioDTO);

        mockMvc.perform(put("/usuario")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(usuarioDTO)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(usuarioDTO.getId()))
                .andExpect(jsonPath("$.nome").value(usuarioDTO.getNome()))
                .andExpect(jsonPath("$.email").value(usuarioDTO.getEmail()));
    }

    @Test
    void testExcluir() throws Exception {
        doNothing().when(usuarioService).excluir(1L);

        mockMvc.perform(delete("/usuario/1"))
                .andExpect(status().isOk());
    }
}
