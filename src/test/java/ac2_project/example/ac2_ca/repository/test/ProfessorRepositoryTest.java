package ac2_project.example.ac2_ca.repository.test;

import ac2_project.example.ac2_ca.entity.*;
import ac2_project.example.ac2_ca.repository.InstituicaoRepository;
import ac2_project.example.ac2_ca.repository.ProfessorRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class ProfessorRepositoryTest {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    private Instituicao inst;
    private Professor_RA ra;
    private Professor professor;

    @BeforeEach
    void setup() {
        inst = new Instituicao("Fatec Repo", "Rua Repo", new InstituicaoCNPJ("12345678000190"));
        inst = instituicaoRepository.save(inst);

        ra = new Professor_RA("555555");
        professor = new Professor(ra, inst);
        professor = professorRepository.save(professor);
    }

    @Test
    void saveAndFindById() {
        Optional<Professor> opt = professorRepository.findById(professor.getId());
        assertTrue(opt.isPresent());
        assertEquals("555555", opt.get().getRa().getCodigo());
        assertEquals(inst.getId(), opt.get().getInstituicao().getId());
    }

    @Test
    void findAllShouldReturnAtLeastOne() {
        List<Professor> all = professorRepository.findAll();
        assertFalse(all.isEmpty());
    }

    @Test
    void updateShouldPersistChange() {
        professor.getRa().setCodigo("666666");
        professor = professorRepository.save(professor);

        Professor loaded = professorRepository.findById(professor.getId()).orElseThrow();
        assertEquals("666666", loaded.getRa().getCodigo());
    }

    @Test
    void deleteShouldRemove() {
        Long id = professor.getId();
        professorRepository.deleteById(id);
        assertFalse(professorRepository.findById(id).isPresent());
    }
}
