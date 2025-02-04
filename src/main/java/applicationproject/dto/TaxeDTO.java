package applicationproject.dto;

import applicationproject.entity.Taxe;
import lombok.Data;

@Data
public class TaxeDTO {

    private String codeTaxe;
    private float montant;
    public Taxe toEntity() {
        Taxe taxe = new Taxe();
        taxe.setCodeTaxe(this.codeTaxe);
        taxe.setMontant(this.montant);
        return taxe;
    }
}
