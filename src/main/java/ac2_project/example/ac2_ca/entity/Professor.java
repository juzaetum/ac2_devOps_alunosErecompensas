package ac2_project.example.ac2_ca.entity;

import ac2_project.example.ac2_ca.entity.Professor_RA;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_professor")
public class Professor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Embedded
    private Professor_RA ra;

    @ManyToOne
    @JoinColumn(name = "instituicao_id")
    private Instituicao instituicao;

    // Construtores
    public Professor() {
    }

    public Professor(Professor_RA ra, Instituicao instituicao) {
        this.ra = ra;
        this.instituicao = instituicao;
    }

    // Getters e Setters
    public Long getId() {
        return id;
    }

    public Professor_RA getRa() {
        return ra;
    }

    public void setRa(Professor_RA ra) {
        this.ra = ra;
    }

    public Instituicao getInstituicao() {
        return instituicao;
    }

    public void setInstituicao(Instituicao instituicao) {
        this.instituicao = instituicao;
    }

    @Override
    public String toString() {
        return "Professor{" +
                "id=" + id +
                ", ra=" + ra +
                ", instituicao=" + (instituicao != null ? instituicao.getNome() : "Sem Instituição") +
                '}';
    }
}
