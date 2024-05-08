package com.benjamin_thomas_simon.mastermind.modele.controleur;

import android.app.Activity;

import com.benjamin_thomas_simon.mastermind.modele.DBUtil;
import com.benjamin_thomas_simon.mastermind.modele.entite.HistoriqueSQL;


public class Controleur {
    static DBUtil gestionnaireDataBase;

    public Controleur(Activity activity) {
        gestionnaireDataBase = new DBUtil(activity, HistoriqueSQL.DB_NAME, null, HistoriqueSQL.DB_VERSION);
    }

    public static DBUtil getDBUtil() {
        return gestionnaireDataBase;
    }
}
