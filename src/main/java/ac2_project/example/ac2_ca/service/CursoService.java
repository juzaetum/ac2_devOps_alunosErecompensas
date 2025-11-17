package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.entity.Curso;
import ac2_project.example.ac2_ca.repository.CursoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CursoService {

    @Autowired
    private CursoRepository repository;

    public List<Curso> getAll() {
        return repository.findAll();
    }

    public Optional<Curso> getById(Long id) {
        return repository.findById(id);
    }

    public Curso create(Curso curso) {
        return repository.save(curso);
    }

    public Optional<Curso> update(Long id, Curso dados) {
        return repository.findById(id).map(existente -> {
            existente.setTitulo(dados.getTitulo());
            existente.setDescricao(dados.getDescricao());
            existente.setProfessor(dados.getProfessor());
            existente.setAtivo(dados.isAtivo());
            return repository.save(existente);
        });
    }

    public boolean delete(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}
