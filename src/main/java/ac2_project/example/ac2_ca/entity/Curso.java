package ac2_project.example.ac2_ca.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

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
    private List<Aluno> alunos = new ArrayList<>();

    @ElementCollection
    @CollectionTable(name = "tb_curso_aulas", joinColumns = @JoinColumn(name = "curso_id"))
    private List<CursoAula> aulas = new ArrayList<>();


    public Curso(String titulo, String descricao, String professor) {
        this.titulo = titulo;
        this.descricao = descricao;
        this.professor = professor;
        this.ativo = true;
    }

    public Curso(String string, int i) {
        this.titulo = string;
    }

    public Curso() {
        //TODO Auto-generated constructor stub
    }

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


    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    public List<Aluno> getAlunos() {
        return alunos;
    
    }
    
    public boolean isAtivo() {
        return ativo;
    }

    public void setAtivo(boolean ativo) {
        this.ativo = ativo;
    }

    public List<CursoAula> getAulas() {
        return aulas;
    }

    public void setAulas(List<CursoAula> aulas) {
        this.aulas = aulas;
    }
}
