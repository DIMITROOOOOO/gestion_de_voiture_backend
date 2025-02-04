package applicationproject.dto;

import java.sql.Date;

import applicationproject.enums.statut;
import applicationproject.enums.typeCarburant;
import lombok.Data;

@Data
public class VehicleDTO {
    private String plaque_immatriculation;
    private float kilometrage;
    private Date dateMiseEnCirculation;
    private typeCarburant typeCarburant;
    private float consommation;
    private statut statut;
    private ModeleDTO modele;
    private VignetteDTO vignette;
    private AssuranceDTO assurance;
    private TaxeDTO taxe;

}
