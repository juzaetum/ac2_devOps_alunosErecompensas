package ac2_project.example.ac2_ca.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import java.util.List;

import ac2_project.example.ac2_ca.entity.*;

public class CursoTest {

    @Test
    void testCriacaoCursoValido() {
        Curso curso = new Curso("Engenharia de Software", "Curso focado em boas práticas", "Prof. João");

        assertEquals("Engenharia de Software", curso.getTitulo());
        assertEquals("Curso focado em boas práticas", curso.getDescricao());
        assertEquals("Prof. João", curso.getProfessor());
        assertTrue(curso.isAtivo());
        assertEquals(0, curso.getQuantidadeAulas());
    }

    @Test
    void testAdicionarEAulasRemover() {
        Curso curso = new Curso("Sistemas Digitais", "Aprofundamento em lógica digital", "Prof. Lucas");
        CursoAula aula1 = new CursoAula("Introdução", 45, "Visão geral sobre lógica", true);
        CursoAula aula2 = new CursoAula("Flip-Flops", 60, "Circuitos sequenciais", true);

        curso.adicionarAula(aula1);
        curso.adicionarAula(aula2);

        assertEquals(2, curso.getQuantidadeAulas());
        assertTrue(curso.getAulas().contains(aula1));

        curso.removerAula(aula1);
        assertEquals(1, curso.getQuantidadeAulas());
        assertFalse(curso.getAulas().contains(aula1));
    }

    @Test
    void testAdicionarAlunos() {
        Curso curso = new Curso("Banco de Dados", "Modelagem e SQL", "Prof. Clara");

        Aluno aluno1 = new Aluno(new AlunoRA("123456"));
        Aluno aluno2 = new Aluno(new AlunoRA("654321"));

        curso.adicionarAluno(aluno1);
        curso.adicionarAluno(aluno2);

        List<Aluno> alunos = curso.getAlunos();
        assertEquals(2, alunos.size());
        assertEquals("123456", alunos.get(0).getRa().getNumero());
    }

    @Test
    void testDesativarCurso() {
        Curso curso = new Curso("Arquitetura de Software", "Camadas e padrões", "Prof. José");

        assertTrue(curso.isAtivo());
        curso.desativar();
        assertFalse(curso.isAtivo());
    }

    @Test
    void testSettersFuncionam() {
        Curso curso = new Curso("Redes", "Conceitos básicos", "Prof. Ana");
        curso.setTitulo("Redes de Computadores");
        curso.setDescricao("Conceitos avançados de redes");
        curso.setProfessor("Prof. Maria");

        assertEquals("Redes de Computadores", curso.getTitulo());
        assertEquals("Conceitos avançados de redes", curso.getDescricao());
        assertEquals("Prof. Maria", curso.getProfessor());
    }
    
    @Test
    void deveCriarCursoComConstrutorPadrao() {
        Curso curso = new Curso();
        assertEquals("", curso.getTitulo());
        assertEquals("", curso.getDescricao());
        assertEquals("", curso.getProfessor());
        assertTrue(curso.isAtivo());
        assertNull(curso.getAulas());
    }

    @Test
    void deveCriarCursoComTituloEIdPersonalizado() {
        Curso curso = new Curso("Cálculo I", 123);
        assertEquals("Cálculo I", curso.getTitulo());
        assertEquals(123L, curso.getId());
    }

    @Test
    void devePermitirDefinirAulasViaSetter() {
        Curso curso = new Curso("Matemática", "Álgebra Linear", "Prof. Luiz");
        CursoAula aula = new CursoAula("Vetores", 40, "Introdução a vetores", true);

        curso.setAulas(List.of(aula));

        assertEquals(1, curso.getAulas().size());
        assertEquals("Vetores", curso.getAulas().get(0).getTitulo());
    }

    @Test
    void devePermitirAlterarEstadoAtivoComSetter() {
        Curso curso = new Curso("Eletrônica", "Circuitos", "Prof. Paulo");
        curso.setAtivo(false);
        assertFalse(curso.isAtivo());
        curso.setAtivo(true);
        assertTrue(curso.isAtivo());
    }

    @Test
    void devePermitirObterListaDeAulasMesmoSemAdicionar() {
        Curso curso = new Curso("Química", "Estrutura Atômica", "Prof. Alice");
        assertNotNull(curso.getAulas());
        assertTrue(curso.getAulas().isEmpty());
    }

    @Test
    void deveManterListaDeAlunosIndependenteEntreCursos() {
        Curso curso1 = new Curso("Física", "Mecânica", "Prof. Carlos");
        Curso curso2 = new Curso("Física II", "Eletromagnetismo", "Prof. Diego");

        Aluno aluno = new Aluno(new AlunoRA("112233"));
        curso1.adicionarAluno(aluno);

        assertEquals(1, curso1.getAlunos().size());
        assertEquals(0, curso2.getAlunos().size());
    }

}
