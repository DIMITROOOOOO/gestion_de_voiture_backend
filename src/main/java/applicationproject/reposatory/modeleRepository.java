package applicationproject.reposatory;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import applicationproject.entity.Modele;

public interface modeleRepository extends JpaRepository<Modele, Integer> {

    List<Modele> findByMarque(String marque);

    Optional<Modele> findById(int id);

    @SuppressWarnings("null")
    List<Modele> findAll();
}