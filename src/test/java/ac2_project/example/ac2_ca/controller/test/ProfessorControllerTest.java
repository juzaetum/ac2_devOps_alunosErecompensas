package ac2_project.example.ac2_ca.controller.test;

import ac2_project.example.ac2_ca.controller.ProfessorController;
import ac2_project.example.ac2_ca.dto.ProfessorDTO;
import ac2_project.example.ac2_ca.entity.Instituicao;
import ac2_project.example.ac2_ca.entity.InstituicaoCNPJ;
import ac2_project.example.ac2_ca.entity.Professor;
import ac2_project.example.ac2_ca.entity.Professor_RA;
import ac2_project.example.ac2_ca.service.ProfessorService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(controllers = ProfessorController.class)
class ProfessorControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ProfessorService professorService;

    @Autowired
    private ObjectMapper objectMapper;

    private Instituicao inst;
    private Professor prof;

    @BeforeEach
    void setUp() {
        inst = new Instituicao("Fatec", "Rua", new InstituicaoCNPJ("12345678000190"));
        inst.setId(7L);
        prof = new Professor(new Professor_RA("123456"), inst);
        prof.setId(3L);
    }

    @Test
    void getAllShouldReturnList() throws Exception {
        when(professorService.getAll()).thenReturn(List.of(prof));

        mockMvc.perform(get("/professores"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(3));
    }

    @Test
    void getByIdFound() throws Exception {
        when(professorService.getById(3L)).thenReturn(Optional.of(prof));

        mockMvc.perform(get("/professores/3"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(3))
                .andExpect(jsonPath("$.instituicao.nome").value("Fatec"));
    }

    @Test
    void getByIdNotFound() throws Exception {
        when(professorService.getById(99L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/professores/99"))
                .andExpect(status().isNotFound());
    }

    @Test
    void createShouldReturnOkAndProfessor() throws Exception {
        ProfessorDTO dto = new ProfessorDTO(null, "222222", inst.getId());
        Professor created = new Professor(new Professor_RA("222222"), inst);
        created.setId(11L);

        when(professorService.create(Mockito.any(ProfessorDTO.class))).thenReturn(created);

        mockMvc.perform(post("/professores")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(11))
                .andExpect(jsonPath("$.ra.codigo").value("222222"));
    }

    @Test
    void updateWhenFound() throws Exception {
        ProfessorDTO dto = new ProfessorDTO(null, "333333", inst.getId());
        Professor updated = new Professor(new Professor_RA("333333"), inst);
        updated.setId(3L);

        when(professorService.update(eq(3L), any(ProfessorDTO.class))).thenReturn(Optional.of(updated));

        mockMvc.perform(put("/professores/3")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.ra.codigo").value("333333"));
    }

    @Test
    void updateWhenNotFound() throws Exception {
        ProfessorDTO dto = new ProfessorDTO(null, "333333", inst.getId());
        when(professorService.update(eq(99L), any(ProfessorDTO.class))).thenReturn(Optional.empty());

        mockMvc.perform(put("/professores/99")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isNotFound());
    }

    @Test
    void deleteWhenExists() throws Exception {
        when(professorService.delete(3L)).thenReturn(true);

        mockMvc.perform(delete("/professores/3"))
                .andExpect(status().isNoContent());
    }

    @Test
    void deleteWhenNotExists() throws Exception {
        when(professorService.delete(99L)).thenReturn(false);

        mockMvc.perform(delete("/professores/99"))
                .andExpect(status().isNotFound());
    }
}
