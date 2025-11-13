package ac2_project.example.ac2_ca.entity;

import jakarta.persistence.*;
import lombok.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_curso")
public class Curso {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String descricao;
    private String professor;
    private boolean ativo;

    @OneToMany
    private List<Aluno> alunos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "tb_curso_aulas", joinColumns = @JoinColumn(name = "curso_id"))
    private List<CursoAula> aulas = new ArrayList<>();

    // Construtores adicionais personalizados
    public Curso(String titulo, String descricao, String professor) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.professor = professor;
        this.ativo = true;
    }

    public Curso(String string, int RA_curso) {
        this.titulo = string;
        this.id = (long) RA_curso;
    }

    // Métodos personalizados
    public void adicionarAluno(Aluno aluno) {
        this.alunos.add(aluno);
    }

    public void adicionarAula(CursoAula aula) {
        this.aulas.add(aula);
    }

    public void removerAula(CursoAula aula) {
        this.aulas.remove(aula);
    }

    public int getQuantidadeAulas() {
        return aulas.size();
    }

    public void desativar() {
        this.ativo = false;
    }
}
