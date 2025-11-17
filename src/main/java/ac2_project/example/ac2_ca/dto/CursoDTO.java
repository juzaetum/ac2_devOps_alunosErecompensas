package ac2_project.example.ac2_ca.dto;

import ac2_project.example.ac2_ca.entity.Curso;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CursoDTO {

    private Long id;
    private String titulo;
    private String descricao;
    private String professor;
    private boolean ativo;

    public CursoDTO(Curso curso) {
        this.id = curso.getId();
        this.titulo = curso.getTitulo();
        this.descricao = curso.getDescricao();
        this.professor = curso.getProfessor();
        this.ativo = curso.isAtivo();
    }

    public Curso toEntity() {
        Curso curso = new Curso();
        curso.setId(id);
        curso.setTitulo(titulo);
        curso.setDescricao(descricao);
        curso.setProfessor(professor);
        curso.setAtivo(ativo);
        return curso;
    }

    public CursoDTO(long id, String titulo, String descricao, String professor, boolean ativo) {
        this.id = id;
        this.titulo = titulo;
        this.descricao = descricao;
        this.professor = professor;
        this.ativo = ativo;
    }
}
