package applicationproject.dto;

import java.sql.Date;

import applicationproject.entity.Assurance;
import applicationproject.enums.typeContrat;
import lombok.Data;
@Data
public class AssuranceDTO {
    private String idAssurance;
    private String compagnieAssurance;
    private Date dateExpiration;
    private Date dateDebut;
    private float coutAssurance;
    private typeContrat typeContrat;
     public Assurance toEntity() {
        Assurance assurance = new Assurance();
        assurance.setIdAssurance(this.idAssurance);
        assurance.setCompagnieAssurance(this.compagnieAssurance);
        assurance.setDateExpiration(this.dateExpiration);
        assurance.setDateDebut(this.dateDebut);
        assurance.setCoutAssurance(this.coutAssurance);
        assurance.setTypeContrat(this.typeContrat);
        return assurance;
    }
}
