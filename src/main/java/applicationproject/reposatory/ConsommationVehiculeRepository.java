package applicationproject.reposatory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import applicationproject.entity.ConsommationVehicule;
@Repository

public interface ConsommationVehiculeRepository extends JpaRepository<ConsommationVehicule, Integer> {
    List<ConsommationVehicule> findByVehicule_VehiculeId(int vehiculeId);
}


