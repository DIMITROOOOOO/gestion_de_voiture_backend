package applicationproject.controller;

import applicationproject.dto.ChauffeurDTO;
import applicationproject.entity.chauffeurs;
import applicationproject.service.auth.chauffeursService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/chauffeurs")
public class ChauffeursController {

    @Autowired
    private chauffeursService chauffeursService;

    @PostMapping
    public ResponseEntity<chauffeurs> addChauffeur(@RequestBody ChauffeurDTO chauffeurDTO) {
        chauffeurs chauffeur = chauffeursService.addChauffeur(chauffeurDTO);
        return ResponseEntity.ok(chauffeur);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteChauffeur(@PathVariable int id) {
        chauffeursService.deleteChauffeur(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<chauffeurs> updateChauffeur(@PathVariable int id, @RequestBody ChauffeurDTO chauffeurDTO) {
        chauffeurs chauffeur = chauffeursService.updateChauffeur(id, chauffeurDTO);
        if (chauffeur != null) {
            return ResponseEntity.ok(chauffeur);
        }
        return ResponseEntity.notFound().build();
    }

    @PostMapping("/assign/{chauffeurId}/{vehiculeId}")
    public ResponseEntity<Void> assignChauffeurToVehicle(@PathVariable int chauffeurId, @PathVariable int vehiculeId) {
        chauffeursService.assignChauffeurToVehicle(chauffeurId, vehiculeId);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    public ResponseEntity<List<chauffeurs>> getAllChauffeurs() {
        List<chauffeurs> chauffeurs = chauffeursService.getAllChauffeurs();
        return ResponseEntity.ok(chauffeurs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<chauffeurs> getChauffeurById(@PathVariable int id) {
        Optional<chauffeurs> chauffeur = chauffeursService.getChauffeurById(id);
        return chauffeur.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }
}