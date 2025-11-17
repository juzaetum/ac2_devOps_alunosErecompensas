package ac2_project.example.ac2_ca.repository.test;

import ac2_project.example.ac2_ca.entity.Instituicao;
import ac2_project.example.ac2_ca.entity.InstituicaoCNPJ;
import ac2_project.example.ac2_ca.repository.InstituicaoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class InstituicaoRepositoryTest {

    @Autowired
    private InstituicaoRepository repository;

    private Instituicao inst;

    @BeforeEach
    void setup() {
        inst = new Instituicao("Fatec Repo", "Rua Repo", new InstituicaoCNPJ("12345678000190"));
        repository.save(inst);
    }

    @Test
    void saveAndFind() {
        Instituicao loaded = repository.findById(inst.getId()).orElseThrow();
        assertEquals("Fatec Repo", loaded.getNome());
    }

    @Test
    void findAllShouldReturnList() {
        List<Instituicao> list = repository.findAll();
        assertFalse(list.isEmpty());
    }

    @Test
    void updateShouldPersist() {
        inst.setNome("Atualizado");
        repository.save(inst);

        Instituicao loaded = repository.findById(inst.getId()).orElseThrow();
        assertEquals("Atualizado", loaded.getNome());
    }

    @Test
    void deleteShouldRemove() {
        Long id = inst.getId();
        repository.deleteById(id);
        assertTrue(repository.findById(id).isEmpty());
    }
}
