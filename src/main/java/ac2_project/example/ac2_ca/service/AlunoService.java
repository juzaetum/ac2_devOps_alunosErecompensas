package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.entity.Aluno;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    private final List<Aluno> alunos = new ArrayList<>();

    public List<Aluno> getAllAlunos() {
        return alunos;
    }

    public Aluno getAlunoById(Long id) {
        return alunos.stream()
                .filter(a -> a.getId() != null && a.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public Aluno saveAluno(Aluno aluno) {
        alunos.add(aluno);
        return aluno;
    }

    public Aluno updateAluno(Long id, Aluno alunoAtualizado) {
        Aluno existente = getAlunoById(id);
        if (existente != null) {
            existente.setCurso(alunoAtualizado.getCurso());
            existente.setMedia(alunoAtualizado.getMedia());
            existente.setRa(alunoAtualizado.getRa());
        }
        return existente;
    }

    public void deleteAluno(Long id) {
        alunos.removeIf(a -> a.getId() != null && a.getId().equals(id));
    }
}
