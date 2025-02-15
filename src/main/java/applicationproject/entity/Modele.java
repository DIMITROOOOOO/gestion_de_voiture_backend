package applicationproject.entity;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import applicationproject.enums.*;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;
@Table(name = "modele")
@Entity
@Data
public class Modele {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    @Column(name = "marque", nullable = false)
    private String marque; 
    
    @Column(name = "modele", nullable = false)
    private String modele; 

    @Enumerated(EnumType.STRING)
    @Column(name = "typeVehicle", nullable = false,columnDefinition = "ENUM('voiture','camion','voiture_commerciale')")
    private typeVehicle typeVehicle; 

    @Enumerated(EnumType.STRING)
    @Column(name = "typeBoiteVitesse", nullable = false,columnDefinition = "ENUM('auto','manuelle')")
    private typeBoiteVitesse typeBoiteVitesse;

    @OneToMany(mappedBy = "modele", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonIgnore
    private List<vehicules> vehicules;


    

}
