package ac2_project.example.ac2_ca.controller.test;

import ac2_project.example.ac2_ca.controller.InstituicaoController;
import ac2_project.example.ac2_ca.entity.Instituicao;
import ac2_project.example.ac2_ca.entity.InstituicaoCNPJ;
import ac2_project.example.ac2_ca.service.InstituicaoService;
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

@WebMvcTest(InstituicaoController.class)
class InstituicaoControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private InstituicaoService service;

    private Instituicao inst;

    @BeforeEach
    void setup() {
        inst = new Instituicao("Inst 1", "Rua X", new InstituicaoCNPJ("12345678000190"));
        inst.setId(1L);
    }

    @Test
    void getAllShouldReturn200() throws Exception {
        when(service.getAll()).thenReturn(List.of(inst));

        mvc.perform(get("/instituicoes"))
                .andExpect(status().isOk());
    }

    @Test
    void getByIdShouldReturn200() throws Exception {
        when(service.getById(1L)).thenReturn(Optional.of(inst));

        mvc.perform(get("/instituicoes/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getByIdShouldReturn404() throws Exception {
        when(service.getById(9L)).thenReturn(Optional.empty());

        mvc.perform(get("/instituicoes/9"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createShouldReturn200() throws Exception {
        when(service.create(Mockito.any())).thenReturn(inst);

        String json = """
                {
                  "nome": "Inst 1",
                  "endereco": "Rua X",
                  "cnpj": "12345678000190",
                  "ativa": true
                }
                """;

        mvc.perform(post("/instituicoes")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void updateShouldReturn200() throws Exception {
        when(service.update(Mockito.eq(1L), Mockito.any()))
                .thenReturn(Optional.of(inst));

        String json = """
                {
                  "nome": "Inst 1",
                  "endereco": "Rua X",
                  "cnpj": "12345678000190",
                  "ativa": true
                }
                """;

        mvc.perform(put("/instituicoes/1")
                .contentType(MediaType.APPLICATION_JSON)
                .content(json))
                .andExpect(status().isOk());
    }

    @Test
    void deleteShouldReturn204() throws Exception {
        when(service.delete(1L)).thenReturn(true);

        mvc.perform(delete("/instituicoes/1"))
                .andExpect(status().isNoContent());
    }
}
