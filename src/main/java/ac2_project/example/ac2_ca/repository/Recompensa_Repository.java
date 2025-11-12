package ac2_project.example.ac2_ca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import ac2_project.example.ac2_ca.entity.Recompensa;

import java.util.List;

@Repository
public interface Recompensa_Repository extends JpaRepository<Recompensa, Long> {

    List<Recompensa> findByNomeContainingIgnoreCase(String nome);

    List<Recompensa> findByValorBetween(float min, float max);

    @Query("SELECT r FROM Recompensa r WHERE r.curso_titulo = :cursoTitulo")
    List<Recompensa> findByCursoTitulo(@Param("cursoTitulo") String cursoTitulo);

    List<Recompensa> findTop10ByOrderByValorDesc();
}
