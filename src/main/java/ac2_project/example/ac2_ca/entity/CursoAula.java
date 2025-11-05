package ac2_project.example.ac2_ca.entity;

import java.util.function.IntPredicate;

import jakarta.persistence.Embeddable;

@Embeddable
public class CursoAula {

    private String titulo;
    private int duracaoMinutos; 
    private String conteudoResumo;
    private boolean obrigatoria;

    public CursoAula() {
    }

    public CursoAula(String titulo, int duracaoMinutos, String conteudoResumo, boolean obrigatoria) {
        if (titulo == null || titulo.isBlank()) {
            throw new IllegalArgumentException("O título da aula não pode estar vazio.");
        }
        if (duracaoMinutos <= 0) {
            throw new IllegalArgumentException("A duração da aula deve ser positiva.");
        }

        this.titulo = titulo;
        this.duracaoMinutos = duracaoMinutos;
        this.conteudoResumo = conteudoResumo;
        this.obrigatoria = obrigatoria;
    }

    public String getTitulo() {
        return titulo;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public String getConteudoResumo() {
        return conteudoResumo;
    }

    public boolean isObrigatoria() {
        return obrigatoria;
    }

    @Override
    public String toString() {
        return titulo + " (" + duracaoMinutos + " min)";
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof CursoAula))
            return false;
        CursoAula other = (CursoAula) obj;
        return titulo != null && titulo.equalsIgnoreCase(other.titulo);
    }

    @Override
    public int hashCode() {
        return titulo != null ? titulo.toLowerCase().hashCode() : 0;
    }

    public IntPredicate getCurso() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'getCurso'");
    }
}
