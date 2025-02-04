package applicationproject.reposatory;

import org.springframework.data.jpa.repository.JpaRepository;

import applicationproject.entity.Taxe;

public interface TaxeRepository extends JpaRepository<Taxe, Integer>{

}
