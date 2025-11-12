package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.entity.Recompensa;
import ac2_project.example.ac2_ca.repository.Recompensa_Repository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class RecompensaServiceTest {

    @Mock
    private Recompensa_Repository recompensaRepository;

    @InjectMocks
    private RecompensaService recompensaService;

    private Recompensa r1;
    private Recompensa r2;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
        r1 = new Recompensa(1L, 200f, "Bolsa", "Engenharia");
        r2 = new Recompensa(2L, 100f, "Certificado", "Computação");
    }

    // GET ALL
    @Test
    void deveRetornarTodasAsRecompensas() {
        when(recompensaRepository.findAll()).thenReturn(Arrays.asList(r1, r2));

        List<Recompensa> resultado = recompensaService.getAllRecompensas();

        assertThat(resultado).hasSize(2);
        verify(recompensaRepository, times(1)).findAll();
    }

    // GET BY ID
    @Test
    void deveRetornarRecompensaPorId() {
        when(recompensaRepository.findById(1L)).thenReturn(Optional.of(r1));

        Optional<Recompensa> resultado = recompensaService.getRecompensaById(1L);

        assertThat(resultado).isPresent();
        assertThat(resultado.get().getNome()).isEqualTo("Bolsa");
        verify(recompensaRepository).findById(1L);
    }

    @Test
    void deveRetornarVazioSeIdNaoExistir() {
        when(recompensaRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Recompensa> resultado = recompensaService.getRecompensaById(99L);

        assertThat(resultado).isEmpty();
    }

    // CREATE
    @Test
    void deveCriarRecompensa() {
        when(recompensaRepository.save(any(Recompensa.class))).thenReturn(r1);

        Recompensa criada = recompensaService.createRecompensa(r1);

        assertThat(criada.getNome()).isEqualTo("Bolsa");
        verify(recompensaRepository).save(r1);
    }

    // UPDATE
    @Test
    void deveAtualizarRecompensaExistente() {
        Recompensa nova = new Recompensa(null, 500f, "Nova Bolsa", "Engenharia");

        when(recompensaRepository.findById(1L)).thenReturn(Optional.of(r1));
        when(recompensaRepository.save(any(Recompensa.class))).thenReturn(r1);

        Optional<Recompensa> atualizada = recompensaService.updateRecompensa(1L, nova);

        assertThat(atualizada).isPresent();
        assertThat(atualizada.get().getValor()).isEqualTo(500f);
        assertThat(atualizada.get().getNome()).isEqualTo("Nova Bolsa");

        verify(recompensaRepository).findById(1L);
        verify(recompensaRepository).save(any(Recompensa.class));
    }

    @Test
    void deveRetornarVazioAoAtualizarRecompensaInexistente() {
        when(recompensaRepository.findById(99L)).thenReturn(Optional.empty());

        Optional<Recompensa> resultado = recompensaService.updateRecompensa(99L, r1);

        assertThat(resultado).isEmpty();
        verify(recompensaRepository, never()).save(any());
    }

    // DELETE
    @Test
    void deveDeletarRecompensaSeExistir() {
        when(recompensaRepository.existsById(1L)).thenReturn(true);

        boolean resultado = recompensaService.deleteRecompensa(1L);

        assertThat(resultado).isTrue();
        verify(recompensaRepository).deleteById(1L);
    }

    @Test
    void naoDeveDeletarSeNaoExistir() {
        when(recompensaRepository.existsById(99L)).thenReturn(false);

        boolean resultado = recompensaService.deleteRecompensa(99L);

        assertThat(resultado).isFalse();
        verify(recompensaRepository, never()).deleteById(any());
    }
}
