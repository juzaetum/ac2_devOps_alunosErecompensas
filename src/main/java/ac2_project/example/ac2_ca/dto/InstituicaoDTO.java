package ac2_project.example.ac2_ca.dto;

import ac2_project.example.ac2_ca.entity.Instituicao;
import ac2_project.example.ac2_ca.entity.InstituicaoCNPJ;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InstituicaoDTO {

    private Long id;
    private String nome;
    private String endereco;
    private String cnpj;
    private boolean ativa;

    public InstituicaoDTO(Instituicao inst) {
        this.id = inst.getId();
        this.nome = inst.getNome();
        this.endereco = inst.getEndereco();
        this.cnpj = inst.getCnpj() != null ? inst.getCnpj().getNumero() : null;
        this.ativa = inst.isAtiva();
    }

    public Instituicao toEntity() {
        Instituicao inst = new Instituicao();
        inst.setId(id);
        inst.setNome(nome);
        inst.setEndereco(endereco);
        inst.setCnpj(new InstituicaoCNPJ(cnpj));
        inst.setAtiva(ativa);
        return inst;
    }

    public InstituicaoDTO(long l, String nome_instituicao, String endereco_instituicao, String cnpj_instituicao) {
        this.id = l;
        this.nome = nome_instituicao;
        this.endereco = endereco_instituicao;
        this.cnpj = cnpj_instituicao;
    }
}
