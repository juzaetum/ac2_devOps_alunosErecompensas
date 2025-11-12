package ac2_project.example.ac2_ca.service;

import ac2_project.example.ac2_ca.entity.Recompensa;
import ac2_project.example.ac2_ca.repository.Recompensa_Repository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RecompensaService {

    @Autowired
    private Recompensa_Repository recompensaRepository;

    public List<Recompensa> getAllRecompensas() {
        return recompensaRepository.findAll();
    }

    public Optional<Recompensa> getRecompensaById(Long id) {
        return recompensaRepository.findById(id);
    }

    public Recompensa createRecompensa(Recompensa recompensa) {
        return recompensaRepository.save(recompensa);
    }

    public Optional<Recompensa> updateRecompensa(Long id, Recompensa recompensaData) {
        return recompensaRepository.findById(id).map(existing -> {
            existing.setValor(recompensaData.getValor());
            existing.setNome(recompensaData.getNome());
            existing.setCurso_titulo(recompensaData.getCurso_titulo());
            return recompensaRepository.save(existing);
        });
    }

    public boolean deleteRecompensa(Long id) {
        if (recompensaRepository.existsById(id)) {
            recompensaRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
