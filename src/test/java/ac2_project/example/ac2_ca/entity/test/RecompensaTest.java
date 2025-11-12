package ac2_project.example.ac2_ca.entity.test;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ac2_project.example.ac2_ca.entity.*;

class RecompensaTest {

    @Test
    void testConstrutorComParametros() {
        Recompensa recompensa = new Recompensa(150.0f, "Desconto no curso", "Engenharia");

        assertEquals(150.0f, recompensa.getValor());
        assertEquals("Desconto no curso", recompensa.getNome());
        assertEquals("Engenharia", recompensa.getCurso_titulo());
    }

    @Test
    void testSettersAndGetters() {
        Recompensa recompensa = new Recompensa();
        recompensa.setValor(200.5f);
        recompensa.setNome("Certificado Premium");
        recompensa.setCurso_titulo("Computação");

        assertEquals(200.5f, recompensa.getValor());
        assertEquals("Certificado Premium", recompensa.getNome());
        assertEquals("Computação", recompensa.getCurso_titulo());
    }

    @Test
    void testEqualsAndHashCode() {
        Recompensa r1 = new Recompensa(100.0f, "Bônus", "Matemática");
        Recompensa r2 = new Recompensa(100.0f, "Bônus", "Matemática");
        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void testToString() {
        Recompensa recompensa = new Recompensa(300.0f, "Acesso VIP", "Engenharia de Software");
        String toStringResult = recompensa.toString();

        assertTrue(toStringResult.contains("Acesso VIP"));
        assertTrue(toStringResult.contains("Engenharia de Software"));
    }
}
