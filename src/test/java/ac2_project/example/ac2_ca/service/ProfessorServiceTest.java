package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.dto.ProfessorDTO;
import ac2_project.example.ac2_ca.entity.Instituicao;
import ac2_project.example.ac2_ca.entity.InstituicaoCNPJ;
import ac2_project.example.ac2_ca.entity.Professor;
import ac2_project.example.ac2_ca.entity.Professor_RA;
import ac2_project.example.ac2_ca.repository.InstituicaoRepository;
import ac2_project.example.ac2_ca.repository.ProfessorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfessorServiceTest {

    @InjectMocks
    private ProfessorService service;

    @Mock
    private ProfessorRepository professorRepository;

    @Mock
    private InstituicaoRepository instituicaoRepository;

    private Instituicao instituicao;
    private Professor_RA ra;
    private Professor professor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        instituicao = new Instituicao("Fatec Teste", "Rua X", new InstituicaoCNPJ("12345678000190"));
        instituicao.setId(10L);
        ra = new Professor_RA("123456");
        professor = new Professor(ra, instituicao);
        professor.setId(1L);
    }

    @Test
    void getAllShouldReturnList() {
        when(professorRepository.findAll()).thenReturn(List.of(professor));

        List<Professor> list = service.getAll();
        assertNotNull(list);
        assertEquals(1, list.size());
        assertEquals(professor, list.get(0));
        verify(professorRepository, times(1)).findAll();
    }

    @Test
    void getByIdWhenExists() {
        when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));

        Optional<Professor> opt = service.getById(1L);
        assertTrue(opt.isPresent());
        assertEquals(professor, opt.get());
    }

    @Test
    void getByIdWhenNotFound() {
        when(professorRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Professor> opt = service.getById(2L);
        assertTrue(opt.isEmpty());
    }

    @Test
    void createShouldSaveWhenInstituicaoExistsAndRaValid() {
        ProfessorDTO dto = new ProfessorDTO(null, "654321", instituicao.getId());
        when(instituicaoRepository.findById(instituicao.getId())).thenReturn(Optional.of(instituicao));
        when(professorRepository.save(any(Professor.class))).thenAnswer(i -> {
            Professor p = i.getArgument(0);
            p.setId(5L);
            return p;
        });

        Professor saved = service.create(dto);
        assertNotNull(saved);
        assertEquals(5L, saved.getId());
        assertEquals("654321", saved.getRa().getCodigo());
        assertEquals(instituicao, saved.getInstituicao());
        verify(professorRepository).save(any());
    }

    @Test
    void createShouldThrowWhenInstituicaoNotFound() {
        ProfessorDTO dto = new ProfessorDTO(null, "654321", 999L);
        when(instituicaoRepository.findById(999L)).thenReturn(Optional.empty());

        IllegalArgumentException ex = assertThrows(IllegalArgumentException.class, () -> service.create(dto));
        assertEquals("Instituição não encontrada.", ex.getMessage());
        verify(professorRepository, never()).save(any());
    }

    @Test
    void createShouldThrowWhenRaInvalid() {
        ProfessorDTO dto = new ProfessorDTO(null, "12AB", instituicao.getId());
        when(instituicaoRepository.findById(instituicao.getId())).thenReturn(Optional.of(instituicao));

        assertThrows(IllegalArgumentException.class, () -> service.create(dto));
        verify(professorRepository, never()).save(any());
    }

    @Test
    void updateShouldModifyExisting() {
        ProfessorDTO dto = new ProfessorDTO(null, "999999", instituicao.getId());
        when(professorRepository.findById(1L)).thenReturn(Optional.of(professor));
        when(instituicaoRepository.findById(instituicao.getId())).thenReturn(Optional.of(instituicao));
        when(professorRepository.save(any())).thenAnswer(i -> i.getArgument(0));

        Optional<Professor> updated = service.update(1L, dto);
        assertTrue(updated.isPresent());
        assertEquals("999999", updated.get().getRa().getCodigo());
    }

    @Test
    void updateWhenNotFoundShouldReturnEmpty() {
        ProfessorDTO dto = new ProfessorDTO(null, "999999", instituicao.getId());
        when(professorRepository.findById(2L)).thenReturn(Optional.empty());

        Optional<Professor> updated = service.update(2L, dto);
        assertTrue(updated.isEmpty());
        verify(professorRepository, never()).save(any());
    }

    @Test
    void deleteShouldReturnTrueWhenExists() {
        when(professorRepository.existsById(1L)).thenReturn(true);
        doNothing().when(professorRepository).deleteById(1L);

        assertTrue(service.delete(1L));
        verify(professorRepository).deleteById(1L);
    }

    @Test
    void deleteShouldReturnFalseWhenNotExists() {
        when(professorRepository.existsById(2L)).thenReturn(false);

        assertFalse(service.delete(2L));
        verify(professorRepository, never()).deleteById(anyLong());
    }
}
