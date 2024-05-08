package com.benjamin_thomas_simon.mastermind.modele.entite;

public class Code {
    private int id;
    private String[] code;
    private int nbCouleurs;

    Code(int id, String[] code, int nbCouleurs) {
        this.id = id;
        this.code = code;
        this.nbCouleurs = nbCouleurs;
    }
    Code(){//IMPORTANT POUR HTTPJSONSERVICE SANS CA, PROGRAMME PLANTE
    }
    public String toString() { //FOR DEBUGGING PURPOSES
        String codes = "";
        for (int i = 0; i < this.getCode().length; i++) {
            codes += this.getCode()[i] + " ";
        }
        return "ID: " + this.getId() + "\n CODE: " + codes + "\n nbCouleurs: " + this.getNbCouleurs();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String[] getCode() {
        return code;
    }

    public void setCode(String[] code) {
        this.code = code;
    }

    public int getNbCouleurs() {
        return nbCouleurs;
    }

    public void setNbCouleurs(int nbCouleurs) {
        this.nbCouleurs = nbCouleurs;
    }

}
