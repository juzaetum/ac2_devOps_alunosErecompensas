package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.entity.Curso;
import ac2_project.example.ac2_ca.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import org.mockito.MockitoAnnotations;

class CursoServiceTest {

    @Mock
    private CursoRepository repository;

    @InjectMocks
    private CursoService service;

    private Curso curso;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        curso = new Curso("Engenharia", "Desc", "Prof ABC");
        curso.setId(1L);
    }

    @Test
    void getAllShouldReturnList() {
        when(repository.findAll()).thenReturn(List.of(curso));
        assertEquals(1, service.getAll().size());
    }

    @Test
    void getByIdShouldReturnOptional() {
        when(repository.findById(1L)).thenReturn(Optional.of(curso));
        assertTrue(service.getById(1L).isPresent());
    }

    @Test
    void createShouldSave() {
        when(repository.save(curso)).thenReturn(curso);
        assertEquals(curso, service.create(curso));
    }

    @Test
    void updateShouldModifyValues() {
        when(repository.findById(1L)).thenReturn(Optional.of(curso));
        when(repository.save(curso)).thenReturn(curso);

        curso.setTitulo("Atualizado");
        Optional<Curso> updated = service.update(1L, curso);

        assertTrue(updated.isPresent());
        assertEquals("Atualizado", updated.get().getTitulo());
    }

    @Test
    void deleteShouldReturnTrue() {
        when(repository.existsById(1L)).thenReturn(true);
        assertTrue(service.delete(1L));
    }

    @Test
    void deleteShouldReturnFalse() {
        when(repository.existsById(1L)).thenReturn(false);
        assertFalse(service.delete(1L));
    }
}
