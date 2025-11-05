/*package ac2_project.example.ac2_ca.repository.test;

import ac2_project.example.ac2_ca.entity.Aluno;
import ac2_project.example.ac2_ca.entity.AlunoRA;
import ac2_project.example.ac2_ca.repository.AlunoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

class AlunoRepositoryTest {

    @Mock
    private AlunoRepository alunoRepository;

    private Aluno aluno;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        aluno = new Aluno(new AlunoRA("12345"), "Engenharia", 8.5f);
        aluno.setNome("Juliane");
        aluno.setEmail("juliane@example.com");
    }

    @Test
    @DisplayName("Deve salvar um aluno corretamente")
    void deveSalvarAluno() {
        when(alunoRepository.save(any(Aluno.class))).thenReturn(aluno);

        Aluno salvo = alunoRepository.save(aluno);

        assertThat(salvo).isNotNull();
        assertThat(salvo.getNome()).isEqualTo("Juliane");
        assertThat(salvo.getCurso()).isEqualTo("Engenharia");

        verify(alunoRepository, times(1)).save(aluno);
    }

    @Test
    @DisplayName("Deve encontrar um aluno pelo ID")
    void deveEncontrarAlunoPorId() {
        when(alunoRepository.findById(1L)).thenReturn(Optional.of(aluno));

        Optional<Aluno> encontrado = alunoRepository.findById(1L);

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getRa().getValor()).isEqualTo("12345");
        verify(alunoRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("Deve deletar um aluno pelo ID")
    void deveDeletarAluno() {
        doNothing().when(alunoRepository).deleteById(1L);

        alunoRepository.deleteById(1L);

        verify(alunoRepository, times(1)).deleteById(1L);
    }

    @Test
    @DisplayName("Deve listar todos os alunos")
    void deveListarTodosOsAlunos() {
        Aluno a1 = new Aluno(new AlunoRA("11111"), "Computação", 9.0f);
        Aluno a2 = new Aluno(new AlunoRA("22222"), "ADS", 8.0f);
        List<Aluno> listaMock = new ArrayList<>(List.of(a1, a2));

        when(alunoRepository.findAll()).thenReturn(listaMock);

        var lista = alunoRepository.findAll();

        assertThat(lista).isNotEmpty();
        assertThat(lista).hasSize(2);
        verify(alunoRepository, times(1)).findAll();
    }
}
*/