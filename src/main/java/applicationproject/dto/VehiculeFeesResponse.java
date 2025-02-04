package applicationproject.dto;

public class VehiculeFeesResponse {
    private float assuranceFees;
    private float vignetteFees;
    private float taxeFees;
    private float maintenanceFees;
    private float consommationFees;

    public VehiculeFeesResponse(float assuranceFees, float vignetteFees, float taxeFees, float maintenanceFees, float consommationFees) {
        this.assuranceFees = assuranceFees;
        this.vignetteFees = vignetteFees;
        this.taxeFees = taxeFees;
        this.maintenanceFees = maintenanceFees;
        this.consommationFees = consommationFees;
    }

    public float getAssuranceFees() {
        return assuranceFees;
    }

    public void setAssuranceFees(float assuranceFees) {
        this.assuranceFees = assuranceFees;
    }

    public float getVignetteFees() {
        return vignetteFees;
    }

    public void setVignetteFees(float vignetteFees) {
        this.vignetteFees = vignetteFees;
    }

    public float getTaxeFees() {
        return taxeFees;
    }

    public void setTaxeFees(float taxeFees) {
        this.taxeFees = taxeFees;
    }

    public float getMaintenanceFees() {
        return maintenanceFees;
    }

    public void setMaintenanceFees(float maintenanceFees) {
        this.maintenanceFees = maintenanceFees;
    }

    public float getConsommationFees() {
        return consommationFees;
    }

    public void setConsommationFees(float consommationFees) {
        this.consommationFees = consommationFees;
    }
}