package ac2_project.example.ac2_ca.repository.test;

import ac2_project.example.ac2_ca.entity.Recompensa;
import ac2_project.example.ac2_ca.repository.Recompensa_Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class RecompensaRepositoryTest {
    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private Recompensa_Repository repository;


    private Recompensa r1, r2, r3;

    @BeforeEach
    void setup() {
        r1 = new Recompensa("Medalha Ouro", 90f, "Java");
        r2 = new Recompensa("Medalha Prata", 70f, "Python");
        r3 = new Recompensa("Medalha Bronze", 50f, "Java");

        entityManager.persist(r1);
        entityManager.persist(r2);
        entityManager.persist(r3);
    }

    @Test
    void testFindByNomeContainingIgnoreCase() {
        List<Recompensa> result = repository.findByNomeContainingIgnoreCase("medalha");

        assertEquals(3, result.size());
    }

    @Test
    void testFindByValorBetween() {
        List<Recompensa> result = repository.findByValorBetween(60f, 100f);

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(r -> r.getValor() >= 60 && r.getValor() <= 100));
    }

    @Test
    void testFindByCursoTitulo() {
        List<Recompensa> result = repository.findByCursoTitulo("Java");

        assertEquals(2, result.size());
        assertTrue(result.stream().allMatch(r -> r.getCurso_titulo().equals("Java")));
    }

    @Test
    void testFindTop10ByOrderByValorDesc() {
        List<Recompensa> result = repository.findTop10ByOrderByValorDesc();

        assertEquals(3, result.size());
        assertEquals(90f, result.get(0).getValor());
        assertEquals(70f, result.get(1).getValor());
        assertEquals(50f, result.get(2).getValor());
    }
}
