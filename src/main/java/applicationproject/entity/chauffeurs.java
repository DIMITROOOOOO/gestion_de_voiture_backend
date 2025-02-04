package applicationproject.entity;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import  applicationproject.enums.statutChaffeurs;

@Entity
@Data
@Table(name = "chauffeurs")
public class chauffeurs {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="chauffeur_id")
    private int chauffeur_id;
    @Column(name = "nom")
    private String nom;
    @Column (name = "telephone")
    private String telephone;
    @Column(name = "numero_permis")
    private String numero_permis;
    @Column(name = "date_embauche")
    private Date date_embauche;
    @Enumerated(EnumType.STRING)
    @Column(name = "statut_chaffeurs")
    private statutChaffeurs statutChaffeurs;


    public int getChauffeur_id() {
        return chauffeur_id;
    }

    public void setChauffeur_id(int chauffeur_id) {
        this.chauffeur_id = chauffeur_id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getNumero_permis() {
        return numero_permis;
    }

    public void setNumero_permis(String numero_permis) {
        this.numero_permis = numero_permis;
    }

    public Date getDate_embauche() {
        return date_embauche;
    }

    public void setDate_embauche(Date date_embauche) {
        this.date_embauche = date_embauche;
    }

    public statutChaffeurs getStatutChaffeurs() {
        return statutChaffeurs;
    }

    public void setStatutChaffeurs(statutChaffeurs statutChaffeurs) {
        this.statutChaffeurs = statutChaffeurs;
    }
}
