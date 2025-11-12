package ac2_project.example.ac2_ca.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tb_recompensa")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@AllArgsConstructor
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float valor;
    private String nome;
    private String curso_titulo;

    public Recompensa(float valor, String nome, String curso_titulo) {
        this.valor = valor;
        this.nome = nome;
        this.curso_titulo = curso_titulo;
    }
}
