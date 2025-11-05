package ac2_project.example.ac2_ca.repository.test;

import ac2_project.example.ac2_ca.entity.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class CursoAulaTest {

    private Curso curso;
    private CursoAula aula1;
    private CursoAula aula2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        curso = new Curso();
        curso.setTitulo("Engenharia de Computação");

        aula1 = new CursoAula("Introdução a Microcontroladores", 90, "Conceitos básicos e aplicações práticas.", true);
        aula2 = new CursoAula("Arquitetura de Computadores", 120, "Funcionamento interno e instruções.", false);
    }

    @Test
    @DisplayName("Deve adicionar aulas ao curso corretamente")
    void deveAdicionarAulasAoCurso() {
        curso.adicionarAula(aula1);
        curso.adicionarAula(aula2);

        List<CursoAula> aulas = curso.getAulas();

        assertThat(aulas)
                .isNotEmpty()
                .hasSize(2)
                .containsExactly(aula1, aula2);
    }

    @Test
    @DisplayName("Deve garantir que as aulas pertencem ao curso")
    void deveGarantirQueAulasPertencemAoCurso() {
        curso.adicionarAula(aula1);

        assertThat(aula1.getCurso()).isEqualTo(curso);
        assertThat(((Curso) aula1.getCurso()).getTitulo()).isEqualTo("Engenharia de Computação");
    }

    @Test
    @DisplayName("Deve permitir remover uma aula do curso")
    void deveRemoverAulaDoCurso() {
        curso.adicionarAula(aula1);
        curso.adicionarAula(aula2);

        curso.removerAula(aula1);

        assertThat(curso.getAulas())
                .hasSize(1)
                .containsExactly(aula2);
    }

    @Test
    @DisplayName("Deve retornar corretamente o nome e quantidade de aulas do curso")
    void deveRetornarNomeEQuantidadeDeAulas() {
        curso.adicionarAula(aula1);
        curso.adicionarAula(aula2);

        String resumo = curso.getTitulo() + " - " + curso.getAulas().size() + " aulas";

        assertThat(resumo)
                .isEqualTo("Engenharia de Computação - 2 aulas");
    }

    @Test
    @DisplayName("Deve validar getters e setters de Aula")
    void deveValidarGettersESettersDeAula() {
        CursoAula aula = new CursoAula("Eletrônica Digital", 80, "Portas lógicas e circuitos combinacionais", true);
    

        assertThat(aula.getTitulo()).isEqualTo("Eletrônica Digital");
        assertThat(aula.getConteudoResumo()).contains("Portas lógicas");
        assertThat(aula.getDuracaoMinutos()).isEqualTo(80);
    }

    @Test
    @DisplayName("Deve simular comportamento com Mockito")
    void deveUsarMockitoParaVerificarChamadaDeMetodo() {
        Curso mockCurso = mock(Curso.class);
        when(mockCurso.getTitulo()).thenReturn("Curso Mockado");

        String titulo = mockCurso.getTitulo();

        assertThat(titulo).isEqualTo("Curso Mockado");
        verify(mockCurso, times(1)).getTitulo();
    }
}
