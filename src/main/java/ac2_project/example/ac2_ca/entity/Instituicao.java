package ac2_project.example.ac2_ca.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_instituicao")
public class Instituicao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String endereco;
    private boolean ativa;

    @Embedded
    private InstituicaoCNPJ cnpj;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "instituicao_id")
    private List<Curso> cursos = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "instituicao_id")
    private List<Aluno> alunos = new ArrayList<>();

    public Instituicao(int numero, String nome, String endereco, String cnpj) {
        this.nome = nome;
        this.endereco = endereco;
        this.cnpj = new InstituicaoCNPJ(cnpj);
        this.ativa = true;
    }

    public Instituicao(String nome, String endereco, InstituicaoCNPJ cnpj) {
        if (nome == null || nome.isBlank()) {
            throw new IllegalArgumentException("O nome da instituição não pode estar vazio.");
        }
        this.nome = nome;
        this.endereco = endereco;
        this.cnpj = cnpj;
        this.ativa = true;
    }

    public void adicionarCurso(Curso curso) {
        cursos.add(curso);
    }

    public void adicionarAluno(Aluno aluno) {
        alunos.add(aluno);
    }

    public void removerCurso(Curso curso) {
        cursos.remove(curso);
    }

    public void removerAluno(Aluno aluno) {
        alunos.remove(aluno);
    }

    public void desativar() {
        this.ativa = false;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public InstituicaoCNPJ getCnpj() {
        return cnpj;
    }

    public void setCnpj(InstituicaoCNPJ cnpj) {
        this.cnpj = cnpj;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public boolean isAtiva() {
        return ativa;
    }

    public void setAtiva(boolean ativa) {
        this.ativa = ativa;
    }

    public List<Curso> getCursos() {
        return cursos;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    }
}
