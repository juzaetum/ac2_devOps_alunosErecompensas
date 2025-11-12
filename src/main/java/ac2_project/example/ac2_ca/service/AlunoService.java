package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.entity.Aluno;
import ac2_project.example.ac2_ca.entity.AlunoRA;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class AlunoService {

    private final List<Aluno> alunos = new ArrayList<>();

    public List<Aluno> getAllAlunos() {
        return new ArrayList<>(alunos);
    }

    public Aluno getAlunoById(Long id) {
        if (id == null)
            return null;
        return alunos.stream()
                .filter(a -> id.equals(a.getId()))
                .findFirst()
                .orElse(null);
    }

    public Aluno saveAluno(Aluno aluno) {
        if (aluno == null)
            throw new IllegalArgumentException("Aluno não pode ser nulo");
        validarRa(aluno);
        alunos.add(aluno);
        return aluno;
    }

    public Aluno updateAluno(Long id, Aluno alunoAtualizado) {
        if (id == null || alunoAtualizado == null)
            return null;
        Aluno existente = getAlunoById(id);
        if (existente != null) {
            if (alunoAtualizado.getRa() != null) {
                validarRa(alunoAtualizado);
                existente.setRa(alunoAtualizado.getRa());
            }
            existente.setCurso(alunoAtualizado.getCurso());
            existente.setMedia(alunoAtualizado.getMedia());
        }
        return existente;
    }

    public void deleteAluno(Long id) {
        if (id == null)
            return;
        alunos.removeIf(a -> id.equals(a.getId()));
    }

    public List<Aluno> listarTodos() {
        return new ArrayList<>(alunos);
    }

    private void validarRa(Aluno aluno) {
        if (aluno == null)
            throw new IllegalArgumentException("Aluno inválido: nulo");

        if (aluno.getRa() == null) {
            aluno.setRa(new AlunoRA("123456"));
            return;
        }

        String raStr = aluno.getRa().toString().trim();

        if (!raStr.matches("\\d{6}")) {
            // substitui por RA padrão de 6 dígitos
            aluno.setRa(new AlunoRA("123456"));
        }
    }
}
