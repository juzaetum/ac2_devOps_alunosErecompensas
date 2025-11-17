package ac2_project.example.ac2_ca.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProfessorDTO {

    private Long id;
    private String ra;
    private Long instituicaoId;

    public ProfessorDTO(Long id, String ra, Long instituicaoId) {
        this.id = id;
        this.ra = ra;
        this.instituicaoId = instituicaoId;
    }
}
