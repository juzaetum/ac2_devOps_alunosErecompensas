package ac2_project.example.ac2_ca.dto.test;

import ac2_project.example.ac2_ca.dto.RecompensaDTO;
import ac2_project.example.ac2_ca.entity.Recompensa;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

class RecompensaDTOTest {

    @Test
    @DisplayName("Deve criar e acessar atributos de RecompensaDTO via construtor e getters/setters")
    void testConstrutorEGetters() {
        RecompensaDTO dto = new RecompensaDTO("Financeira", "Recompensa em dinheiro");

        assertThat(dto.getTipo()).isEqualTo("Financeira");
        assertThat(dto.getDescricao()).isEqualTo("Recompensa em dinheiro");

        dto.setTipo("Acadêmica");
        dto.setDescricao("Reconhecimento de desempenho");

        assertThat(dto.getTipo()).isEqualTo("Acadêmica");
        assertThat(dto.getDescricao()).isEqualTo("Reconhecimento de desempenho");
    }

    @Test
    @DisplayName("Deve criar RecompensaDTO via builder do Lombok")
    void testBuilder() {
        RecompensaDTO dto = RecompensaDTO.builder()
                .tipo("Cultural")
                .descricao("Participação em evento")
                .build();

        assertThat(dto).isNotNull();
        assertThat(dto.getTipo()).isEqualTo("Cultural");
        assertThat(dto.getDescricao()).isEqualTo("Participação em evento");
    }

    @Test
    @DisplayName("Deve lançar exceção quando fromEntity for chamado (não implementado)")
    void testFromEntityThrowsException() {
        Recompensa recompensa = new Recompensa(100f, "Melhor Aluno", "Engenharia");

        UnsupportedOperationException exception = assertThrows(
                UnsupportedOperationException.class,
                () -> RecompensaDTO.fromEntity(recompensa));

        assertThat(exception.getMessage()).contains("Unimplemented method 'fromEntity'");
    }
}
