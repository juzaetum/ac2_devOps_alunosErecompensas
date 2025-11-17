package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.dto.ProfessorDTO;
import ac2_project.example.ac2_ca.entity.Instituicao;
import ac2_project.example.ac2_ca.entity.Professor;
import ac2_project.example.ac2_ca.entity.Professor_RA;
import ac2_project.example.ac2_ca.repository.InstituicaoRepository;
import ac2_project.example.ac2_ca.repository.ProfessorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProfessorService {

    @Autowired
    private ProfessorRepository professorRepository;

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    public List<Professor> getAll() {
        return professorRepository.findAll();
    }

    public Optional<Professor> getById(Long id) {
        return professorRepository.findById(id);
    }

    public Professor create(ProfessorDTO dto) {

        Instituicao inst = instituicaoRepository.findById(dto.getInstituicaoId())
                .orElseThrow(() -> new IllegalArgumentException("Instituição não encontrada."));

        Professor prof = new Professor(
                new Professor_RA(dto.getRa()),
                inst);

        return professorRepository.save(prof);
    }

    public Optional<Professor> update(Long id, ProfessorDTO dto) {
        return professorRepository.findById(id).map(existing -> {

            if (dto.getRa() != null) {
                existing.setRa(new Professor_RA(dto.getRa()));
            }

            if (dto.getInstituicaoId() != null) {
                Instituicao inst = instituicaoRepository.findById(dto.getInstituicaoId())
                        .orElseThrow(() -> new IllegalArgumentException("Instituição não encontrada."));
                existing.setInstituicao(inst);
            }

            return professorRepository.save(existing);
        });
    }

    public boolean delete(Long id) {
        if (professorRepository.existsById(id)) {
            professorRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
