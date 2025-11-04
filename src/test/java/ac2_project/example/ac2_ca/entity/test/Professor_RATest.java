package ac2_project.example.ac2_ca.entity.test;

import org.junit.jupiter.api.Test;

import ac2_project.example.ac2_ca.entity.*;

import static org.junit.jupiter.api.Assertions.*;

class Professor_RATest {

    @Test
    void deveCriarRaValidoComSeisDigitos() {
        Professor_RA ra = new Professor_RA("123456");
        assertEquals("123456", ra.getCodigo());
    }

    @Test
    void deveGerarExcecaoParaRaComMenosDeSeisDigitos() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Professor_RA("12345");
        });
        assertEquals("O RA do professor deve conter exatamente 6 dígitos numéricos.", exception.getMessage());
    }

    @Test
    void deveGerarExcecaoParaRaComMaisDeSeisDigitos() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Professor_RA("1234567");
        });
        assertTrue(exception.getMessage().contains("6 dígitos"));
    }

    @Test
    void deveGerarExcecaoParaRaComLetras() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Professor_RA("ABC123");
        });
        assertTrue(exception.getMessage().contains("6 dígitos"));
    }

    @Test
    void deveGerarExcecaoParaRaNulo() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            new Professor_RA(null);
        });
        assertTrue(exception.getMessage().contains("6 dígitos"));
    }

    @Test
    void devePermitirAlterarRaValidoComSetCodigo() {
        Professor_RA ra = new Professor_RA("111111");
        ra.setCodigo("222222");
        assertEquals("222222", ra.getCodigo());
    }

    @Test
    void deveGerarExcecaoAoAlterarParaRaInvalido() {
        Professor_RA ra = new Professor_RA("111111");
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            ra.setCodigo("12AB34");
        });
        assertTrue(exception.getMessage().contains("6 dígitos"));
    }

    @Test
    void toStringDeveRetornarCodigoCorretamente() {
        Professor_RA ra = new Professor_RA("999999");
        assertEquals("999999", ra.toString());
    }
}
