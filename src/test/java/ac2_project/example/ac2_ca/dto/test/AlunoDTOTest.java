package ac2_project.example.ac2_ca.dto.test;

import org.junit.jupiter.api.Test;

import ac2_project.example.ac2_ca.dto.*;

import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

class AlunoDTOTest {

    @Test
    void deveCriarAlunoDTOComBuilder() {
        RecompensaDTO recompensa = RecompensaDTO.builder()
                .tipo("Cursos Extras")
                .descricao("Ganhou acesso a 2 novos cursos")
                .build();

        AlunoDTO aluno = AlunoDTO.builder()
                .id(1L)
                .nome("Juliane Zaetum")
                .ra("123456")
                .curso("Engenharia de Computação")
                .rendimento(8.5)
                .numeroDeCursos(3)
                .recompensas(List.of(recompensa))
                .build();

        assertEquals("Juliane Zaetum", aluno.getNome());
        assertEquals("123456", aluno.getRa());
        assertEquals(8.5, aluno.getRendimento());
        assertEquals(1, aluno.getRecompensas().size());
        assertEquals("Cursos Extras", aluno.getRecompensas().get(0).getTipo());
    }

    @Test
    void devePermitirSettersEGetters() {
        AlunoDTO aluno = new AlunoDTO();
        aluno.setNome("Juliane");
        aluno.setCurso("Engenharia");

        assertEquals("Juliane", aluno.getNome());
        assertEquals("Engenharia", aluno.getCurso());
    }

    @Test
    void deveGerarToStringENaoSerNulo() {
        AlunoDTO aluno = new AlunoDTO();
        assertNotNull(aluno.toString());
    }
}
