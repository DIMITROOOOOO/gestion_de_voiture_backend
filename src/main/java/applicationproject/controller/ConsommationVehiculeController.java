package applicationproject.controller;

import applicationproject.dto.ConsommationVehiculeDTO;
import applicationproject.service.auth.ConsommationVehiculeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/consommation-vehicule")
public class ConsommationVehiculeController {

    @Autowired
    private ConsommationVehiculeService consommationVehiculeService;

    @GetMapping("/by-plaque-immatriculation")
    public List<ConsommationVehiculeDTO> getConsommationByPlaqueImmatriculation(@RequestParam String plaqueImmatriculation) {
        return consommationVehiculeService.getConsommationByPlaqueImmatriculation(plaqueImmatriculation);
    }
    @PostMapping("/add")
    public ConsommationVehiculeDTO addConsommationVehicule(@RequestBody ConsommationVehiculeDTO request) {
        return consommationVehiculeService.addConsommationVehicule(request);
    }
}