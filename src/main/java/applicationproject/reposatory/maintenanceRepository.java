package applicationproject.reposatory;

import applicationproject.entity.Maintenance;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface maintenanceRepository extends JpaRepository<Maintenance, Integer> {
    @Query("SELECT m.vehicule.vehiculeId, CAST(SUM(m.depense) as float) FROM Maintenance m GROUP BY m.vehicule.vehiculeId")
    List<Object[]> findDepensesByVehicule();

    @Query("SELECT SUM(m.depense) FROM Maintenance m")
    float findTotalDepenses();

    @Query("SELECT m FROM Maintenance m JOIN FETCH m.vehicule")
    List<Maintenance> findAllWithVehicule();
    
}