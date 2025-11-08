package ac2_project.example.ac2_ca.entity.test;

import ac2_project.example.ac2_ca.entity.Aluno;
import ac2_project.example.ac2_ca.entity.Curso;
import ac2_project.example.ac2_ca.entity.Instituicao;
import ac2_project.example.ac2_ca.entity.InstituicaoCNPJ;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class InstituicaoTest {

    private InstituicaoCNPJ cnpjValido;
    private Instituicao instituicao;

    @BeforeEach
    void setUp() {
        cnpjValido = new InstituicaoCNPJ("12345678000190");
        instituicao = new Instituicao("Fatec Sorocaba", "Av. Eng. Carlos Reinaldo Mendes", cnpjValido);
    }

    @Test
    void deveCriarInstituicaoComCnpjString() {
        Instituicao outra = new Instituicao("Fatec Itapetininga", "Rua Central, 100", "98765432000199");
        assertEquals("Fatec Itapetininga", outra.getNome());
        assertEquals("Rua Central, 100", outra.getEndereco());
        assertTrue(outra.isAtiva());
        assertNotNull(outra.getCnpj());
    }

    @Test
    void deveCriarInstituicaoComDadosValidos() {
        assertEquals("Fatec Sorocaba", instituicao.getNome());
        assertEquals("Av. Eng. Carlos Reinaldo Mendes", instituicao.getEndereco());
        assertTrue(instituicao.isAtiva());
        assertEquals(cnpjValido, instituicao.getCnpj());
    }

    @Test
    void deveLancarExcecaoQuandoNomeForVazioOuNulo() {
        assertThrows(IllegalArgumentException.class, () -> new Instituicao("", "Rua A", cnpjValido));
        assertThrows(IllegalArgumentException.class, () -> new Instituicao("   ", "Rua A", cnpjValido));
        assertThrows(IllegalArgumentException.class, () -> new Instituicao(null, "Rua A", cnpjValido));
    }

    @Test
    void deveAdicionarERemoverCurso() {
        Curso curso = new Curso("Engenharia de Computação", 8);
        instituicao.adicionarCurso(curso);
        assertEquals(1, instituicao.getCursos().size());
        assertTrue(instituicao.getCursos().contains(curso));

        instituicao.removerCurso(curso);
        assertTrue(instituicao.getCursos().isEmpty());
    }

    @Test
    void devePermitirAdicionarMesmoCursoVariasVezes() {
        Curso curso = new Curso("ADS", 6);
        instituicao.adicionarCurso(curso);
        instituicao.adicionarCurso(curso);
        assertEquals(2, instituicao.getCursos().size());
    }

    @Test
    void deveAdicionarERemoverAluno() {
        Aluno aluno = new Aluno("Juliane", "juliane@email.com");
        instituicao.adicionarAluno(aluno);
        assertEquals(1, instituicao.getAlunos().size());
        assertTrue(instituicao.getAlunos().contains(aluno));

        instituicao.removerAluno(aluno);
        assertTrue(instituicao.getAlunos().isEmpty());
    }

    @Test
    void devePermitirAdicionarMesmoAlunoVariasVezes() {
        Aluno aluno = new Aluno("Ana", "ana@email.com");
        instituicao.adicionarAluno(aluno);
        instituicao.adicionarAluno(aluno);
        assertEquals(2, instituicao.getAlunos().size());
    }

    @Test
    void deveDesativarEReativarInstituicao() {
        instituicao.desativar();
        assertFalse(instituicao.isAtiva());

        instituicao.setAtiva(true);
        assertTrue(instituicao.isAtiva());
    }

    @Test
    void deveAlterarDadosComSetters() {
        InstituicaoCNPJ novoCnpj = new InstituicaoCNPJ("11111111000111");
        instituicao.setNome("Fatec Zona Norte");
        instituicao.setEndereco("Rua das Flores, 123");
        instituicao.setCnpj(novoCnpj);
        instituicao.setAtiva(false);

        assertEquals("Fatec Zona Norte", instituicao.getNome());
        assertEquals("Rua das Flores, 123", instituicao.getEndereco());
        assertEquals(novoCnpj, instituicao.getCnpj());
        assertFalse(instituicao.isAtiva());
    }

    @Test
    void listasDevemSerInicializadasVazias() {
        assertNotNull(instituicao.getCursos());
        assertNotNull(instituicao.getAlunos());
        assertTrue(instituicao.getCursos().isEmpty());
        assertTrue(instituicao.getAlunos().isEmpty());
    }

    @Test
    void devePermitirSetarCnpjNulo() {
        instituicao.setCnpj(null);
        assertNull(instituicao.getCnpj());
    }

    @Test
    void deveRetornarIdNuloPorPadrao() {
        assertNull(instituicao.getId());
    }
}
