package applicationproject.reposatory;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import applicationproject.entity.Assurance;

@Repository
public interface AssuranceRepository extends JpaRepository<Assurance, Integer>{

}
