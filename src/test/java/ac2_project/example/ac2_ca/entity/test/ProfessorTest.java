package ac2_project.example.ac2_ca.entity.test;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ac2_project.example.ac2_ca.entity.*;

import static org.junit.jupiter.api.Assertions.*;

class ProfessorTest {

    private Instituicao instituicao;
    private Professor_RA raValido;
    private Professor professor;

    @BeforeEach
    void setUp() {
        InstituicaoCNPJ cnpj = new InstituicaoCNPJ("12345678000190");
        instituicao = new Instituicao("Fatec Sorocaba", "Av. Eng. Carlos Reinaldo Mendes", cnpj);

        // supondo que o Professor_RA valide algo simples como número do RA
        raValido = new Professor_RA("991235");
        professor = new Professor(raValido, instituicao);
    }

    @Test
    void deveCriarProfessorComRaEInstituicao() {
        assertNotNull(professor);
        assertEquals(raValido, professor.getRa());
        assertEquals(instituicao, professor.getInstituicao());
    }

    @Test
    void devePermitirAlterarRa() {
        Professor_RA novoRa = new Professor_RA("885421");
        professor.setRa(novoRa);
        assertEquals(novoRa, professor.getRa());
    }

    @Test
    void devePermitirAlterarInstituicao() {
        Instituicao novaInstituicao = new Instituicao(
                "Fatec Campinas",
                "Rua Exemplo, 123",
                new InstituicaoCNPJ("98765432000188"));

        professor.setInstituicao(novaInstituicao);
        assertEquals("Fatec Campinas", professor.getInstituicao().getNome());
    }

    @Test
    void toStringDeveConterNomeDaInstituicao() {
        String resultado = professor.toString();
        assertTrue(resultado.contains("Fatec Sorocaba"));
        assertTrue(resultado.contains("Professor"));
    }

    @Test
    void toStringDeveIndicarSemInstituicaoQuandoNula() {
        Professor professorSemInst = new Professor(raValido, null);
        String resultado = professorSemInst.toString();
        assertTrue(resultado.contains("Sem Instituição"));
    }

    @Test
    void devePermitirSettersEGettersBasicos() {
        professor.setRa(raValido);
        professor.setInstituicao(instituicao);

        assertEquals(raValido, professor.getRa());
        assertEquals(instituicao, professor.getInstituicao());
        assertNull(professor.getId()); 
    }
    
    @Test
    void construtorPadraoDeveCriarProfessorComCamposNulos() {
        Professor novoProfessor = new Professor();
        assertNull(novoProfessor.getId());
        assertNull(novoProfessor.getRa());
        assertNull(novoProfessor.getInstituicao());
    }

    @Test
    void toStringDeveConterRaQuandoNaoNulo() {
        String resultado = professor.toString();
        assertTrue(resultado.contains(raValido.toString()),
                "toString deve conter o RA quando não for nulo");
    }

    @Test
    void toStringNaoDeveLancarExcecaoQuandoTudoForNulo() {
        Professor professorNulo = new Professor();
        assertDoesNotThrow(() -> professorNulo.toString());
        String resultado = professorNulo.toString();
        assertTrue(resultado.contains("Professor{"));
    }

    @Test
    void devePermitirDefinirInstituicaoComoNula() {
        professor.setInstituicao(null);
        assertNull(professor.getInstituicao());
        String resultado = professor.toString();
        assertTrue(resultado.contains("Sem Instituição"));
    }

    @Test
    void deveCompararReferenciasDeInstituicaoCorretamente() {
        Instituicao mesma = professor.getInstituicao();
        professor.setInstituicao(mesma);
        assertSame(mesma, professor.getInstituicao());
    }

    @Test
    void devePermitirAlterarRaParaNulo() {
        professor.setRa(null);
        assertNull(professor.getRa());
        String resultado = professor.toString();
        assertTrue(resultado.contains("Professor"));
    }

}
