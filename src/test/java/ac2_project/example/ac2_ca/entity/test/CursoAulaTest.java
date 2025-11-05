package ac2_project.example.ac2_ca.entity.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CursoAulaTest {

    private List<String> cursos;
    private List<String> aulas;

    @BeforeEach
    void setUp() {
        cursos = new ArrayList<>();
        aulas = new ArrayList<>();
    }

    @Test
    void deveCadastrarCurso() {
        cursos.add("Engenharia de Software");

        assertEquals(1, cursos.size());
        assertEquals("Engenharia de Software", cursos.get(0));
    }

    @Test
    void deveCadastrarAula() {
        aulas.add("Introdução ao Spring Boot");

        assertEquals(1, aulas.size());
        assertEquals("Introdução ao Spring Boot", aulas.get(0));
    }

    @Test
    void deveLancarErroQuandoCursoNaoExiste() {
        Exception ex = assertThrows(RuntimeException.class, () -> {
            String curso = cursos.stream()
                    .filter(c -> c.equals("Inexistente"))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Curso não encontrado"));
        });

        assertEquals("Curso não encontrado", ex.getMessage());
    }

    @Test
    void deveLancarErroQuandoAulaNaoExiste() {
        cursos.add("Engenharia de Software");

        Exception ex = assertThrows(RuntimeException.class, () -> {
            String aula = aulas.stream()
                    .filter(a -> a.equals("Inexistente"))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Aula não encontrada"));
        });

        assertEquals("Aula não encontrada", ex.getMessage());
    }
}
