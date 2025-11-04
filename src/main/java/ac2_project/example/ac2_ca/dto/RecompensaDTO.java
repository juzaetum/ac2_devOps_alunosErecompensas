package ac2_project.example.ac2_ca.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecompensaDTO {

    private String tipo;
    private String descricao;
}
