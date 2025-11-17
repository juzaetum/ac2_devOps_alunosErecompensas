
package ac2_project.example.ac2_ca.repository;

import ac2_project.example.ac2_ca.entity.Instituicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InstituicaoRepository extends JpaRepository<Instituicao, Long> {
}
