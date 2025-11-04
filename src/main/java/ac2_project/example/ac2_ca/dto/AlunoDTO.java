package ac2_project.example.ac2_ca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AlunoDTO {

    private Long id;
    private String nome;
    private String ra;
    private String curso;
    private Double rendimento;
    private Integer numeroDeCursos;
    private List<RecompensaDTO> recompensas;
}
