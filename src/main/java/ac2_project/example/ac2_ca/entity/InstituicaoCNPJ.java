package ac2_project.example.ac2_ca.entity;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Embeddable
public class InstituicaoCNPJ {

    private String numero;

    public InstituicaoCNPJ(String numero) {
        if (!numero.matches("\\d{14}")) {
            throw new IllegalArgumentException("O CNPJ deve conter exatamente 14 dígitos numéricos.");
        }
        this.numero = numero;
    }

    public void setNumero(String numero) {
        if (!numero.matches("\\d{14}")) {
            throw new IllegalArgumentException("O CNPJ deve conter exatamente 14 dígitos numéricos.");
        }
        this.numero = numero;
    }

    @Override
    public String toString() {
        return numero;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (!(obj instanceof InstituicaoCNPJ))
            return false;
        InstituicaoCNPJ other = (InstituicaoCNPJ) obj;
        return numero != null && numero.equals(other.numero);
    }

    @Override
    public int hashCode() {
        return numero != null ? numero.hashCode() : 0;
    }
}
