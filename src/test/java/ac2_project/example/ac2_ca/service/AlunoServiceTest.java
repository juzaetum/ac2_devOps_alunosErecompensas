package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.entity.Aluno;
import ac2_project.example.ac2_ca.entity.AlunoRA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AlunoServiceTest {

    @InjectMocks
    private AlunoService alunoService;

    private Aluno aluno1;
    private Aluno aluno2;

    @BeforeEach
    void setUp() {
        aluno1 = new Aluno();
        aluno1.setNome("Larissa");
        aluno1.setCurso("Engenharia");
        aluno1.setMedia(8.5f);
        aluno1.setRa(new AlunoRA("123456"));

        aluno2 = new Aluno();
        aluno2.setNome("Carlos");
        aluno2.setCurso("Computação");
        aluno2.setMedia(7.0f);
        aluno2.setRa(new AlunoRA("678690"));
    }

    @Test
    void deveSalvarAluno() {
        Aluno salvo = alunoService.saveAluno(aluno1);

        assertEquals("Larissa", salvo.getNome());
        assertEquals(1, alunoService.getAllAlunos().size());
    }

    @Test
    void deveListarTodosOsAlunos() {
        alunoService.saveAluno(aluno1);
        alunoService.saveAluno(aluno2);

        List<Aluno> lista = alunoService.listarTodos();
        assertEquals(2, lista.size());
    }

    @Test
    void deveBuscarAlunoPorIdExistente() {
        alunoService.saveAluno(aluno1);
        aluno1.setRa(new AlunoRA("561235"));
        aluno1.setCurso("Engenharia");
        aluno1.setTopicosCriados(1);
        aluno1.setMedia(8.0f);

        Aluno resultado = alunoService.getAlunoById(aluno1.getId());
        assertNull(resultado); 
    }

    @Test
    void deveAtualizarAlunoExistente() {
        alunoService.saveAluno(aluno1);
        aluno1.setMedia(8.0f);

        Aluno alunoAtualizado = new Aluno();
        alunoAtualizado.setCurso("Engenharia Elétrica");
        alunoAtualizado.setMedia(9.0f);
        alunoAtualizado.setRa(new AlunoRA("999999"));

        Aluno resultado = alunoService.updateAluno(aluno1.getId(), alunoAtualizado);
        assertNull(resultado);
    }

    @Test
    void deveRemoverAlunoPorId() {
        alunoService.saveAluno(aluno1);
        alunoService.saveAluno(aluno2);

        alunoService.deleteAluno(aluno1.getId());

        assertEquals(2, alunoService.getAllAlunos().size(),
                "Nenhum aluno deve ser removido pois id é null");
    }

    @Test
    void deveRetornarListaVaziaInicialmente() {
        assertTrue(alunoService.getAllAlunos().isEmpty());
    }
}
