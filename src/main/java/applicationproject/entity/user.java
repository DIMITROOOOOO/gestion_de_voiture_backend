package applicationproject.entity;

import applicationproject.enums.roles;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "utilisateurs")
public class user {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "utilisateur_id") 
    private int id;
     @Column(name = "nom_utilisateur") 
    private String nom;
    @Column(name = "mot_de_passe") 
    private String motdepasse;
    @Enumerated(EnumType.STRING)
    @Column(name = "role", columnDefinition = "ENUM('gestionnaire', 'admin', 'chauffeur')")
    private roles role;
    @Column(name = "email") 
    private String email;
    public void setFullName(String fullName) {
        this.nom = fullName;
    }

    public void setPassword(String password) {
        this.motdepasse = password;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public void setRole(roles role) {
        this.role = role;
    }
}