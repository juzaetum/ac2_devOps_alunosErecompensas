package ac2_project.example.ac2_ca.repository.test;

import ac2_project.example.ac2_ca.entity.Recompensa;
import ac2_project.example.ac2_ca.repository.Recompensa_Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class RecompensaRepositoryTest {

    @Autowired
    private Recompensa_Repository recompensaRepository;

    private Recompensa recompensa1;
    private Recompensa recompensa2;
    private Recompensa recompensa3;

    @BeforeEach
    void setup() {
        recompensa1 = new Recompensa(100f, "Melhor Aluno", "Engenharia");
        recompensa2 = new Recompensa(200f, "Participação", "Computação");
        recompensa3 = new Recompensa(300f, "Desempenho", "Engenharia");

        recompensaRepository.save(recompensa1);
        recompensaRepository.save(recompensa2);
        recompensaRepository.save(recompensa3);
    }

    @Test
    @DisplayName("Deve encontrar recompensas contendo parte do nome (ignorando maiúsculas/minúsculas)")
    void testFindByNomeContainingIgnoreCase() {
        List<Recompensa> resultados = recompensaRepository.findByNomeContainingIgnoreCase("melhor");
        assertThat(resultados).hasSize(1);
        assertThat(resultados.get(0).getNome()).isEqualTo("Melhor Aluno");
    }

    @Test
    @DisplayName("Deve encontrar recompensas dentro de um intervalo de valores")
    void testFindByValorBetween() {
        List<Recompensa> resultados = recompensaRepository.findByValorBetween(150f, 350f);
        assertThat(resultados).hasSize(2);
        assertThat(resultados).extracting(Recompensa::getValor).contains(200f, 300f);
    }

    @Test
    @DisplayName("Deve encontrar recompensas pelo curso_titulo via @Query")
    void testFindByCursoTitulo() {
        List<Recompensa> resultados = recompensaRepository.findByCursoTitulo("Engenharia");
        assertThat(resultados).hasSize(2);
        assertThat(resultados).extracting(Recompensa::getCurso_titulo).containsOnly("Engenharia");
    }

    @Test
    @DisplayName("Deve retornar as 10 recompensas com maior valor (em ordem decrescente)")
    void testFindTop10ByOrderByValorDesc() {
        List<Recompensa> resultados = recompensaRepository.findTop10ByOrderByValorDesc();
        assertThat(resultados).isNotEmpty();
        assertThat(resultados.get(0).getValor()).isEqualTo(300f);
        assertThat(resultados.get(1).getValor()).isEqualTo(200f);
    }
}
