package ac2_project.example.ac2_ca.entity.test;

import ac2_project.example.ac2_ca.entity.Recompensa;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecompensaTest {

    @Test
    void testConstrutorPadraoESetters() {
        Recompensa r = new Recompensa();

        r.setId(10L);
        r.setValor(55.5f);
        r.setNome("Certificado");
        r.setCurso_titulo("ADS");

        assertEquals(10L, r.getId());
        assertEquals(55.5f, r.getValor());
        assertEquals("Certificado", r.getNome());
        assertEquals("ADS", r.getCurso_titulo());
    }

    @Test
    void testConstrutorValorNomeCurso() {
        Recompensa r = new Recompensa(150.0f, "Desconto", "Engenharia");

        assertEquals(150.0f, r.getValor());
        assertEquals("Desconto", r.getNome());
        assertEquals("Engenharia", r.getCurso_titulo());
    }

    @Test
    void testConstrutorNomeValorCurso() {
        Recompensa r = new Recompensa("Brinde", 80.0f, "Marketing");

        assertEquals(80.0f, r.getValor());
        assertEquals("Brinde", r.getNome());
        assertEquals("Marketing", r.getCurso_titulo());
    }

    @Test
    void testConstrutorCompleto() {
        Recompensa r = new Recompensa(5L, 200.0f, "VIP", "TI");

        assertEquals(5L, r.getId());
        assertEquals(200.0f, r.getValor());
        assertEquals("VIP", r.getNome());
        assertEquals("TI", r.getCurso_titulo());
    }

    @Test
    void testEqualsAndHashCodeTrue() {
        Recompensa r1 = new Recompensa(1L, 100.0f, "Brinde", "Logística");
        Recompensa r2 = new Recompensa(1L, 100.0f, "Brinde", "Logística");

        assertEquals(r1, r2);
        assertEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void testEqualsAndHashCodeFalseDifferentId() {
        Recompensa r1 = new Recompensa(1L, 100.0f, "Brinde", "Logística");
        Recompensa r2 = new Recompensa(2L, 100.0f, "Brinde", "Logística");

        assertNotEquals(r1, r2);
        assertNotEquals(r1.hashCode(), r2.hashCode());
    }

    @Test
    void testEqualsDifferentObjectType() {
        Recompensa r = new Recompensa(1L, 50f, "X", "Y");
        assertNotEquals(r, "string");
    }

    @Test
    void testEqualsNull() {
        Recompensa r = new Recompensa(1L, 50f, "X", "Y");
        assertNotEquals(r, null);
    }

    @Test
    void testToStringContainsFields() {
        Recompensa r = new Recompensa(300.0f, "Acesso VIP", "Engenharia de Software");
        String txt = r.toString();

        assertTrue(txt.contains("Acesso VIP"));
        assertTrue(txt.contains("Engenharia de Software"));
        assertTrue(txt.contains("300.0"));
    }

    @Test
    void testDifferentValuesNotEqual() {
        Recompensa r1 = new Recompensa(1L, 10f, "A", "Curso1");
        Recompensa r2 = new Recompensa(1L, 20f, "B", "Curso2");
        assertEquals(r1.getId(), r2.getId());
        assertNotEquals(r1.getValor(), r2.getValor());
        assertNotEquals(r1.getNome(), r2.getNome());
        assertNotEquals(r1.getCurso_titulo(), r2.getCurso_titulo());
    }
}
