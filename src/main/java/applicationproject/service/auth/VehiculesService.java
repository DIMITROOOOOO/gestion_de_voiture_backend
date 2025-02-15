package applicationproject.service.auth;

import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

import applicationproject.enums.*;
import applicationproject.dto.DateIntervalDTO;
import applicationproject.dto.VehiculeFeesResponse;
import applicationproject.entity.Assurance;
import applicationproject.entity.Modele;
import applicationproject.entity.Taxe;
import applicationproject.entity.Vignette;
import applicationproject.entity.vehicules;
import applicationproject.reposatory.AssuranceRepository;
import applicationproject.reposatory.TaxeRepository;
import applicationproject.reposatory.VehiculeRepository;
import applicationproject.reposatory.VignetteRepository;
import applicationproject.reposatory.modeleRepository;

@Service
public class VehiculesService {

    private final VehiculeRepository vehiculesRepository;
    private final modeleRepository modeleRepository;
    private final AssuranceRepository assuranceRepository;
    private final VignetteRepository vignetteRepository;
    private final TaxeRepository taxeRepository;

    public VehiculesService(
        VehiculeRepository vehiculesRepository,
        modeleRepository modeleRepository,
        AssuranceRepository assuranceRepository,
        VignetteRepository vignetteRepository,
        TaxeRepository taxeRepository
    ) {
        this.vehiculesRepository = vehiculesRepository;
        this.modeleRepository = modeleRepository;
        this.assuranceRepository = assuranceRepository;
        this.vignetteRepository = vignetteRepository;
        this.taxeRepository = taxeRepository;
    }

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
        vehicules existingVehicule = vehiculesRepository.findById(vehiculeId)
                .orElseThrow(() -> new RuntimeException("Vehicule not found with ID: " + vehiculeId));
    
        existingVehicule.setPlaque_immatriculation(updatedVehicule.getPlaque_immatriculation());
        existingVehicule.setKilometrage(updatedVehicule.getKilometrage());
        existingVehicule.setDateMiseEnCirculation(updatedVehicule.getDateMiseEnCirculation());
        existingVehicule.setTypeCarburant(updatedVehicule.getTypeCarburant());
        existingVehicule.setConsommation(updatedVehicule.getConsommation());
        existingVehicule.setStatut(updatedVehicule.getStatut());
    
        if (updatedVehicule.getModele() != null) {
            Modele existingModele = modeleRepository.findByMarqueAndModele(
                    updatedVehicule.getModele().getMarque(), updatedVehicule.getModele().getModele())
                    .orElseGet(() -> modeleRepository.save(updatedVehicule.getModele()));
            existingVehicule.setModele(existingModele);
        }
    
        if (updatedVehicule.getAssurance() != null) {
            Assurance existingAssurance = assuranceRepository.findByIdAssurance(updatedVehicule.getAssurance().getIdAssurance())
                    .orElseGet(() -> assuranceRepository.save(updatedVehicule.getAssurance()));
            existingVehicule.setAssurance(existingAssurance);
        }
    
        if (updatedVehicule.getVignette() != null) {
            Vignette existingVignette = vignetteRepository.findByNumeroVignette(updatedVehicule.getVignette().getNumeroVignette())
                    .orElseGet(() -> vignetteRepository.save(updatedVehicule.getVignette()));
            existingVehicule.setVignette(existingVignette);
        }
    
        if (updatedVehicule.getTaxe() != null) {
            Taxe existingTaxe = taxeRepository.findByCodeTaxe(updatedVehicule.getTaxe().getCodeTaxe())
                    .orElseGet(() -> taxeRepository.save(updatedVehicule.getTaxe()));
            existingVehicule.setTaxe(existingTaxe);
        }
    
        return vehiculesRepository.save(existingVehicule);
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
            throw new RuntimeException("Vehicle no trouvee: " + plaqueImmatriculation);
        }

        vehicules vehicule = vehiculeOptional.get();
        return vehicule.calculateFeesSeparately();
    }
    public List<vehicules> getVehiculesByAssuranceExpirationDateBetween(DateIntervalDTO dateInterval) {
        return vehiculesRepository.findVehiculesByAssuranceExpirationDateBetween(dateInterval.getDateDebut(), dateInterval.getDateFin());
    }

    public List<vehicules> getVehiculesByVignetteExpirationDateBetween(DateIntervalDTO dateInterval) {
        return vehiculesRepository.findVehiculesByVignetteExpirationDateBetween(dateInterval.getDateDebut(), dateInterval.getDateFin());
    }
}