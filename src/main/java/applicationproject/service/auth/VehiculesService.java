package applicationproject.service.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import applicationproject.enums.*;
import applicationproject.dto.VehiculeFeesResponse;
import applicationproject.entity.vehicules;
import applicationproject.reposatory.VehiculeRepository;

@Service
public class VehiculesService {

    @Autowired
    private VehiculeRepository vehiculesRepository;

    public vehicules createVehicule(vehicules vehicule) {
        return vehiculesRepository.save(vehicule);
    }

    public List<vehicules> getAvailableVehicules() {
        return vehiculesRepository.findByStatut(statut.disponible);
    }

    public vehicules getVehiculeById(int vehiculeId) {
        return vehiculesRepository.findById(vehiculeId).orElse(null);
    }

    public vehicules updateVehicule(int vehiculeId, vehicules updatedVehicule) {
        vehicules existingVehicule = vehiculesRepository.findById(vehiculeId).orElse(null);
        if (existingVehicule != null) {
            existingVehicule.setPlaque_immatriculation(updatedVehicule.getPlaque_immatriculation());
            existingVehicule.setKilometrage(updatedVehicule.getKilometrage());
            existingVehicule.setDateMiseEnCirculation(updatedVehicule.getDateMiseEnCirculation());
            existingVehicule.setTypeCarburant(updatedVehicule.getTypeCarburant());
            existingVehicule.setConsommation(updatedVehicule.getConsommation());
            existingVehicule.setStatut(updatedVehicule.getStatut());
            existingVehicule.setModele(updatedVehicule.getModele());
            existingVehicule.setVignette(updatedVehicule.getVignette());
            existingVehicule.setAssurance(updatedVehicule.getAssurance());
            existingVehicule.setTaxe(updatedVehicule.getTaxe());
            return vehiculesRepository.save(existingVehicule);
        }
        return null;
    }

    public void deleteVehicule(int vehiculeId) {
        vehiculesRepository.deleteById(vehiculeId);
    }

    public List<vehicules> getAllVehicules() {
        return vehiculesRepository.findAll();
    }

    public void updateVehiculeStatut(int vehiculeId, statut newStatut) {
        vehicules vehicule = vehiculesRepository.findById(vehiculeId).orElse(null);
        if (vehicule != null) {
            vehicule.setStatut(newStatut);
            vehiculesRepository.save(vehicule);
        }
    }

    public vehicules getVehiculeByMatricule(String plaqueImmatriculation) {
        return vehiculesRepository.findByPlaqueImmatriculation(plaqueImmatriculation).orElse(null);
    }

    public List<vehicules> getAllVehiculesInfo() {
        return vehiculesRepository.findAll();
    }
    public List<vehicules> getVehiculesByMarque(String marque) {
        return vehiculesRepository.findByModele_Marque(marque);
    }
    public List<vehicules> getVehiculesByTypeContrat(typeContrat typeContrat) {
        return vehiculesRepository.findByAssurance_TypeContrat(typeContrat);
    }
    public List<vehicules> getVehiculesByType(typeVehicle type) {
        return vehiculesRepository.findByModele_TypeVehicle(type);
    }
    public VehiculeFeesResponse getFeesByImmatriculation(String plaqueImmatriculation) {
        Optional<vehicules> vehiculeOptional = vehiculesRepository.findByPlaqueImmatriculation(plaqueImmatriculation);

        if (vehiculeOptional.isEmpty()) {
            throw new RuntimeException("Vehicle not found with license plate: " + plaqueImmatriculation);
        }

        vehicules vehicule = vehiculeOptional.get();
        return vehicule.calculateFeesSeparately();
    }
}