package applicationproject.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import applicationproject.dto.VehiculeFeesResponse;
import applicationproject.enums.*;
import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.Data;

@Entity
@Data
@Table(name = "vehicules")

public class vehicules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vehicule_id",nullable = false) 
    private int vehiculeId;
    @Column(name = "plaque_immatriculation",unique = true,nullable = false)
    private String plaque_immatriculation;
    @Column(name = "kilometrage",nullable = false)
    private float kilometrage;
    @Column(name = "dateMiseEnCirculation",nullable = false)
    private Date dateMiseEnCirculation;

    @Enumerated(EnumType.STRING)
    @Column(name = "typeCarburant",nullable = false,columnDefinition = "ENUM('voiture','diesel','electrique')")
    private typeCarburant typeCarburant;

    @Column(name = "consommation",nullable = false)
    private float consommation;

    @Enumerated(EnumType.STRING)
    @Column(name = "statut" , columnDefinition = "ENUM('disponible','assigné','en_maintenance')")
    private statut statut;

    @ManyToOne
    @JsonManagedReference 
    @JoinColumn(name = "modele_id", nullable = false)
    private Modele modele;

    @ManyToOne
    @JsonManagedReference 
    @JoinColumn(name = "vignette_id")
    private Vignette vignette;

    @ManyToOne
    @JsonManagedReference 
    @JoinColumn(name = "assurance_id")
    private Assurance assurance;

    @ManyToOne
    @JsonManagedReference 
    @JoinColumn(name = "taxe_id")
    private Taxe taxe;

    @JsonManagedReference 
    @OneToMany(mappedBy = "vehicule", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Maintenance> maintenances;

    @OneToMany(mappedBy = "vehicule")
    @JsonManagedReference 
    private List<ConsommationVehicule> consommationVehicules;
    
public VehiculeFeesResponse calculateFeesSeparately() {
    float assuranceFees = 0;
    float vignetteFees = 0;
    float taxeFees = 0;
    float maintenanceFees = 0;
    float consommationFees = 0;

    if (this.assurance != null) {
        assuranceFees = this.assurance.getCoutAssurance();
    }

    if (this.vignette != null) {
        vignetteFees = this.vignette.getCoutAchat();
    }

    if (this.taxe != null) {
        taxeFees = this.taxe.getMontant();
    }

    if (this.maintenances != null) {
        for (Maintenance maintenance : this.maintenances) {
            maintenanceFees += maintenance.getDepense();
        }
    }

    if (this.consommationVehicules != null) {
        for (ConsommationVehicule consommation : this.consommationVehicules) {
            consommationFees += consommation.getMontantDepense();
        }
    }
    return new VehiculeFeesResponse(assuranceFees, vignetteFees, taxeFees, maintenanceFees, consommationFees);
}
}
