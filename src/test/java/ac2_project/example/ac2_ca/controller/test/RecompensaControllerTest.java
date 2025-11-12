package ac2_project.example.ac2_ca.controller.test;

import ac2_project.example.ac2_ca.controller.RecompensaController;
import ac2_project.example.ac2_ca.entity.Recompensa;
import ac2_project.example.ac2_ca.service.RecompensaService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RecompensaController.class)
public class RecompensaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RecompensaService recompensaService;

    @Autowired
    private ObjectMapper objectMapper;

    private Recompensa recompensa1;
    private Recompensa recompensa2;

    @BeforeEach
    void setup() {
        recompensa1 = new Recompensa(1L, 100.0f, "Medalha Ouro", "Engenharia");
        recompensa2 = new Recompensa(2L, 50.0f, "Medalha Prata", "Computação");
    }

    // GET - todas
    @Test
    void deveRetornarTodasAsRecompensas() throws Exception {
        List<Recompensa> lista = Arrays.asList(recompensa1, recompensa2);
        Mockito.when(recompensaService.getAllRecompensas()).thenReturn(lista);

        mockMvc.perform(get("/recompensas"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Medalha Ouro"))
                .andExpect(jsonPath("$[1].curso_titulo").value("Computação"));
    }

    // GET - por ID
    @Test
    void deveRetornarRecompensaPorId() throws Exception {
        Mockito.when(recompensaService.getRecompensaById(1L)).thenReturn(Optional.of(recompensa1));

        mockMvc.perform(get("/recompensas/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Medalha Ouro"));
    }

    @Test
    void deveRetornar404SeNaoEncontrarRecompensa() throws Exception {
        Mockito.when(recompensaService.getRecompensaById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/recompensas/99"))
                .andExpect(status().isNotFound());
    }

    // POST - cria
    @Test
    void deveCriarNovaRecompensa() throws Exception {
        Mockito.when(recompensaService.createRecompensa(any(Recompensa.class))).thenReturn(recompensa1);

        mockMvc.perform(post("/recompensas")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recompensa1)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.nome").value("Medalha Ouro"));
    }

    // PUT - atualiza
    @Test
    void deveAtualizarRecompensaExistente() throws Exception {
        Recompensa atualizada = new Recompensa(1L, 150.0f, "Medalha Diamante", "Engenharia");
        Mockito.when(recompensaService.updateRecompensa(eq(1L), any(Recompensa.class)))
                .thenReturn(Optional.of(atualizada));

        mockMvc.perform(put("/recompensas/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(atualizada)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Medalha Diamante"));
    }

    @Test
    void deveRetornar404AoAtualizarInexistente() throws Exception {
        Mockito.when(recompensaService.updateRecompensa(eq(99L), any(Recompensa.class)))
                .thenReturn(Optional.empty());

        mockMvc.perform(put("/recompensas/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(recompensa1)))
                .andExpect(status().isNotFound());
    }

    // DELETE
    @Test
    void deveExcluirRecompensa() throws Exception {
        Mockito.when(recompensaService.deleteRecompensa(1L)).thenReturn(true);

        mockMvc.perform(delete("/recompensas/1"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deveRetornar404AoExcluirInexistente() throws Exception {
        Mockito.when(recompensaService.deleteRecompensa(99L)).thenReturn(false);

        mockMvc.perform(delete("/recompensas/99"))
                .andExpect(status().isNotFound());
    }
}
