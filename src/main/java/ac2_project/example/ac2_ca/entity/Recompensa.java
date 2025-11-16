package ac2_project.example.ac2_ca.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "tb_recompensa")
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Recompensa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private float valor;
    private String nome;
    private String curso_titulo;

    // Construtor usado pelo teste: (valor, nome, curso)
    public Recompensa(float valor, String nome, String curso_titulo) {
        this.valor = valor;
        this.nome = nome;
        this.curso_titulo = curso_titulo;
    }

    // Construtor alternativo usado pelo outro teste: (nome, valor, curso)
    public Recompensa(String nome, float valor, String curso_titulo) {
        this.valor = valor;
        this.nome = nome;
        this.curso_titulo = curso_titulo;
    }

    // Construtor com id usado nos testes de controller
    public Recompensa(Long id, float valor, String nome, String curso_titulo) {
        this.id = id;
        this.valor = valor;
        this.nome = nome;
        this.curso_titulo = curso_titulo;
    }
}
