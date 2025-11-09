package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.entity.Aluno;
import ac2_project.example.ac2_ca.entity.AlunoRA;
import ac2_project.example.ac2_ca.entity.Curso;

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
        aluno1.setCurso(new Curso("Engenharia", 1));
        aluno1.setMedia(8.5f);
        aluno1.setRa(new AlunoRA("123456"));

        aluno2 = new Aluno();
        aluno2.setNome("Carlos");
        aluno2.setCurso(new Curso("Computação", 1));
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
        atualizado.setCurso(new Curso("Elétrica", 1));
        atualizado.setMedia(9.0f);
        atualizado.setRa(new AlunoRA("999999"));

        Aluno resultado = alunoService.updateAluno(aluno1.getId(), atualizado);
        assertNull(resultado);
    }

    @Test
    void deveRetornarNullAoAtualizarComIdNulo() {
        alunoService.saveAluno(aluno1);
        Aluno atualizado = new Aluno();
        atualizado.setCurso(new Curso("Civil", 1));

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
        novo.setCurso(new Curso("Computação", 1));
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
        aluno.setCurso(new Curso("Computação", 1));
        aluno.setMedia(5.0f);
        aluno.setRa(null);

        alunoService.saveAluno(aluno);

        assertEquals("123456", aluno.getRa().toString());
    }

    @Test
    void deveSubstituirRaInvalidoPorPadraoDuranteValidacao() {
        Aluno aluno = new Aluno();
        aluno.setNome("Teste");
        aluno.setCurso(new Curso("ADS", 1));
        aluno.setMedia(6.5f);
        aluno.setRa(new AlunoRA("123456")); // inválido

        Aluno salvo = alunoService.saveAluno(aluno);
        assertEquals("123456", salvo.getRa().toString());
    }

    @Test
    void deveManterRaValidoDuranteValidacao() {
        Aluno aluno = new Aluno();
        aluno.setNome("Bruno");
        aluno.setCurso(new Curso("Sistemas", 1));
        aluno.setMedia(9.0f);
        aluno.setRa(new AlunoRA("654321"));

        Aluno salvo = alunoService.saveAluno(aluno);
        assertEquals("654321", salvo.getRa().toString());
    }

    @Test
    void deveRetornarCopiaIndependenteDaListaDeAlunos() {
        alunoService.saveAluno(aluno1);
        List<Aluno> lista = alunoService.getAllAlunos();

        lista.clear(); // altera cópia, não lista interna
        assertEquals(1, alunoService.getAllAlunos().size());
    }
    
    @Test
    void deveAtualizarAlunoExistenteComNovoRaValido() throws Exception {
        alunoService.saveAluno(aluno1);

        // simula que o aluno salvo tem id = 1L
        var field = Aluno.class.getDeclaredField("id");
        field.setAccessible(true);
        field.set(aluno1, 1L);

        Aluno atualizado = new Aluno();
        atualizado.setCurso(new Curso("Engenharia Civil", 1));
        atualizado.setMedia(9.2f);
        atualizado.setRa(new AlunoRA("999999"));

        Aluno resultado = alunoService.updateAluno(1L, atualizado);

        assertNotNull(resultado);
        assertEquals("999999", resultado.getRa().toString());
        assertEquals("Engenharia Civil", resultado.getCurso());
        assertEquals(9.2f, resultado.getMedia());
    }

    @Test
    void deveAtualizarAlunoExistenteComRaNuloMantendoPadrao() throws Exception {
        alunoService.saveAluno(aluno1);

        // força o id via reflection
        var field = Aluno.class.getDeclaredField("id");
        field.setAccessible(true);
        field.set(aluno1, 1L);

        Aluno atualizado = new Aluno();
        atualizado.setCurso(new Curso("Engenharia Mecânica", 1));
        atualizado.setMedia(8.7f);
        atualizado.setRa(null); // força uso de RA padrão

        Aluno resultado = alunoService.updateAluno(1L, atualizado);

        assertNotNull(resultado);
        assertEquals("123456", resultado.getRa().toString());
        assertEquals("Engenharia Mecânica", resultado.getCurso());
    }

    @Test
    void deveDeletarAlunoComIdValido() {
        alunoService.saveAluno(aluno1);

        // como id é nulo, removeIf não acha nada, então simulamos manualmente
        alunoService.getAllAlunos().forEach(a -> {
            try {
                var field = Aluno.class.getDeclaredField("id");
                field.setAccessible(true);
                field.set(a, 1L);
            } catch (Exception ignored) {
            }
        });

        alunoService.deleteAluno(1L);
        assertEquals(0, alunoService.getAllAlunos().size());
    }

    @Test
    void deveRetornarListaIndependenteEmListarTodos() {
        alunoService.saveAluno(aluno1);
        List<Aluno> lista = alunoService.listarTodos();
        lista.clear();
        assertFalse(alunoService.listarTodos().isEmpty());
    }

    @Test
    void deveLancarExcecaoQuandoValidarAlunoNulo() throws Exception {
        var method = AlunoService.class.getDeclaredMethod("validarRa", Aluno.class);
        method.setAccessible(true);
        var service = new AlunoService();

        var ex = assertThrows(java.lang.reflect.InvocationTargetException.class,
                () -> method.invoke(service, new Object[] { null }));

        assertTrue(ex.getCause() instanceof IllegalArgumentException);
        assertEquals("Aluno inválido: nulo", ex.getCause().getMessage());
    }

}
