package ac2_project.example.ac2_ca.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ac2_project.example.ac2_ca.entity.Aluno;

@Repository
public interface AlunoRepository extends JpaRepository<Aluno, Long> {
}
