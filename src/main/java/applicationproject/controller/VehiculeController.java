package applicationproject.controller;

import applicationproject.dto.VehicleDTO;
import applicationproject.dto.VehiculeFeesResponse;
import applicationproject.entity.vehicules;
import applicationproject.service.auth.VehiculesService;
import applicationproject.service.auth.ModeleService;
import applicationproject.service.auth.VignetteService;
import applicationproject.service.auth.AssuranceService;
import applicationproject.service.auth.TaxeService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicules")
@RequiredArgsConstructor
public class VehiculeController {

    private final VehiculesService vehiculeService;
    private final ModeleService modeleService;
    private final VignetteService vignetteService;
    private final AssuranceService assuranceService;
    private final TaxeService taxeService;

    @GetMapping
    public ResponseEntity<List<vehicules>> getAllVehicules() {
        List<vehicules> vehicules = vehiculeService.getAllVehicules();
        return ResponseEntity.ok(vehicules);
    }

    @GetMapping("/available")
    public ResponseEntity<List<vehicules>> getAvailableVehicules() {
        List<vehicules> availableVehicules = vehiculeService.getAvailableVehicules();
        return ResponseEntity.ok(availableVehicules);
    }

    @GetMapping("/{id}")
    public ResponseEntity<vehicules> getVehiculeById(@PathVariable int id) {
        vehicules vehicule = vehiculeService.getVehiculeById(id);
        if (vehicule != null) {
            return ResponseEntity.ok(vehicule);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<vehicules> createVehicule(@RequestBody VehicleDTO vehicleDTO) {
        // Convert DTOs to Entities
        var modele = vehicleDTO.getModele().toEntity();
        var vignette = vehicleDTO.getVignette().toEntity();
        var assurance = vehicleDTO.getAssurance().toEntity();
        var taxe = vehicleDTO.getTaxe().toEntity();

        // Save associated entities
        var savedModele = modeleService.createModele(modele);
        var savedVignette = vignetteService.createVignette(vignette);
        var savedAssurance = assuranceService.createAssurance(assurance);
        var savedTaxe = taxeService.createTaxe(taxe);

        // Create and save Vehicule
        vehicules vehicule = new vehicules();
        vehicule.setPlaque_immatriculation(vehicleDTO.getPlaque_immatriculation());
        vehicule.setKilometrage(vehicleDTO.getKilometrage());
        vehicule.setDateMiseEnCirculation(vehicleDTO.getDateMiseEnCirculation());
        vehicule.setTypeCarburant(vehicleDTO.getTypeCarburant());
        vehicule.setConsommation(vehicleDTO.getConsommation());
        vehicule.setStatut(vehicleDTO.getStatut());
        vehicule.setModele(savedModele);
        vehicule.setVignette(savedVignette);
        vehicule.setAssurance(savedAssurance);
        vehicule.setTaxe(savedTaxe);

        vehicules createdVehicule = vehiculeService.createVehicule(vehicule);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdVehicule);
    }

    @PutMapping("/{id}")
    public ResponseEntity<vehicules> updateVehicule(@PathVariable int id, @RequestBody vehicules updatedVehicule) {
        vehicules vehicule = vehiculeService.updateVehicule(id, updatedVehicule);
        return ResponseEntity.ok(vehicule);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicule(@PathVariable int id) {
        vehiculeService.deleteVehicule(id);
        return ResponseEntity.noContent().build();
    }
     @GetMapping("/fees/{plaqueImmatriculation}")
    public VehiculeFeesResponse getFeesByImmatriculation(@PathVariable String plaqueImmatriculation) {
        return vehiculeService.getFeesByImmatriculation(plaqueImmatriculation);
    }
}