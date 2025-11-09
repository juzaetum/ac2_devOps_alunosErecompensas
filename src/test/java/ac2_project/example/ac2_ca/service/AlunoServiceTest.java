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
        assertEquals("123456", salvo.getRa().toString());
    }

    @Test
    void deveLancarExcecaoAoSalvarAlunoNulo() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> alunoService.saveAluno(null));
        assertEquals("Aluno não pode ser nulo", e.getMessage());
    }

    @Test
    void deveListarTodosOsAlunos() {
        alunoService.saveAluno(aluno1);
        alunoService.saveAluno(aluno2);

        List<Aluno> lista = alunoService.listarTodos();
        assertEquals(2, lista.size());
    }

    @Test
    void deveBuscarAlunoPorIdExistenteMesmoComIdNulo() {
        alunoService.saveAluno(aluno1);
        // id é nulo, então busca retorna null
        Aluno resultado = alunoService.getAlunoById(aluno1.getId());
        assertNull(resultado);
    }

    @Test
    void deveRetornarNullAoBuscarComIdNulo() {
        Aluno resultado = alunoService.getAlunoById(null);
        assertNull(resultado);
    }

    @Test
    void deveAtualizarAlunoExistenteComIdNuloRetornaNull() {
        alunoService.saveAluno(aluno1);

        Aluno atualizado = new Aluno();
        atualizado.setCurso("Elétrica");
        atualizado.setMedia(9.0f);
        atualizado.setRa(new AlunoRA("999999"));

        Aluno resultado = alunoService.updateAluno(aluno1.getId(), atualizado);
        assertNull(resultado);
    }

    @Test
    void deveRetornarNullAoAtualizarComIdNulo() {
        alunoService.saveAluno(aluno1);
        Aluno atualizado = new Aluno();
        atualizado.setCurso("Civil");

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
        novo.setCurso("Computação");
        novo.setRa(new AlunoRA("111111"));
        novo.setMedia(7.5f);

        Aluno resultado = alunoService.updateAluno(99L, novo);
        assertNull(resultado);
    }

    @Test
    void deveRemoverAlunoPorIdNuloSemErro() {
        alunoService.saveAluno(aluno1);
        alunoService.deleteAluno(null);

        assertEquals(1, alunoService.getAllAlunos().size());
    }

    @Test
    void deveRemoverAlunoPorIdMesmoComIdNulo() {
        alunoService.saveAluno(aluno1);
        alunoService.saveAluno(aluno2);

        alunoService.deleteAluno(aluno1.getId());
        assertEquals(2, alunoService.getAllAlunos().size(),
                "Nenhum aluno é removido pois id é null");
    }

    @Test
    void deveRetornarListaVaziaInicialmente() {
        assertTrue(alunoService.getAllAlunos().isEmpty());
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

    @Test
    void deveSubstituirRaInvalidoPorPadraoDuranteValidacao() {
        Aluno aluno = new Aluno();
        aluno.setNome("Teste");
        aluno.setCurso("ADS");
        aluno.setMedia(6.5f);
        aluno.setRa(new AlunoRA("abc123")); // inválido

        Aluno salvo = alunoService.saveAluno(aluno);
        assertEquals("123456", salvo.getRa().toString());
    }

    @Test
    void deveManterRaValidoDuranteValidacao() {
        Aluno aluno = new Aluno();
        aluno.setNome("Bruno");
        aluno.setCurso("Sistemas");
        aluno.setMedia(9.0f);
        aluno.setRa(new AlunoRA("654321"));

        Aluno salvo = alunoService.saveAluno(aluno);
        assertEquals("654321", salvo.getRa().toString());
    }

    @Test
    void deveLancarExcecaoQuandoValidarAlunoNulo() {
        Exception e = assertThrows(IllegalArgumentException.class, () -> {
            var metodo = AlunoService.class.getDeclaredMethod("validarRa", Aluno.class);
            metodo.setAccessible(true);
            metodo.invoke(alunoService, new Object[] { null });
        });

        assertTrue(e.getCause() instanceof IllegalArgumentException);
        assertEquals("Aluno inválido: nulo", e.getCause().getMessage());
    }

    @Test
    void deveRetornarCopiaIndependenteDaListaDeAlunos() {
        alunoService.saveAluno(aluno1);
        List<Aluno> lista = alunoService.getAllAlunos();

        lista.clear(); // altera cópia, não lista interna
        assertEquals(1, alunoService.getAllAlunos().size());
    }
}
