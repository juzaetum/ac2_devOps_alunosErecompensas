package ac2_project.example.ac2_ca.dto.test;

import ac2_project.example.ac2_ca.dto.CursoDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CursoDTOTest {

    @Test
    void testConstructorGetterSetter() {
        CursoDTO dto = new CursoDTO();
        dto.setId(1L);
        dto.setTitulo("Java");
        dto.setDescricao("Curso completo");
        dto.setProfessor("Carlos");
        dto.setAtivo(true);

        assertEquals(1L, dto.getId());
        assertEquals("Java", dto.getTitulo());
        assertEquals("Curso completo", dto.getDescricao());
        assertEquals("Carlos", dto.getProfessor());
        assertTrue(dto.isAtivo());
    }

    @Test
    void testAllArgsConstructor() {
        CursoDTO dto = new CursoDTO(2L, "Python", "Intro", "Ana", false);

        assertEquals(2L, dto.getId());
        assertEquals("Python", dto.getTitulo());
        assertEquals("Intro", dto.getDescricao());
        assertEquals("Ana", dto.getProfessor());
        assertFalse(dto.isAtivo());
    }
}
