package ac2_project.example.ac2_ca.repository.test;

import ac2_project.example.ac2_ca.entity.Curso;
import ac2_project.example.ac2_ca.repository.CursoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;


import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class CursoRepositoryTest {

    @Autowired
    private CursoRepository repository;

    private Curso curso;

    @BeforeEach
    void setup() {
        curso = new Curso("Java", "Curso de Java", "Prof. Silva");
        repository.save(curso);
    }

    @Test
    void saveAndFind() {
        Curso c = repository.findById(curso.getId()).orElseThrow();
        assertEquals("Java", c.getTitulo());
    }

    @Test
    void findAllShouldReturnList() {
        assertFalse(repository.findAll().isEmpty());
    }

    @Test
    void updateShouldPersist() {
        curso.setTitulo("Spring Boot");
        repository.save(curso);

        Curso updated = repository.findById(curso.getId()).orElseThrow();
        assertEquals("Spring Boot", updated.getTitulo());
    }

    @Test
    void deleteShouldRemove() {
        Long id = curso.getId();
        repository.deleteById(id);
        assertTrue(repository.findById(id).isEmpty());
    }
}
