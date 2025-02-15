package applicationproject.service.auth;
import applicationproject.entity.vehicules;
import applicationproject.dto.MaintenanceRequest;
import applicationproject.entity.Maintenance;
import applicationproject.reposatory.maintenanceRepository;
import lombok.RequiredArgsConstructor;

import java.util.Map;
import java.util.stream.Collectors;
import java.util.List;

import org.springframework.stereotype.Service;

import applicationproject.enums.etat;
import applicationproject.enums.nature;
import applicationproject.enums.statut;

@Service
@RequiredArgsConstructor
public class MaintenanceService {
    private final maintenanceRepository maintenanceRepository;
    private final VehiculesService vehiculeService;

    public Maintenance scheduleRepair(MaintenanceRequest request) {
        vehicules vehicule = vehiculeService.getVehiculeById(request.getVehiculeId());
        if (vehicule == null) {
            throw new RuntimeException("Vehicule not found with ID: " + request.getVehiculeId());
        }
        vehiculeService.updateVehiculeStatut(request.getVehiculeId(), statut.en_maintenance);
        Maintenance maintenance = new Maintenance();
        maintenance.setVehicule(vehicule); 
        maintenance.setDatePlanifiee(request.getDatePlanifiee());
        maintenance.setDescription(request.getDescription());
        maintenance.setStatut(request.getStatut());
        maintenance.setDepense(request.getDepense());
        maintenance.setNature(request.getNature()); 
        maintenance.setType(request.getType()); 
    
    
        return maintenanceRepository.save(maintenance);
    }

   public Map<Integer, Float> getDepensesByVehicule() {
        List<Object[]> results = maintenanceRepository.findDepensesByVehicule();
        return results.stream()
                .collect(Collectors.toMap(
                        result -> (Integer) result[0],
                        result -> (float) result[1]
                ));
    }
    public List<Maintenance> getAllMaintenances() {
        return maintenanceRepository.findAllWithVehicule(); 
    }

    public Float getTotalDepenses() {
        return maintenanceRepository.findTotalDepenses();
    }

    public Maintenance updateMaintenance(int id, MaintenanceRequest request) {
        Maintenance existingMaintenance = maintenanceRepository.findById(id).orElseThrow(() -> new RuntimeException("Maintenance not found"));
        existingMaintenance.setDatePlanifiee(request.getDatePlanifiee());
        existingMaintenance.setDescription(request.getDescription());
        existingMaintenance.setStatut(request.getStatut());
        existingMaintenance.setDepense(request.getDepense());
        existingMaintenance.setNature(request.getNature()); 
        existingMaintenance.setType(request.getType()); 
        if (request.getStatut() == etat.termine) {
            vehiculeService.updateVehiculeStatut(existingMaintenance.getVehicule().getVehiculeId(), statut.disponible);
        }

        return maintenanceRepository.save(existingMaintenance);
    }

    public void deleteMaintenance(int id) {
        maintenanceRepository.deleteById(id);
    }
    public List<Maintenance> getMaintenancesByNature(nature nature) {
        return maintenanceRepository.findByNature(nature);
    }

}