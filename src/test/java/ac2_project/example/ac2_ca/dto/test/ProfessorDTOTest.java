package ac2_project.example.ac2_ca.dto.test;

import ac2_project.example.ac2_ca.dto.ProfessorDTO;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class ProfessorDTOTest {

    @Test
    void testConstructorGettersAndSetters() {
        ProfessorDTO dto = new ProfessorDTO();
        dto.setId(1L);
        dto.setRa("123456");
        dto.setInstituicaoId(10L);

        assertEquals(1L, dto.getId());
        assertEquals("123456", dto.getRa());
        assertEquals(10L, dto.getInstituicaoId());
    }

    @Test
    void testAllArgsConstructor() {
        ProfessorDTO dto = new ProfessorDTO(2L, "654321", 20L);

        assertEquals(2L, dto.getId());
        assertEquals("654321", dto.getRa());
        assertEquals(20L, dto.getInstituicaoId());
    }
}
