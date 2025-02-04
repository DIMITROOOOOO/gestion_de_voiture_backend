package applicationproject.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
@Table(name = "consommationvehicule")
public class ConsommationVehicule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;
    
    @ManyToOne
    @JsonBackReference
    @JoinColumn(name = "vehicule_id", nullable = false)
    private vehicules vehicule; 

    @Column(name = "dateConsommation", nullable = false)
    private Date dateConsommation; 

    @Column(name = "quantiteConsommee", nullable = false)
    private float quantiteConsommee; 

    @Column(name = "montantDepense", nullable = false)
    private float montantDepense;
}
