package applicationproject.dto;

import applicationproject.entity.Modele;
import applicationproject.enums.typeBoiteVitesse;
import applicationproject.enums.typeVehicle;
import lombok.Data;

@Data
public class ModeleDTO {
    private String marque;
    private String modele;
    private typeVehicle typeVehicle;
    private typeBoiteVitesse typeBoiteVitesse;
    public Modele toEntity() {
        Modele modele = new Modele();
        modele.setMarque(this.marque);
        modele.setModele(this.modele);
        modele.setTypeVehicle(this.typeVehicle);
        modele.setTypeBoiteVitesse(this.typeBoiteVitesse);
        return modele;
    }
}
