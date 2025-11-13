package ac2_project.example.ac2_ca.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
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

    public Professor(Professor_RA ra, Instituicao instituicao) {
        this.ra = ra;
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
