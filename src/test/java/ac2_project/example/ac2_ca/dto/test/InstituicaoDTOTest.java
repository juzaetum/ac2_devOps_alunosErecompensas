package ac2_project.example.ac2_ca.dto.test;

import ac2_project.example.ac2_ca.dto.InstituicaoDTO;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstituicaoDTOTest {

    @Test
    void testConstructorGetterSetter() {
        InstituicaoDTO dto = new InstituicaoDTO();
        dto.setId(1L);
        dto.setNome("Fatec");
        dto.setEndereco("Rua 1");
        dto.setCnpj("12345678000190");

        assertEquals(1L, dto.getId());
        assertEquals("Fatec", dto.getNome());
        assertEquals("Rua 1", dto.getEndereco());
        assertEquals("12345678000190", dto.getCnpj());
    }

    @Test
    void testAllArgsConstructor() {
        InstituicaoDTO dto = new InstituicaoDTO(2L, "Unesp", "Av Central", "11111111000111");

        assertEquals(2L, dto.getId());
        assertEquals("Unesp", dto.getNome());
        assertEquals("Av Central", dto.getEndereco());
        assertEquals("11111111000111", dto.getCnpj());
    }
}
