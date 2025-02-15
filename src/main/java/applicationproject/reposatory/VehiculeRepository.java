package applicationproject.reposatory;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import applicationproject.entity.vehicules;
import applicationproject.enums.statut;
import applicationproject.enums.typeContrat;
import applicationproject.enums.typeVehicle;
@Repository
public interface VehiculeRepository extends JpaRepository<vehicules, Integer>{
     List<vehicules> findByStatut(statut statut);
     @Query("SELECT v FROM vehicules v WHERE v.plaque_immatriculation = :plaqueImmatriculation")
     Optional<vehicules> findByPlaqueImmatriculation(@Param("plaqueImmatriculation") String plaqueImmatriculation);
     List<vehicules> findByModele_Marque(String marque);
     List<vehicules> findByAssurance_TypeContrat(typeContrat typeContrat);
     List<vehicules> findByModele_TypeVehicle(typeVehicle type);
     @Query("SELECT v FROM vehicules v WHERE v.assurance.dateExpiration BETWEEN :dateDebut AND :dateFin")
    List<vehicules> findVehiculesByAssuranceExpirationDateBetween(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin);
    
    @Query("SELECT v FROM vehicules v WHERE v.vignette.dateExpiration BETWEEN :dateDebut AND :dateFin")
    List<vehicules> findVehiculesByVignetteExpirationDateBetween(@Param("dateDebut") Date dateDebut, @Param("dateFin") Date dateFin);
}   
