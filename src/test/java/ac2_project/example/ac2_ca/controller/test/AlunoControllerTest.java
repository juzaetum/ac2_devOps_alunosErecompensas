package ac2_project.example.ac2_ca.controller.test;

import ac2_project.example.ac2_ca.controller.AlunoController;
import ac2_project.example.ac2_ca.entity.Aluno;
import ac2_project.example.ac2_ca.entity.AlunoRA;
import ac2_project.example.ac2_ca.service.AlunoService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AlunoController.class)
public class AlunoControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private AlunoService alunoService;

    @Test
    public void testGetAllAlunos() throws Exception {
        // cria um aluno de teste
        AlunoRA ra = new AlunoRA("123456");
        Aluno aluno = new Aluno(ra, "Engenharia de Computação", 8.5f);

        // simula o retorno do serviço
        Mockito.when(alunoService.listarTodos()).thenReturn(List.of(aluno));

        // executa a requisição GET
        mockMvc.perform(MockMvcRequestBuilders.get("/alunos")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].curso").value("Engenharia de Computação"))
                .andExpect(jsonPath("$[0].media").value(8.5));
    }
}
