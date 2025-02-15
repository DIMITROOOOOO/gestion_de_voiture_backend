package applicationproject.reposatory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import applicationproject.entity.Vignette;

@Repository
public interface VignetteRepository extends JpaRepository<Vignette, Integer>{
    Optional<Vignette> findByNumeroVignette(String numeroVignette);
}
