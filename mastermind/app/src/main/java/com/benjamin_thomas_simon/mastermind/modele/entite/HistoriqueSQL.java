package com.benjamin_thomas_simon.mastermind.modele.entite;


public class HistoriqueSQL {
    public static final String DB_NAME = "historiques.db";
    public static final int DB_VERSION = 1;
    public static final String TABLE_NAME = "HISTORIQUE";

    public class Colonnes {
        public static final String ID = "_ID";
        public static final String IDCODE = "_IDCODE";
        public static final String COURRIEL = "_COURRIEL";
        public static final String NBCOULEUR = "_NBCOULEUR";
        public static final String NBTENTATIVE = "_NBTENTATIVE";
        public static final String RESULTAT = "_RESULTAT";

    }
}

