package ac2_project.example.ac2_ca.entity.test;

import ac2_project.example.ac2_ca.entity.Curso;
import ac2_project.example.ac2_ca.entity.CursoAula;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CursoAulaTest {

    private CursoAula aula;
    private Curso cursoMock;

    @BeforeEach
    void setUp() {
        cursoMock = new Curso(); // pode ser um mock simples, sem dependências
        aula = new CursoAula("Introdução ao Spring Boot", 90, "Fundamentos do framework Spring", true);
        aula.setCurso(cursoMock);
    }

    @Test
    void deveCriarAulaComDadosValidos() {
        assertEquals("Introdução ao Spring Boot", aula.getTitulo());
        assertEquals(90, aula.getDuracaoMinutos());
        assertEquals("Fundamentos do framework Spring", aula.getConteudoResumo());
        assertTrue(aula.isObrigatoria());
        assertEquals(cursoMock, aula.getCurso());
    }

    @Test
    void deveLancarErroQuandoTituloForNuloOuVazio() {
        Exception ex1 = assertThrows(IllegalArgumentException.class, () -> new CursoAula(null, 60, "Resumo", true));
        assertEquals("O título da aula não pode estar vazio.", ex1.getMessage());

        Exception ex2 = assertThrows(IllegalArgumentException.class, () -> new CursoAula("   ", 60, "Resumo", true));
        assertEquals("O título da aula não pode estar vazio.", ex2.getMessage());
    }

    @Test
    void deveLancarErroQuandoDuracaoNaoForPositiva() {
        Exception ex = assertThrows(IllegalArgumentException.class,
                () -> new CursoAula("Java Básico", 0, "Resumo", false));
        assertEquals("A duração da aula deve ser positiva.", ex.getMessage());
    }

    @Test
    void devePermitirDefinirCurso() {
        Curso outroCurso = new Curso();
        aula.setCurso(outroCurso);
        assertEquals(outroCurso, aula.getCurso());
    }

    @Test
    void deveGerarToStringCorretamente() {
        String esperado = "Introdução ao Spring Boot (90 min)";
        assertEquals(esperado, aula.toString());
    }

    @Test
    void deveCompararIguaisComMesmoTituloIgnorandoCase() {
        CursoAula outra = new CursoAula("introdução ao spring boot", 120, "Outro resumo", false);
        assertEquals(aula, outra);
        assertEquals(aula.hashCode(), outra.hashCode());
    }

    @Test
    void naoDeveSerIgualAObjetoDeOutroTipoOuTituloDiferente() {
        CursoAula diferente = new CursoAula("Spring Avançado", 90, "Outro conteúdo", true);
        assertNotEquals(aula, diferente);
        assertNotEquals(aula, null);
        assertNotEquals(aula, "string qualquer");
    }

    @Test
    void deveGerarHashCodeZeroQuandoTituloForNulo() {
        CursoAula semTitulo = new CursoAula();
        assertEquals(0, semTitulo.hashCode());
    }

    @Test
    void construtorVazioNaoDeveLancarErro() {
        CursoAula vazio = new CursoAula();
        assertNull(vazio.getTitulo());
        assertEquals(0, vazio.getDuracaoMinutos());
        assertNull(vazio.getConteudoResumo());
    }
}
