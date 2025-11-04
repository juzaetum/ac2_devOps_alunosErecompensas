package ac2_project.example.ac2_ca.entity.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ac2_project.example.ac2_ca.entity.*;

import static org.junit.jupiter.api.Assertions.*;

class AlunoRATest {

    private AlunoRA alunoRAValido;

    @BeforeEach
    void setUp() {
        alunoRAValido = new AlunoRA("123456");
    }

    @Test
    void deveCriarAlunoRAValido() {
        assertNotNull(alunoRAValido);
        assertEquals("123456", alunoRAValido.getNumero());
    }

    @Test
    void deveLancarExcecaoParaRAInvalidoComLetras() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AlunoRA("12AB56"));
        assertEquals("O RA deve conter exatamente 6 números.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaRAComMenosDe6Digitos() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AlunoRA("12345"));
        assertEquals("O RA deve conter exatamente 6 números.", exception.getMessage());
    }

    @Test
    void deveLancarExcecaoParaRAComMaisDe6Digitos() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> new AlunoRA("1234567"));
        assertEquals("O RA deve conter exatamente 6 números.", exception.getMessage());
    }

    @Test
    void deveAtualizarRACorretamenteComSetNumeroValido() {
        alunoRAValido.setNumero("654321");
        assertEquals("654321", alunoRAValido.getNumero());
    }

    @Test
    void deveLancarExcecaoAoDefinirNumeroInvalido() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> alunoRAValido.setNumero("abc123"));
        assertEquals("O RA deve conter exatamente 6 números.", exception.getMessage());
    }

    @Test
    void toStringDeveRetornarONumero() {
        assertEquals("123456", alunoRAValido.toString());
    }

    @Test
    void equalsDeveRetornarTrueParaObjetosIguais() {
        AlunoRA outroRA = new AlunoRA("123456");
        assertTrue(alunoRAValido.equals(outroRA));
        assertEquals(alunoRAValido.hashCode(), outroRA.hashCode());
    }

    @Test
    void equalsDeveRetornarFalseParaObjetosDiferentes() {
        AlunoRA outroRA = new AlunoRA("654321");
        assertFalse(alunoRAValido.equals(outroRA));
    }

    @Test
    void equalsDeveRetornarFalseParaObjetoDeOutroTipo() {
        assertFalse(alunoRAValido.equals("123456"));
    }

    @Test
    void equalsDeveRetornarFalseParaNulo() {
        assertFalse(alunoRAValido.equals(null));
    }
}
