package applicationproject.dto;

import java.sql.Date;

import applicationproject.enums.statutChaffeurs;

public class ChauffeurDTO {
    private int chauffeur_id;
    private String nom;
    private String telephone;
    private String numero_permis;
    private Date date_embauche;
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