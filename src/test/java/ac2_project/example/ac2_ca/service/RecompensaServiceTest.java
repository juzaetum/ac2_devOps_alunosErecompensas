package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.entity.Aluno;
import ac2_project.example.ac2_ca.entity.AlunoRA;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class RecompensaServiceTest {

    private RecompensaService recompensaService;
    private Aluno aluno;

    @BeforeEach
    void setUp() {
        recompensaService = new RecompensaService();
        aluno = new Aluno(new AlunoRA("123456"), "Engenharia", 8.5f);
    }

    @Test
    void naoDeveConcederRecompensaQuandoMediaForMenorOuIgualASete() {
        aluno.setMedia(6.5f);
        aluno.setTopicosCriados(10);
        aluno.setComentariosFeitos(10);

        recompensaService.calcularRecompensa(aluno);

        assertEquals(0, aluno.getCursosExtrasRecebidos(),
                "Não deve conceder recompensa se a média for <= 7.0");
    }

    @Test
    void deveConcederRecompensaQuandoMediaMaiorQueSete() {
        aluno.setMedia(8.0f);
        aluno.setTopicosCriados(5);
        aluno.setComentariosFeitos(0);

        recompensaService.calcularRecompensa(aluno);

        assertEquals(1, aluno.getCursosExtrasRecebidos(),
                "Deve conceder 1 curso extra a cada 5 interações com média > 7");
    }

    @Test
    void deveConcederMultiplasRecompensasParaMuitasInteracoes() {
        aluno.setMedia(9.0f);
        aluno.setTopicosCriados(12);
        aluno.setComentariosFeitos(8); // total 20 interações

        recompensaService.calcularRecompensa(aluno);

        assertEquals(4, aluno.getCursosExtrasRecebidos(),
                "Deve conceder 1 curso extra a cada 5 interações com média > 7");
    }

    @Test
    void naoDeveReduzirRecompensasQuandoInteracoesDiminuem() {
        aluno.setMedia(9.0f);
        aluno.setTopicosCriados(10);
        aluno.setComentariosFeitos(5);
        recompensaService.calcularRecompensa(aluno); // 15 interações → 3 cursos

        aluno.setTopicosCriados(2);
        aluno.setComentariosFeitos(1);
        recompensaService.calcularRecompensa(aluno); // 3 interações → 0 cursos novos

        assertEquals(3, aluno.getCursosExtrasRecebidos(),
                "Recompensas não devem ser reduzidas se as interações diminuírem");
    }

    @Test
    void deveZerarRecompensasQuandoMediaForAbaixadaParaMenorOuIgualASete() {
        aluno.setMedia(8.5f);
        aluno.setTopicosCriados(10);
        aluno.setComentariosFeitos(5);
        recompensaService.calcularRecompensa(aluno);

        aluno.setMedia(6.0f);
        recompensaService.calcularRecompensa(aluno);

        assertEquals(0, aluno.getCursosExtrasRecebidos(),
                "Deve remover recompensas se a média cair para <= 7.0");
    }
}
