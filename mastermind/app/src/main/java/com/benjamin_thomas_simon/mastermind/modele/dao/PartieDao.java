package com.benjamin_thomas_simon.mastermind.modele.dao;

import android.app.Activity;

import com.benjamin_thomas_simon.mastermind.modele.DBUtil;
import com.benjamin_thomas_simon.mastermind.modele.controleur.Controleur;
import com.benjamin_thomas_simon.mastermind.modele.entite.Partie;

import java.util.List;

public class PartieDao {

    private final List<Partie> parties;

    public PartieDao(Activity activity) {
        DBUtil db = new Controleur(activity).getDBUtil();
        parties = db.retournerListeParties();
    }

    public List<Partie> getParties() {
        return parties;
    }
}
