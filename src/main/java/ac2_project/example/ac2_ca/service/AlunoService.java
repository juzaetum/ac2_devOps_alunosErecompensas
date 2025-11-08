package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.entity.Aluno;
import org.springframework.stereotype.Service;

import java.lang.reflect.Method;
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
        validarRa(aluno);
        alunos.add(aluno);
        return aluno;
    }

    public Aluno updateAluno(Long id, Aluno alunoAtualizado) {
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
        alunos.removeIf(a -> a.getId() != null && a.getId().equals(id));
    }

    public List<Aluno> listarTodos() {
        return new ArrayList<>(alunos);
    }

    // valida se o RA da entidade possui exatamente 6 dígitos numéricos
    private void validarRa(Aluno aluno) {
        if (aluno == null || aluno.getRa() == null) {
            throw new IllegalArgumentException("RA inválido: nulo");
        }
        String raStr = extrairRaComoString(aluno);
        if (raStr == null || !raStr.matches("\\d{6}")) {
            throw new IllegalArgumentException("RA inválido: deve conter exatamente 6 dígitos numéricos");
        }
    }

    // tenta extrair uma representação string do objeto AlunoRA procurando getters comuns
    private String extrairRaComoString(Aluno aluno) {
        Object raObj = aluno.getRa();
        if (raObj == null) return null;
        try {
            for (Method m : raObj.getClass().getMethods()) {
                if (m.getParameterCount() == 0 && m.getReturnType() == String.class) {
                    String name = m.getName().toLowerCase();
                    if (name.equals("getra") || name.contains("ra") || name.contains("numero") || name.contains("valor") || name.equals("tostring")) {
                        Object val = m.invoke(raObj);
                        if (val != null) return val.toString();
                    }
                }
            }
        } catch (Exception ignored) {
        }
        // último recurso: toString (pode não ser apenas os dígitos)
        try {
            return raObj.toString();
        } catch (Exception e) {
            return null;
        }
    }
}
