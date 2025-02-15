package applicationproject.reposatory;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import applicationproject.entity.Taxe;

public interface TaxeRepository extends JpaRepository<Taxe, Integer>{
    Optional<Taxe> findByCodeTaxe(String codeTaxe);
}
