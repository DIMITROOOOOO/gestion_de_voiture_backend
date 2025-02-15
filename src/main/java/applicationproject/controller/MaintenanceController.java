package applicationproject.controller;

import applicationproject.dto.MaintenanceRequest;
import applicationproject.entity.Maintenance;
import applicationproject.enums.nature;
import applicationproject.service.auth.MaintenanceService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/maintenances") 
@RequiredArgsConstructor
public class MaintenanceController {
    private final MaintenanceService maintenanceService;

    @PostMapping("/schedule-repair")
    public ResponseEntity<Maintenance> scheduleRepair(@RequestBody MaintenanceRequest request) {
        try {
            Maintenance savedMaintenance = maintenanceService.scheduleRepair(request);
            return ResponseEntity.ok(savedMaintenance);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @GetMapping
    public ResponseEntity<List<Maintenance>> getAllMaintenances() {
        List<Maintenance> maintenances = maintenanceService.getAllMaintenances();
        return ResponseEntity.ok(maintenances);
    }

    @GetMapping("/depenses-by-vehicule")
    public ResponseEntity<Map<Integer, Float>> getDepensesByVehicule() {
        Map<Integer, Float> depenses = maintenanceService.getDepensesByVehicule();
        return ResponseEntity.ok(depenses);
    }

    @GetMapping("/total-depenses")
    public ResponseEntity<Float> getTotalDepenses() {
        Float totalDepenses = maintenanceService.getTotalDepenses();
        return ResponseEntity.ok(totalDepenses);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Maintenance> updateMaintenance(@PathVariable int id, @RequestBody MaintenanceRequest request) {
        Maintenance updatedMaintenance = maintenanceService.updateMaintenance(id, request);
        return ResponseEntity.ok(updatedMaintenance);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaintenance(@PathVariable int id) {
        maintenanceService.deleteMaintenance(id);
        return ResponseEntity.noContent().build();
    }
     @GetMapping("/by-nature")
    public ResponseEntity<List<Maintenance>> getMaintenancesByNature(@RequestParam nature nature) {
        List<Maintenance> maintenances = maintenanceService.getMaintenancesByNature(nature);
        return ResponseEntity.ok(maintenances);
    }
}