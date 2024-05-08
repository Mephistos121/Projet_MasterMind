package com.benjamin_thomas_simon.mastermind.modele.entite;

public class Partie {
    private int id;
    private int idCode;
    private String courriel;
    private int nbCouleur;
    private int nbTentative;
    private String resultat;

    public Partie() {

    }

    public Partie(int idCode, String courriel, int nbCouleur, int nbTentative, String resultat) {
        this.idCode = idCode;
        this.courriel = courriel;
        this.nbCouleur = nbCouleur;
        this.nbTentative = nbTentative;
        this.resultat = resultat;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getIdCode() {
        return idCode;
    }

    public void setIdCode(int idCode) {
        this.idCode = idCode;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

    public int getNbCouleur() {
        return nbCouleur;
    }

    public void setNbCouleur(int nbCouleur) {
        this.nbCouleur = nbCouleur;
    }

    public int getNbTentative() {
        return nbTentative;
    }

    public void setNbTentative(int nbTentative) {
        this.nbTentative = nbTentative;
    }

    public String getResultat() {
        return resultat;
    }

    public void setResultat(String resultat) {
        this.resultat = resultat;
    }

}
