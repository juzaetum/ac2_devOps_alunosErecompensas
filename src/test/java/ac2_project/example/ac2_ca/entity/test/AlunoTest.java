package ac2_project.example.ac2_ca.entity.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ac2_project.example.ac2_ca.entity.Aluno;
import ac2_project.example.ac2_ca.entity.AlunoRA;
import ac2_project.example.ac2_ca.entity.Curso; // só se existir essa classe no teu projeto

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
        assertThrows(IllegalArgumentException.class, () -> new AlunoRA("12A456"));
        assertThrows(IllegalArgumentException.class, () -> new AlunoRA("12345"));
    }

    @Test
    void testIncrementoDeTopicosEAtribuicaoDeRecompensa() {
        Aluno aluno = new Aluno(new AlunoRA("654321"), "Computação", 9.0f);

        for (int i = 0; i < 5; i++)
            aluno.criarTopico();

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

        for (int i = 0; i < 10; i++)
            aluno.criarTopico();
        assertEquals(0, aluno.getCursosExtrasRecebidos());

        aluno.setMedia(8.0f);
        assertEquals(2, aluno.getCursosExtrasRecebidos());
    }

    @Test
    void testSetRaComValidacao() {
        Aluno aluno = new Aluno(new AlunoRA("123456"));
        aluno.setRa(new AlunoRA("654321"));
        assertEquals("654321", aluno.getRa().getNumero());
    }

    @Test
    void testConstrutorComNomeEEmail() {
        Aluno aluno = new Aluno("Juliane", "juliane@example.com");

        assertEquals("Juliane", aluno.getNome());
        assertEquals("juliane@example.com", aluno.getEmail());
        assertEquals(0, aluno.getTopicosCriados());
        assertEquals(0, aluno.getComentariosFeitos());
        assertEquals(0, aluno.getCursosExtrasRecebidos());
        assertEquals(0.0f, aluno.getMedia());
    }

    @Test
    void testSettersEGettersBasicos() {
        Aluno aluno = new Aluno(new AlunoRA("999999"));
        aluno.setCurso(new Curso("Engenharia da Computação", 1));
        aluno.setTopicosCriados(3);
        aluno.setComentariosFeitos(2);
        aluno.setCursosExtrasRecebidos(1);
        aluno.setNome("Carlos");
        aluno.setEmail("carlos@example.com");

        assertEquals("Engenharia da Computação", aluno.getCurso());
        assertEquals(3, aluno.getTopicosCriados());
        assertEquals(2, aluno.getComentariosFeitos());
        assertEquals(1, aluno.getCursosExtrasRecebidos());
        assertEquals("Carlos", aluno.getNome());
        assertEquals("carlos@example.com", aluno.getEmail());
    }

    @Test
    void testVerificarRecompensaNaoAumentaSemInteracoesNovas() {
        Aluno aluno = new Aluno(new AlunoRA("333333"), "Computação", 9.0f);

        for (int i = 0; i < 5; i++)
            aluno.fazerComentario();
        assertEquals(1, aluno.getCursosExtrasRecebidos());

        // cria mais 2 comentários, mas não suficientes pra nova recompensa
        aluno.fazerComentario();
        aluno.fazerComentario();
        assertEquals(1, aluno.getCursosExtrasRecebidos());
    }

    @Test
    void testConstrutorComNomeAlunoECurso() {
        // só funciona se houver classe Curso
        Curso curso = new Curso("ADS", 1);
        Aluno aluno = new Aluno("Juliane", curso);

        assertEquals("ADS", aluno.getCurso());
        assertEquals("Juliane", aluno.getNome());
        assertEquals(0.0f, aluno.getMedia());
    }

    @Test
    void testVerificarRecompensaComMediaLimite() {
        Aluno aluno = new Aluno(new AlunoRA("444444"), "Computação", 7.0f);
        aluno.criarTopico();
        aluno.fazerComentario();

        // média = 7.0f => sem recompensa
        assertEquals(0, aluno.getCursosExtrasRecebidos());

        // agora aumenta a média pra liberar recompensas
        aluno.setMedia(8.0f);
        for (int i = 0; i < 8; i++)
            aluno.fazerComentario();
        assertTrue(aluno.getCursosExtrasRecebidos() >= 1);
    }
}
