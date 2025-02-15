package applicationproject.reposatory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import applicationproject.dto.ConsommationDTO;
import applicationproject.entity.ConsommationVehicule;
@Repository

public interface ConsommationVehiculeRepository extends JpaRepository<ConsommationVehicule, Integer> {
    List<ConsommationVehicule> findByVehicule_VehiculeId(int vehiculeId);


    @Query("SELECT new applicationproject.dto.ConsommationDTO(v.plaque_immatriculation,(cv.montantDepense)) " +
       "FROM ConsommationVehicule cv JOIN cv.vehicule v")
    List<ConsommationDTO> findTotalMontantDepenseByVehicule();
}


