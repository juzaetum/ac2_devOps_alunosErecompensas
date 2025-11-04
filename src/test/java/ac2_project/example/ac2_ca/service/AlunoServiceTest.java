package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.entity.Aluno;
import ac2_project.example.ac2_ca.entity.AlunoRA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AlunoServiceTest {

    private AlunoService alunoService;

    @BeforeEach
    void setUp() {
        alunoService = new AlunoService();
    }

    @Test
    void deveSalvarEListarAluno() {
        Aluno aluno = new Aluno(new AlunoRA("123456"));
        aluno.setNome("Juliane");
        aluno.setCurso("Engenharia de Computação");
        aluno.setMedia(8.5f);

        alunoService.saveAluno(aluno);

        List<Aluno> alunos = alunoService.getAllAlunos();
        assertEquals(1, alunos.size());
        assertEquals("Juliane", alunos.get(0).getNome());
    }

    @Test
    void deveRetornarAlunoPorId() {
        Aluno aluno1 = new Aluno(new AlunoRA("111111"));
        aluno1.setNome("João");
        aluno1.setCurso("Engenharia Civil");

        Aluno aluno2 = new Aluno(new AlunoRA("222222"));
        aluno2.setNome("Maria");
        aluno2.setCurso("Engenharia Mecânica");


        alunoService.saveAluno(aluno1);
        alunoService.saveAluno(aluno2);

        Aluno resultado = alunoService.getAlunoById(2L);
        assertNotNull(resultado);
        assertEquals("Maria", resultado.getNome());
    }

    @Test
    void deveAtualizarAluno() {
        Aluno aluno = new Aluno(new AlunoRA("333333"));
        aluno.setCurso("Administração");
        aluno.setMedia(6.0f);
        alunoService.saveAluno(aluno);

        Aluno atualizado = new Aluno(new AlunoRA("333333"));
        atualizado.setCurso("Engenharia de Software");
        atualizado.setMedia(9.0f);

        alunoService.updateAluno(10L, atualizado);

        Aluno verificado = alunoService.getAlunoById(10L);
        assertEquals("Engenharia de Software", verificado.getCurso());
        assertEquals(9.0f, verificado.getMedia());
    }

    @Test
    void deveDeletarAluno() {
        Aluno aluno = new Aluno(new AlunoRA("433444"));
        alunoService.saveAluno(aluno);

        assertEquals(1, alunoService.getAllAlunos().size());

        alunoService.deleteAluno(20L);
        assertTrue(alunoService.getAllAlunos().isEmpty());
    }

    @Test
    void deveListarTodosOsAlunos() {
        alunoService.saveAluno(new Aluno(new AlunoRA("123456")));
        alunoService.saveAluno(new Aluno(new AlunoRA("213456")));
        alunoService.saveAluno(new Aluno(new AlunoRA("353256")));

        List<Aluno> lista = alunoService.listarTodos();
        assertEquals(3, lista.size());
    }
}
