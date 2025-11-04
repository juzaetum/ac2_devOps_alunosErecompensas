package ac2_project.example.ac2_ca.dto.test;

import org.junit.jupiter.api.Test;

import ac2_project.example.ac2_ca.dto.*;

import static org.junit.jupiter.api.Assertions.*;

class RecompensaDTOTest {

    @Test
    void deveCriarRecompensaDTOComBuilder() {
        RecompensaDTO recompensa = RecompensaDTO.builder()
                .tipo("Cursos Extras")
                .descricao("Ganhou curso de Java Avançado")
                .build();

        assertEquals("Cursos Extras", recompensa.getTipo());
        assertEquals("Ganhou curso de Java Avançado", recompensa.getDescricao());
    }

    @Test
    void deveTestarSettersEGetters() {
        RecompensaDTO recompensa = new RecompensaDTO();
        recompensa.setTipo("Medalha");
        recompensa.setDescricao("Conquista por 10 tópicos criados");

        assertEquals("Medalha", recompensa.getTipo());
        assertEquals("Conquista por 10 tópicos criados", recompensa.getDescricao());
    }

    @Test
    void deveGerarToStringENaoSerNulo() {
        RecompensaDTO recompensa = new RecompensaDTO();
        assertNotNull(recompensa.toString());
    }
}
