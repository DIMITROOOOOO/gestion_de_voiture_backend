package applicationproject.service.auth;

import applicationproject.dto.ConsommationDTO;
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
        Optional<vehicules> optionalVehicule = vehiculeRepository.findByPlaqueImmatriculation(plaqueImmatriculation);
        vehicules vehicule = optionalVehicule.orElseThrow(() -> 
            new RuntimeException("Vehicule no trouvee " + plaqueImmatriculation));
        List<ConsommationVehicule> consommationVehicules = consommationVehiculeRepository.findByVehicule_VehiculeId(vehicule.getVehiculeId());
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
        Optional<vehicules> optionalVehicule = vehiculeRepository.findByPlaqueImmatriculation(request.getPlaqueImmatriculation());
        vehicules vehicule = optionalVehicule.orElseThrow(() -> 
            new RuntimeException("Vehicule not found with plaque_immatriculation: " + request.getPlaqueImmatriculation())
        );

        ConsommationVehicule consommationVehicule = new ConsommationVehicule();
        consommationVehicule.setVehicule(vehicule);
        consommationVehicule.setDateConsommation(request.getDateConsommation());
        consommationVehicule.setQuantiteConsommee(request.getQuantiteConsommee());
        consommationVehicule.setMontantDepense(request.getMontantDepense());

        ConsommationVehicule savedConsommationVehicule = consommationVehiculeRepository.save(consommationVehicule);

        return convertToDTO(savedConsommationVehicule);
    }
    public List<ConsommationDTO> getTotalConsommationFeesByVehicule() {
        return consommationVehiculeRepository.findTotalMontantDepenseByVehicule();
    }

   
}