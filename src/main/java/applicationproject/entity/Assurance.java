package applicationproject.entity;

import java.sql.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonBackReference;

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
import applicationproject.enums.typeContrat;

@Data
@Entity
@Table(name = "assurance")
public class Assurance {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id", nullable = false)
    private int id;

    @Column(name = "idAssurance", nullable = false, unique = true)
    private String idAssurance; 

    @Column(name = "compagnieAssurance", nullable = false)
    private String compagnieAssurance; 

    @Column(name = "dateExpiration", nullable = false)
    private Date dateExpiration; 

    @Column(name = "dateDebut", nullable = false)
    private Date dateDebut; 

    @Column(name = "coutAssurance", nullable = false)
    private float coutAssurance; 

    @Enumerated(EnumType.STRING)
    @Column(name = "typeContrat", nullable = false,columnDefinition = "ENUM('annuel','trimestre','semestre')")  
    private typeContrat typeContrat; 

    @OneToMany(mappedBy = "assurance", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JsonBackReference
    private List<vehicules> vehicules;

}
