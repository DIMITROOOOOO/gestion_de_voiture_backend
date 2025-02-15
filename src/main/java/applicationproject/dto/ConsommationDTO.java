package applicationproject.dto;



public class ConsommationDTO {
    private String plaqueImmatriculation;
    private float totalMontantDepense;

public ConsommationDTO(String plaqueImmatriculation, float totalMontantDepense) {
    this.plaqueImmatriculation = plaqueImmatriculation;
    this.totalMontantDepense = totalMontantDepense;
}

// Getters and Setters
public String getPlaqueImmatriculation() {
    return plaqueImmatriculation;
}

public void setPlaqueImmatriculation(String plaqueImmatriculation) {
    this.plaqueImmatriculation = plaqueImmatriculation;
}

public float getTotalMontantDepense() {
    return totalMontantDepense;
}

public void setTotalMontantDepense(float totalMontantDepense) {
    this.totalMontantDepense = totalMontantDepense;
}}