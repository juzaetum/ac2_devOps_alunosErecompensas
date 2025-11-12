package ac2_project.example.ac2_ca.dto;

import ac2_project.example.ac2_ca.entity.Recompensa;
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
    public static Object fromEntity(Recompensa recompensa) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'fromEntity'");
    }
}
