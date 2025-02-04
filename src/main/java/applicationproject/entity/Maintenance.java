package applicationproject.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import applicationproject.enums.etat;
import applicationproject.enums.nature;

@Entity
@Data
public class Maintenance {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "maintenance_id", nullable = false)
    private int maintenanceId;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "vehicule_id", nullable = false)
    private vehicules vehicule;
    @Transient 
    private int vehiculeId;

    @Column(name = "type" , nullable = false)
    private String type;

    @Enumerated(EnumType.STRING)
    @Column(name = "nature", nullable = false)
    private nature nature;

    @Column(name = "date_planifiee", nullable = false)
    private Date datePlanifiee;

    @Column(name = "depense")
    private float depense;

    @Column(name ="description" ,nullable = false)
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "etat" )
    private etat statut;

  
}