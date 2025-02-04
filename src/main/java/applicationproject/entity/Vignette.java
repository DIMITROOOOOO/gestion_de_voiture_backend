package applicationproject.entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "vignette")
public class Vignette {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "numeroVignette", nullable = false,unique = true)
    private String numeroVignette; 

    @Column(name = "dateExpiration", nullable = false)
    private Date dateExpiration; 
    
    @Column(name = "dateDebut", nullable = false)
    private Date dateDebut;  

    @Column(name = "coutAchat", nullable = false)
    private float coutAchat;      

    @OneToMany(mappedBy = "vignette", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<vehicules> vehicules; 


}
