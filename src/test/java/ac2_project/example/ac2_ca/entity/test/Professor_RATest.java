package ac2_project.example.ac2_ca.entity.test;

import ac2_project.example.ac2_ca.entity.Professor_RA;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class Professor_RATest {

    @Test
    void deveCriarRaValidoComSeisDigitos() {
        Professor_RA ra = new Professor_RA("123456");
        assertEquals("123456", ra.getCodigo());
    }

    @Test
    void deveGerarExcecaoParaRaComMenosDeSeisDigitos() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Professor_RA("12345"));
        assertEquals("O RA do professor deve conter exatamente 6 dígitos numéricos.", ex.getMessage());
    }

    @Test
    void deveGerarExcecaoParaRaComMaisDeSeisDigitos() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Professor_RA("1234567"));
        assertTrue(ex.getMessage().contains("6 dígitos"));
    }

    @Test
    void deveGerarExcecaoParaRaComLetras() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Professor_RA("ABC123"));
        assertTrue(ex.getMessage().contains("6 dígitos"));
    }

    @Test
    void deveGerarExcecaoParaRaNulo() {
        Exception ex = assertThrows(IllegalArgumentException.class, () -> new Professor_RA(null));
        assertTrue(ex.getMessage().contains("6 dígitos"));
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
        Exception ex = assertThrows(IllegalArgumentException.class, () -> ra.setCodigo("12AB34"));
        assertTrue(ex.getMessage().contains("6 dígitos"));
    }

    @Test
    void toStringDeveRetornarCodigoCorretamente() {
        Professor_RA ra = new Professor_RA("999999");
        assertEquals("999999", ra.toString());
    }

    @Test
    void construtorVazioNaoDeveLancarErro() {
        Professor_RA ra = new Professor_RA();
        assertNull(ra.getCodigo());
    }

    @Test
    void deveManterCodigoInalteradoQuandoSetCodigoValidoChamadoNovamente() {
        Professor_RA ra = new Professor_RA("123456");
        ra.setCodigo("123456");
        assertEquals("123456", ra.getCodigo());
    }

    @Test
    void deveLancarErroQuandoSetCodigoComEspacos() {
        Professor_RA ra = new Professor_RA("654321");
        Exception ex = assertThrows(IllegalArgumentException.class, () -> ra.setCodigo(" 65432"));
        assertTrue(ex.getMessage().contains("6 dígitos"));
    }
}
