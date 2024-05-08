package com.benjamin_thomas_simon.mastermind.modele;

public class Feedback {
    private final int bonne_reponse;
    private final int bonne_couleur;

    public Feedback(String[] entree, String[] codeCopie) {
        int[] reponse = new int[2];
        int bonne_reponse = 0;
        int bonne_couleur = 0;
        int[] comparaison = new int[2];
        //bonne couleur bon index
        for (int i = 0; i < codeCopie.length; i++) {
            if (entree[i].compareTo(codeCopie[i]) == 0) {
                entree[i] = "";
                codeCopie[i] = "";
                bonne_reponse++;
            }

        }
        //bonne couleur mauvais index
        for (int i = 0; i < codeCopie.length; i++) {
            if (entree[i].compareTo("") != 0) {//si cest == à "" alors c'est un rouge pas besoin de recheck
                for (int j = 0; j < codeCopie.length; j++) {
                    if (entree[i].compareTo(codeCopie[j]) == 0 && codeCopie[j].compareTo("") != 0) {
                        bonne_couleur++;
                        entree[i] = "";
                        codeCopie[j] = "";
                    }
                }
            }
        }
        this.bonne_reponse = bonne_reponse;
        this.bonne_couleur = bonne_couleur;
    }

    public int[] getFeedback() {
        int[] reponse = new int[2];
        reponse[0] = this.bonne_reponse;
        reponse[1] = this.bonne_couleur;
        return reponse;
    }

}
