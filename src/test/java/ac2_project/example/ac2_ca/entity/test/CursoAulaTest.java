package ac2_project.example.ac2_ca.entity.test;

import ac2_project.example.ac2_ca.entity.Aluno;
import ac2_project.example.ac2_ca.entity.AlunoRA;
import ac2_project.example.ac2_ca.repository.AlunoRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.Optional;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
class AlunoRepositoryTest {

    @Autowired
    private AlunoRepository alunoRepository;

    @Test
    @DisplayName("Deve salvar um aluno no banco de dados")
    void deveSalvarAluno() {
        Aluno aluno = new Aluno(new AlunoRA("12345"), "Engenharia", 8.5f);
        aluno.setNome("Juliane");
        aluno.setEmail("juliane@example.com");

        Aluno salvo = alunoRepository.save(aluno);

        assertThat(salvo).isNotNull();
        assertThat(salvo.getId()).isNotNull();
        assertThat(salvo.getNome()).isEqualTo("Juliane");
        assertThat(salvo.getCurso()).isEqualTo("Engenharia");
    }

    @Test
    @DisplayName("Deve encontrar um aluno pelo ID")
    void deveEncontrarAlunoPorId() {
        Aluno aluno = new Aluno(new AlunoRA("98765"), "Computação", 9.0f);
        Aluno salvo = alunoRepository.save(aluno);

        Optional<Aluno> encontrado = alunoRepository.findById(salvo.getId());

        assertThat(encontrado).isPresent();
        assertThat(encontrado.get().getCurso()).isEqualTo("Computação");
    }

    @Test
    @DisplayName("Deve atualizar um aluno existente")
    void deveAtualizarAluno() {
        Aluno aluno = new Aluno(new AlunoRA("55555"), "Engenharia", 7.5f);
        aluno.setNome("Lucas");
        Aluno salvo = alunoRepository.save(aluno);

        salvo.setCurso("ADS");
        salvo.setMedia(8.0f);
        Aluno atualizado = alunoRepository.save(salvo);

        assertThat(atualizado.getCurso()).isEqualTo("ADS");
        assertThat(atualizado.getMedia()).isEqualTo(8.0f);
    }

    @Test
    @DisplayName("Deve deletar um aluno do banco")
    void deveDeletarAluno() {
        Aluno aluno = new Aluno(new AlunoRA("11111"), "ADS", 7.5f);
        Aluno salvo = alunoRepository.save(aluno);

        alunoRepository.deleteById(salvo.getId());
        Optional<Aluno> resultado = alunoRepository.findById(salvo.getId());

        assertThat(resultado).isEmpty();
    }

    @Test
    @DisplayName("Deve listar todos os alunos")
    void deveListarTodosOsAlunos() {
        Aluno a1 = new Aluno(new AlunoRA("22222"), "Engenharia", 8.0f);
        Aluno a2 = new Aluno(new AlunoRA("33333"), "ADS", 9.0f);

        alunoRepository.save(a1);
        alunoRepository.save(a2);

        List<Aluno> lista = alunoRepository.findAll();

        assertThat(lista).isNotEmpty();
        assertThat(lista.size()).isGreaterThanOrEqualTo(2);
    }

    @Test
    @DisplayName("Deve retornar vazio ao buscar aluno inexistente")
    void deveRetornarVazioQuandoNaoExistirAluno() {
        Optional<Aluno> resultado = alunoRepository.findById(999L);
        assertThat(resultado).isEmpty();
    }
}
