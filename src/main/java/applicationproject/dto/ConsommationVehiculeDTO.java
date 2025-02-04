package applicationproject.dto;

import java.sql.Date;

import lombok.Data;

@Data
public class ConsommationVehiculeDTO {
    private int id;
    private String plaqueImmatriculation;
    private Date dateConsommation;
    private float quantiteConsommee;
    private float montantDepense;
}
