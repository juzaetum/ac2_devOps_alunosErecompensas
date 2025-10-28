package ac2_project.example.ac2_ca.entity;

import jakarta.persistence.Embeddable;

@Embeddable
public class AlunoRA {

    private String numero;

    public AlunoRA() {
    }

    public AlunoRA(String numero) {
        if (!numero.matches("\\d{6}")) {
            throw new IllegalArgumentException("O RA deve conter exatamente 6 números.");
        }
        this.numero = numero;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if (!numero.matches("\\d{6}")) {
            throw new IllegalArgumentException("O RA deve conter exatamente 6 números.");
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
        if (!(obj instanceof AlunoRA))
            return false;
        AlunoRA other = (AlunoRA) obj;
        return numero != null && numero.equals(other.numero);
    }

    @Override
    public int hashCode() {
        return numero != null ? numero.hashCode() : 0;
    }
}
