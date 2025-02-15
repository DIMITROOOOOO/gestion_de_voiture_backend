package applicationproject.reposatory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import applicationproject.entity.Assurance;

@Repository
public interface AssuranceRepository extends JpaRepository<Assurance, Integer>{
    Optional<Assurance> findByIdAssurance(String idAssurance);
}
