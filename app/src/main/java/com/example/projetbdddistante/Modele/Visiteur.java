package com.example.projetbdddistante.Modele;

public class Visiteur {
    private String identifiant;
    private String nom;
    private String prenom;
    private String login;
    private String mdp;
    private String rue;
    private String cp;
    private String ville;
    private String dtembauche;

    public Visiteur(String identifiant, String nom, String prenom, String login, String mdp, String rue, String cp, String ville, String dtembauche) {
        this.identifiant = identifiant;
        this.nom = nom;
        this.prenom = prenom;
        this.login = login;
        this.mdp = mdp;
        this.rue = rue;
        this.cp = cp;
        this.ville = ville;
        this.dtembauche = dtembauche;
    }

    public String getIdentifiant() {return identifiant;}

    public void setIdentifiant(String identifiant) {this.identifiant = identifiant;}

    public String getNom() {return nom;}

    public void setNom(String nom) {this.nom = nom;}

    public String getPrenom() {return prenom;}

    public void setPrenom(String prenom) {this.prenom = prenom;}

    public String getLogin() {return login;}

    public void setLogin(String login) {this.login = login;}

    public String getMdp() {return mdp;}

    public void setMdp(String mdp) {this.mdp = mdp;}

    public String getRue() {return rue;}

    public void setRue(String rue) {this.rue = rue;}

    public String getCp() {return cp;}

    public void setCp(String cp) {this.cp = cp;}

    public String getVille() {return ville;}

    public void setVille(String ville) {this.ville = ville;}

    public String getDtembauche() {return dtembauche;}

    public void setDtembauche(String dtembauche) {this.dtembauche = dtembauche;}

    @Override
    public String toString() {
        return "Visiteur n°"+ identifiant + " : " + nom + " " + prenom;
    }
}

