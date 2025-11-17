package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.entity.Instituicao;
import ac2_project.example.ac2_ca.entity.InstituicaoCNPJ;
import ac2_project.example.ac2_ca.repository.InstituicaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class InstituicaoServiceTest {

    @Mock
    private InstituicaoRepository repository;

    @InjectMocks
    private InstituicaoService service;

    private Instituicao inst;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        inst = new Instituicao("Inst 1", "Rua X", new InstituicaoCNPJ("12345678000190"));
    }

    @Test
    void getAllShouldReturnList() {
        when(repository.findAll()).thenReturn(List.of(inst));
        assertEquals(1, service.getAll().size());
    }

    @Test
    void getByIdShouldReturnOptional() {
        when(repository.findById(1L)).thenReturn(Optional.of(inst));
        assertTrue(service.getById(1L).isPresent());
    }

    @Test
    void createShouldSave() {
        when(repository.save(inst)).thenReturn(inst);
        assertEquals(inst, service.create(inst));
    }

    @Test
    void updateShouldModifyValues() {
        when(repository.findById(1L)).thenReturn(Optional.of(inst));
        when(repository.save(inst)).thenReturn(inst);

        inst.setNome("Novo");
        Optional<Instituicao> updated = service.update(1L, inst);

        assertTrue(updated.isPresent());
        assertEquals("Novo", updated.get().getNome());
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
