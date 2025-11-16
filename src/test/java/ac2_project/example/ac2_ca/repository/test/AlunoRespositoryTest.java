package ac2_project.example.ac2_ca.repository.test;

import ac2_project.example.ac2_ca.entity.Aluno;
import ac2_project.example.ac2_ca.entity.AlunoRA;
import ac2_project.example.ac2_ca.repository.AlunoRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository repository;

    private Aluno aluno;

    @BeforeEach
    void setup() {
        aluno = new Aluno(new AlunoRA("123456"));
        aluno.setNome("Juliane");
        repository.save(aluno);
    }

    @Test
    void testSaveAluno() {
        Aluno novo = new Aluno(new AlunoRA("654321"));
        novo.setNome("Marcos");
        Aluno salvo = repository.save(novo);

        assertNotNull(salvo.getId());
        assertEquals("Marcos", salvo.getNome());
        assertEquals("654321", salvo.getRa().getNumero());
    }

    @Test
    void testFindById() {
        Optional<Aluno> result = repository.findById(aluno.getId());

        assertTrue(result.isPresent());
        assertEquals("Marcos", result.get().getNome());
    }

    @Test
    void testFindAll() {
        assertEquals(1, repository.findAll().size());
    }

    @Test
    void testUpdateAluno() {
        aluno.setNome("Teste Atualizada");
        Aluno atualizado = repository.save(aluno);

        assertEquals("Teste Atualizada", atualizado.getNome());
    }

    @Test
    void testDeleteAluno() {
        repository.deleteById(aluno.getId());

        assertFalse(repository.findById(aluno.getId()).isPresent());
    }
}
