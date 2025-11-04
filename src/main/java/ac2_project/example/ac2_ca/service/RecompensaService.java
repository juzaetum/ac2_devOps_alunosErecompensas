package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.entity.Aluno;
import org.springframework.stereotype.Service;

@Service
public class RecompensaService {

    public void calcularRecompensa(Aluno aluno) {
        if (aluno.getMedia() <= 7.0f) {
            aluno.setCursosExtrasRecebidos(0);
            return;
        }

        int interacoes = aluno.getTopicosCriados() + aluno.getComentariosFeitos();
        int novosCursos = interacoes / 5;

        if (novosCursos > aluno.getCursosExtrasRecebidos()) {
            aluno.setCursosExtrasRecebidos(novosCursos);
        }
    }
}
