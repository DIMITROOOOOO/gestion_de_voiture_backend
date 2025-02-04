package applicationproject.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.sql.Date;
import applicationproject.enums.etat;
import applicationproject.enums.nature;

@Data
public class MaintenanceRequest {
    @NotNull(message = "vehiculeId is required")
    private int vehiculeId;

    @NotNull(message = "datePlanifiee is required")
    private Date datePlanifiee;

    @NotNull(message = "description is required")
    private String description;

    @NotNull(message = "statut is required")
    private etat statut;

    @NotNull(message = "depense is required")
    private float depense;

    @NotNull(message = "nature is required")
    private nature nature; 

    @NotNull(message = "type is required")
    private String type; 
    
}