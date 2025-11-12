package ac2_project.example.ac2_ca.repository.test;

import ac2_project.example.ac2_ca.entity.Recompensa;
import ac2_project.example.ac2_ca.repository.Recompensa_Repository;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@EntityScan(basePackages = "ac2_project.example.ac2_ca.entity")
@EnableJpaRepositories(basePackages = "ac2_project.example.ac2_ca.repository")
@DataJpaTest
class RecompensaRepositoryTest {

    @Autowired
    private Recompensa_Repository recompensaRepository;

    private Recompensa r1, r2, r3;

    @BeforeEach
    void setUp() {
        r1 = new Recompensa(null, 500f, "Bolsa de Estudos", "Curso ADS");
        r2 = new Recompensa(null, 1000f, "Certificado Ouro", "Curso Engenharia");
        r3 = new Recompensa(null, 200f, "Desconto Especial", "Curso ADS");

        recompensaRepository.saveAll(List.of(r1, r2, r3));
    }

    @Test
    @DisplayName("Deve buscar recompensas contendo parte do nome (ignora maiúsculas/minúsculas)")
    void testFindByNomeContainingIgnoreCase() {
        List<Recompensa> resultados = recompensaRepository.findByNomeContainingIgnoreCase("bolsa");

        assertThat(resultados).hasSize(1);
        assertThat(resultados.get(0).getNome()).isEqualTo("Bolsa de Estudos");
    }

    @Test
    @DisplayName("Deve buscar recompensas dentro de um intervalo de valores")
    void testFindByValorBetween() {
        List<Recompensa> resultados = recompensaRepository.findByValorBetween(300f, 1000f);

        assertThat(resultados).hasSize(2);
        assertThat(resultados)
                .extracting(Recompensa::getNome)
                .containsExactlyInAnyOrder("Bolsa de Estudos", "Certificado Ouro");
    }

    @Test
    @DisplayName("Deve buscar recompensas por título do curso")
    void testFindByCursoTitulo() {
        List<Recompensa> resultados = recompensaRepository.findByCursoTitulo("Curso ADS");

        assertThat(resultados).hasSize(2);
        assertThat(resultados)
                .extracting(Recompensa::getNome)
                .containsExactlyInAnyOrder("Bolsa de Estudos", "Desconto Especial");
    }

    @Test
    @DisplayName("Deve retornar as 10 maiores recompensas por valor")
    void testFindTop10ByOrderByValorDesc() {
        List<Recompensa> resultados = recompensaRepository.findTop10ByOrderByValorDesc();

        assertThat(resultados).isNotEmpty();
        assertThat(resultados.get(0).getValor()).isEqualTo(1000f);
    }
}
