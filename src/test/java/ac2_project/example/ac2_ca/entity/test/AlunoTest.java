package ac2_project.example.ac2_ca.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ac2_project.example.ac2_ca.entity.Aluno;
import ac2_project.example.ac2_ca.entity.AlunoRA;

public class AlunoTest {

    @Test
    void testCriacaoAlunoComRaValido() {
        AlunoRA ra = new AlunoRA("123456");
        Aluno aluno = new Aluno(ra, "Engenharia", 8.5f);

        assertEquals(ra, aluno.getRa());
        assertEquals("Engenharia", aluno.getCurso());
        assertEquals(8.5f, aluno.getMedia());
    }

    @Test
    void testCriacaoAlunoComRaInvalido() {
        assertThrows(IllegalArgumentException.class, () -> {
            new AlunoRA("12A456");
        });
        assertThrows(IllegalArgumentException.class, () -> {
            new AlunoRA("12345"); // menos de 6 dígitos
        });
    }

    @Test
    void testIncrementoDeTopicosEAtribuicaoDeRecompensa() {
        Aluno aluno = new Aluno(new AlunoRA("654321"), "Computação", 9.0f);

        // cria 5 interações => deve ganhar 1 curso extra
        for (int i = 0; i < 5; i++) {
            aluno.criarTopico();
        }

        assertEquals(5, aluno.getTopicosCriados());
        assertEquals(1, aluno.getCursosExtrasRecebidos());
    }

    @Test
    void testComentariosContamParaRecompensa() {
        Aluno aluno = new Aluno(new AlunoRA("654321"), "Computação", 9.0f);

        for (int i = 0; i < 3; i++)
            aluno.criarTopico();
        for (int i = 0; i < 2; i++)
            aluno.fazerComentario();

        assertEquals(3, aluno.getTopicosCriados());
        assertEquals(2, aluno.getComentariosFeitos());
        assertEquals(1, aluno.getCursosExtrasRecebidos());
    }

    @Test
    void testNaoRecebeRecompensaComMediaBaixa() {
        Aluno aluno = new Aluno(new AlunoRA("111111"), "ADS", 6.0f);

        for (int i = 0; i < 10; i++)
            aluno.criarTopico();
        assertEquals(0, aluno.getCursosExtrasRecebidos());
    }

    @Test
    void testAlterarMediaAfetaRecompensa() {
        Aluno aluno = new Aluno(new AlunoRA("222222"), "Engenharia", 6.0f);

        // não deveria receber cursos ainda
        for (int i = 0; i < 10; i++)
            aluno.criarTopico();
        assertEquals(0, aluno.getCursosExtrasRecebidos());

        // agora aumenta a média e deve recalcular a recompensa
        aluno.setMedia(8.0f);
        assertEquals(2, aluno.getCursosExtrasRecebidos());
    }

    @Test
    void testSetRaComValidacao() {
        Aluno aluno = new Aluno(new AlunoRA("123456"));
        aluno.setRa(new AlunoRA("654321"));
        assertEquals("654321", aluno.getRa().getNumero());
    }
}
