package applicationproject.dto;

import java.sql.Date;

import applicationproject.entity.Vignette;
import lombok.Data;

@Data
public class VignetteDTO {
    private String numeroVignette;
    private Date dateExpiration;
    private Date dateDebut;
    private float coutAchat;
    public Vignette toEntity() {
        Vignette vignette = new Vignette();
        vignette.setNumeroVignette(this.numeroVignette);
        vignette.setDateExpiration(this.dateExpiration);
        vignette.setDateDebut(this.dateDebut);
        vignette.setCoutAchat(this.coutAchat);
        return vignette;
    }
}
