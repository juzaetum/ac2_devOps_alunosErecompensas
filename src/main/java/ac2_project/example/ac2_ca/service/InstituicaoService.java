package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.entity.Instituicao;
import ac2_project.example.ac2_ca.entity.InstituicaoCNPJ;
import ac2_project.example.ac2_ca.repository.InstituicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class InstituicaoService {

    @Autowired
    private InstituicaoRepository instituicaoRepository;

    public List<Instituicao> getAll() {
        return instituicaoRepository.findAll();
    }

    public Optional<Instituicao> getById(Long id) {
        return instituicaoRepository.findById(id);
    }

    public Instituicao create(Instituicao inst) {
        return instituicaoRepository.save(inst);
    }

    public Optional<Instituicao> update(Long id, Instituicao dados) {
        return instituicaoRepository.findById(id).map(existente -> {
            existente.setNome(dados.getNome());
            existente.setEndereco(dados.getEndereco());
            existente.setCnpj(new InstituicaoCNPJ(dados.getCnpj().getNumero()));
            existente.setAtiva(dados.isAtiva());
            return instituicaoRepository.save(existente);
        });
    }

    public boolean delete(Long id) {
        if (instituicaoRepository.existsById(id)) {
            instituicaoRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
