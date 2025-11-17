
package ac2_project.example.ac2_ca.repository;

import ac2_project.example.ac2_ca.entity.Professor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {
}
