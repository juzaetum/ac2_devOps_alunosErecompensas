package ac2_project.example.ac2_ca.entity.test;

import org.junit.jupiter.api.Test;

import ac2_project.example.ac2_ca.entity.InstituicaoCNPJ;

import static org.junit.jupiter.api.Assertions.*;

class InstituicaoCNPJTest {

    @Test
    void deveCriarCnpjValido() {
        InstituicaoCNPJ cnpj = new InstituicaoCNPJ("12345678000190");
        assertEquals("12345678000190", cnpj.getNumero());
    }

    @Test
    void deveLancarExcecaoQuandoCnpjInvalido() {
        assertThrows(IllegalArgumentException.class, () -> new InstituicaoCNPJ("123"));
        assertThrows(IllegalArgumentException.class, () -> new InstituicaoCNPJ("abc45678000190"));
        assertThrows(IllegalArgumentException.class, () -> new InstituicaoCNPJ("1234567800019")); // 13 dígitos
    }

    @Test
    void devePermitirAlterarNumeroComCnpjValido() {
        InstituicaoCNPJ cnpj = new InstituicaoCNPJ("12345678000190");
        cnpj.setNumero("11111111000111");
        assertEquals("11111111000111", cnpj.getNumero());
    }

    @Test
    void deveLancarExcecaoAoTentarSetarCnpjInvalido() {
        InstituicaoCNPJ cnpj = new InstituicaoCNPJ("12345678000190");
        assertThrows(IllegalArgumentException.class, () -> cnpj.setNumero("111111111111")); // 12 dígitos
    }

    @Test
    void deveCompararCnpjsCorretamente() {
        InstituicaoCNPJ cnpj1 = new InstituicaoCNPJ("12345678000190");
        InstituicaoCNPJ cnpj2 = new InstituicaoCNPJ("12345678000190");
        InstituicaoCNPJ cnpj3 = new InstituicaoCNPJ("11111111000111");

        assertEquals(cnpj1, cnpj2);
        assertNotEquals(cnpj1, cnpj3);
        assertNotEquals(cnpj1, null);
        assertNotEquals(cnpj1, "texto");
        assertEquals(cnpj1.hashCode(), cnpj2.hashCode());
    }

    @Test
    void deveRetornarToStringCorreto() {
        InstituicaoCNPJ cnpj = new InstituicaoCNPJ("12345678000190");
        assertEquals("12345678000190", cnpj.toString());
    }
}
