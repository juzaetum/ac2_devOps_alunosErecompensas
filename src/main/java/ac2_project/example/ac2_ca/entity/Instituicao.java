package ac2_project.example.ac2_ca.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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

    public Instituicao(String nome, String endereco, String cnpj) {
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
}
