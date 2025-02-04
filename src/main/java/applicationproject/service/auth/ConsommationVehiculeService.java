package applicationproject.service.auth;

import applicationproject.dto.ConsommationVehiculeDTO;
import applicationproject.entity.ConsommationVehicule;
import applicationproject.entity.vehicules;
import applicationproject.reposatory.ConsommationVehiculeRepository;
import applicationproject.reposatory.VehiculeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ConsommationVehiculeService {

    @Autowired
    private VehiculeRepository vehiculeRepository;

    @Autowired
    private ConsommationVehiculeRepository consommationVehiculeRepository;

    public List<ConsommationVehiculeDTO> getConsommationByPlaqueImmatriculation(String plaqueImmatriculation) {
        // Fetch the vehicule by plaque_immatriculation
        Optional<vehicules> optionalVehicule = vehiculeRepository.findByPlaqueImmatriculation(plaqueImmatriculation);

        // Throw an exception if the vehicule is not found
        vehicules vehicule = optionalVehicule.orElseThrow(() -> 
            new RuntimeException("Vehicule not found with plaque_immatriculation: " + plaqueImmatriculation)
        );

        // Fetch consommation details using vehicule_id
        List<ConsommationVehicule> consommationVehicules = consommationVehiculeRepository.findByVehicule_VehiculeId(vehicule.getVehiculeId());

        // Convert entities to DTOs
        return consommationVehicules.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private ConsommationVehiculeDTO convertToDTO(ConsommationVehicule consommationVehicule) {
        ConsommationVehiculeDTO dto = new ConsommationVehiculeDTO();
        dto.setId(consommationVehicule.getId());
        dto.setDateConsommation(consommationVehicule.getDateConsommation());
        dto.setQuantiteConsommee(consommationVehicule.getQuantiteConsommee());
        dto.setMontantDepense(consommationVehicule.getMontantDepense());
        return dto;
    }
    public ConsommationVehiculeDTO addConsommationVehicule(ConsommationVehiculeDTO request) {
        // Fetch the vehicule by plaque_immatriculation
        Optional<vehicules> optionalVehicule = vehiculeRepository.findByPlaqueImmatriculation(request.getPlaqueImmatriculation());
        vehicules vehicule = optionalVehicule.orElseThrow(() -> 
            new RuntimeException("Vehicule not found with plaque_immatriculation: " + request.getPlaqueImmatriculation())
        );

        // Create a new ConsommationVehicule entity
        ConsommationVehicule consommationVehicule = new ConsommationVehicule();
        consommationVehicule.setVehicule(vehicule);
        consommationVehicule.setDateConsommation(request.getDateConsommation());
        consommationVehicule.setQuantiteConsommee(request.getQuantiteConsommee());
        consommationVehicule.setMontantDepense(request.getMontantDepense());

        // Save the new consommation record
        ConsommationVehicule savedConsommationVehicule = consommationVehiculeRepository.save(consommationVehicule);

        // Convert the saved entity to DTO and return it
        return convertToDTO(savedConsommationVehicule);
    }

   
}