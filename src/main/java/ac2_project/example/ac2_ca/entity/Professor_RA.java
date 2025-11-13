package ac2_project.example.ac2_ca.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class Professor_RA {

    private String codigo;

    public Professor_RA(String codigo) {
        if (codigo == null || !codigo.matches("\\d{6}")) {
            throw new IllegalArgumentException("O RA do professor deve conter exatamente 6 dígitos numéricos.");
        }
        this.codigo = codigo;
    }

    public void setCodigo(String codigo) {
        if (codigo == null || !codigo.matches("\\d{6}")) {
            throw new IllegalArgumentException("O RA do professor deve conter exatamente 6 dígitos numéricos.");
        }
        this.codigo = codigo;
    }

    @Override
    public String toString() {
        return codigo;
    }
}
