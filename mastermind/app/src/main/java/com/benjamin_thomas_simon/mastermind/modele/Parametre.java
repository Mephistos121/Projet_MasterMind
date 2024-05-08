package com.benjamin_thomas_simon.mastermind.modele;

public class Parametre {
    private static int longueur_du_code;
    private static int nombre_de_couleur;
    private static int nombre_maximal_tentative;
    private static Parametre instance;

    private Parametre(int longueur_du_code, int nombre_de_couleur, int nombre_maximal_tentative) {
        Parametre.longueur_du_code = longueur_du_code;
        Parametre.nombre_de_couleur = nombre_de_couleur;
        Parametre.nombre_maximal_tentative = nombre_maximal_tentative;
    }

    public static Parametre getInstance() {
        if (instance == null) {
            instance = new Parametre(4, 8, 10);
        }
        return instance;
    }

    public static int getLongueur_du_code() {
        return longueur_du_code;
    }

    public static void setLongueur_du_code(int longueur_du_code) {
        Parametre.longueur_du_code = longueur_du_code;
    }

    public static int getNombre_de_couleur() {
        return nombre_de_couleur;
    }

    public static void setNombre_de_couleur(int nombre_de_couleur) {
        Parametre.nombre_de_couleur = nombre_de_couleur;
    }

    public static int getNombre_maximal_tentative() {
        return nombre_maximal_tentative;
    }

    public static void setNombre_maximal_tentative(int nombre_maximal_tentative) {
        Parametre.nombre_maximal_tentative = nombre_maximal_tentative;
    }
}
