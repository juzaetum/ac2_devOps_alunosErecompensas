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

    // -------------------- TESTES POSITIVOS --------------------

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
        Aluno resultado = alunoService.getAlunoById(1L);
        assertNotNull(resultado);
        assertEquals("Larissa", resultado.getNome());
    }

    @Test
    void deveAtualizarAlunoExistente() {
        alunoService.saveAluno(aluno1);
        Aluno atualizado = new Aluno();
        atualizado.setCurso("Engenharia Elétrica");
        atualizado.setMedia(9.0f);
        atualizado.setRa(new AlunoRA("999999"));

        Aluno resultado = alunoService.updateAluno(1L, atualizado);

        assertNotNull(resultado);
        assertEquals("Engenharia Elétrica", resultado.getCurso());
        assertEquals(9.0f, resultado.getMedia());
        assertEquals("999999", resultado.getRa().toString());
    }

    @Test
    void deveRemoverAlunoPorId() {
        alunoService.saveAluno(aluno1);
        alunoService.saveAluno(aluno2);

        alunoService.deleteAluno(1L);

        List<Aluno> lista = alunoService.getAllAlunos();
        assertEquals(1, lista.size());
        assertEquals("Carlos", lista.get(0).getNome());
    }

    @Test
    void deveRetornarListaVaziaInicialmente() {
        assertTrue(alunoService.getAllAlunos().isEmpty());
    }

    // -------------------- TESTES NEGATIVOS / COBERTURA EXTRA --------------------

    @Test
    void deveRetornarNullAoBuscarComIdNulo() {
        Aluno resultado = alunoService.getAlunoById(null);
        assertNull(resultado);
    }

    @Test
    void deveRetornarNullAoAtualizarComIdNulo() {
        alunoService.saveAluno(aluno1);
        Aluno atualizado = new Aluno();
        atualizado.setRa(new AlunoRA("111111"));

        Aluno resultado = alunoService.updateAluno(null, atualizado);
        assertNull(resultado);
    }

    @Test
    void deveRetornarNullAoAtualizarComAlunoNulo() {
        alunoService.saveAluno(aluno1);
        Aluno resultado = alunoService.updateAluno(1L, null);
        assertNull(resultado);
    }

    @Test
    void deveRetornarNullAoAtualizarAlunoInexistente() {
        alunoService.saveAluno(aluno1);
        Aluno novo = new Aluno();
        novo.setRa(new AlunoRA("888888"));

        Aluno resultado = alunoService.updateAluno(99L, novo);
        assertNull(resultado);
    }

    @Test
    void deveLidarComRemocaoComIdNuloSemErro() {
        alunoService.saveAluno(aluno1);
        alunoService.deleteAluno(null);
        assertEquals(1, alunoService.getAllAlunos().size());
    }

    @Test
    void deveLancarExcecaoAoSalvarAlunoNulo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> alunoService.saveAluno(null));
        assertEquals("Aluno não pode ser nulo", ex.getMessage());
    }

    @Test
    void deveSubstituirRaInvalidoPorPadraoDuranteValidacao() {
        Aluno aluno = new Aluno();
        aluno.setRa(new AlunoRA("000000"));

        aluno.setNome("Teste");
        aluno.setCurso("ADS");
        aluno.setMedia(6.5f);

        // forçar um RA inválido simulando o retorno do toString()
        aluno.setRa(new AlunoRA("12")); // menos de 6 dígitos

        Aluno salvo = alunoService.saveAluno(aluno);
        assertEquals("123456", salvo.getRa().toString());
    }

    @Test
    void deveDefinirRaPadraoQuandoRaForNulo() {
        Aluno aluno = new Aluno();
        aluno.setNome("Sem RA");
        aluno.setCurso("Computação");
        aluno.setMedia(5.0f);
        aluno.setRa(null);

        alunoService.saveAluno(aluno);

        assertEquals("123456", aluno.getRa().toString());
    }
}
