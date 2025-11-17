package ac2_project.example.ac2_ca.controller.test;

import ac2_project.example.ac2_ca.controller.CursoController;
import ac2_project.example.ac2_ca.entity.Curso;
import ac2_project.example.ac2_ca.service.CursoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CursoController.class)
class CursoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CursoService service;

    private Curso curso;

    @BeforeEach
    void setup() {
        curso = new Curso("Java", "Curso Java", "Prof XP");
        curso.setId(1L);
    }

    @Test
    void getAllShouldReturn200() throws Exception {
        when(service.getAll()).thenReturn(List.of(curso));

        mvc.perform(get("/cursos"))
                .andExpect(status().isOk());
    }

    @Test
    void getByIdShouldReturn200() throws Exception {
        when(service.getById(1L)).thenReturn(Optional.of(curso));

        mvc.perform(get("/cursos/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getByIdShouldReturn404() throws Exception {
        when(service.getById(9L)).thenReturn(Optional.empty());

        mvc.perform(get("/cursos/9"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createShouldReturn200() throws Exception {
        when(service.create(Mockito.any())).thenReturn(curso);

        String json = """
                {
                    "titulo": "Java",
                    "descricao": "Curso Java",
                    "professor": "Prof XP",
                    "ativo": true
                }
                """;

        mvc.perform(post("/cursos")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void updateShouldReturn200() throws Exception {
        when(service.update(Mockito.eq(1L), Mockito.any())).thenReturn(Optional.of(curso));

        String json = """
                {
                    "titulo": "Java",
                    "descricao": "Curso Java",
                    "professor": "Prof XP",
                    "ativo": true
                }
                """;

        mvc.perform(put("/cursos/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void deleteShouldReturn204() throws Exception {
        when(service.delete(1L)).thenReturn(true);

        mvc.perform(delete("/cursos/1"))
                .andExpect(status().isNoContent());
    }
}
