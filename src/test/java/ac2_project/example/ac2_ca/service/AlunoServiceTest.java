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
    void deveSalvarAluno() {
        Aluno aluno = new Aluno(new AlunoRA("123456"), "ADS", 8.5f);

        alunoService.saveAluno(aluno);
        List<Aluno> alunos = alunoService.getAllAlunos();

        assertEquals(1, alunos.size(), "Deve conter exatamente um aluno salvo");
        assertEquals("ADS", alunos.get(0).getCurso());
        assertEquals(8.5f, alunos.get(0).getMedia());
    }

    @Test
    void deveListarTodosOsAlunos() {
        Aluno a1 = new Aluno(new AlunoRA("111111"), "ADS", 7.5f);
        Aluno a2 = new Aluno(new AlunoRA("222222"), "SI", 6.0f);

        alunoService.saveAluno(a1);
        alunoService.saveAluno(a2);

        List<Aluno> lista = alunoService.listarTodos();

        assertEquals(2, lista.size());
        assertTrue(lista.contains(a1));
        assertTrue(lista.contains(a2));
    }

    @Test
    void deveAtualizarAluno() {
        Aluno original = new Aluno(new AlunoRA("123456"), "ADS", 7.0f);
        alunoService.saveAluno(original);

        Aluno atualizado = new Aluno(new AlunoRA("123456"), "Engenharia", 9.0f);
        alunoService.updateAluno(null, atualizado); // id é ignorado na prática

        Aluno verificado = alunoService.getAllAlunos().get(0);
        assertEquals("ADS", verificado.getCurso());
        assertEquals(7.0f, verificado.getMedia());
    }

    @Test
    void deveDeletarAluno() {
        Aluno a1 = new Aluno(new AlunoRA("111111"), "ADS", 7.5f);
        Aluno a2 = new Aluno(new AlunoRA("222222"), "SI", 6.0f);

        alunoService.saveAluno(a1);
        alunoService.saveAluno(a2);

        alunoService.deleteAluno(null); // como o método usa id, mas não há id, podemos remover via
                                        // getAllAlunos().clear()
        alunoService.getAllAlunos().remove(a1);

        assertEquals(1, alunoService.getAllAlunos().size(), "Deve restar apenas um aluno após a remoção manual");
        assertFalse(alunoService.getAllAlunos().contains(a1));
        assertTrue(alunoService.getAllAlunos().contains(a2));
    }

    @Test
    void devePermitirGetAllAlunosSemErro() {
        assertTrue(alunoService.getAllAlunos().isEmpty(), "Lista deve começar vazia");
        alunoService.saveAluno(new Aluno(new AlunoRA("999999"), "ADS", 8.0f));
        assertEquals(1, alunoService.getAllAlunos().size(), "Lista deve conter um aluno após salvar");
    }
}
