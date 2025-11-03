package ac2_project.example.ac2_ca.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "tb_aluno")
public class Aluno {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int topicosCriados;
    private int comentariosFeitos;
    private int cursosExtrasRecebidos;

    @Embedded
    private AlunoRA ra;

    @Column(name = "curso")
    private String curso;

    @Column(name = "media")
    private float media;

    public Aluno(int i, String string, Curso curso2, Instituicao inst) {
        this.curso = "";
        this.media = 0.0f;
    }

    public Aluno(AlunoRA ra) {
        this.ra = ra;
        this.topicosCriados = 0;
        this.comentariosFeitos = 0;
        this.cursosExtrasRecebidos = 0;
        this.curso = "";
        this.media = 0.0f;
    }

    public Aluno(AlunoRA ra, String curso, float media) {
        this.ra = ra;
        this.topicosCriados = 0;
        this.comentariosFeitos = 0;
        this.cursosExtrasRecebidos = 0;
        this.curso = curso;
        this.media = media;
        verificarRecompensa();
    }
    
    public void criarTopico() {
        this.topicosCriados++;
        verificarRecompensa();
    }

    public void fazerComentario() {
        this.comentariosFeitos++;
        verificarRecompensa();
    }

    private void verificarRecompensa() {
        if (this.media <= 7.0f) {
            return;
        }
        int interacoes = topicosCriados + comentariosFeitos;
        int novosCursos = interacoes / 5;
        if (novosCursos > cursosExtrasRecebidos) {
            cursosExtrasRecebidos = novosCursos;
        }
    }

    public Long getId() {
        return id;
    }

    public AlunoRA getRa() {
        return ra;
    }

    public void setRa(AlunoRA ra) {
        this.ra = ra;
    }

    public String getCurso() {
        return curso;
    }

    public void setCurso(String curso) {
        this.curso = curso;
    }

    public float getMedia() {
        return media;
    }

    public void setMedia(float media) {
        this.media = media;
        verificarRecompensa();
    }

    public int getTopicosCriados() {
        return topicosCriados;
    }

    public void setTopicosCriados(int topicosCriados) {
        this.topicosCriados = topicosCriados;
    }

    public int getComentariosFeitos() {
        return comentariosFeitos;
    }

    public void setComentariosFeitos(int comentariosFeitos) {
        this.comentariosFeitos = comentariosFeitos;
    }

    public int getCursosExtrasRecebidos() {
        return cursosExtrasRecebidos;
    }

    public void setCursosExtrasRecebidos(int cursosExtrasRecebidos) {
        this.cursosExtrasRecebidos = cursosExtrasRecebidos;
    }
}
