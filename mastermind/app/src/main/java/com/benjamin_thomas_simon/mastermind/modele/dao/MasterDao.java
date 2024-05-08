package com.benjamin_thomas_simon.mastermind.modele.dao;

import com.benjamin_thomas_simon.mastermind.modele.entite.EntiteMasterMind;
import com.benjamin_thomas_simon.mastermind.modele.entite.Stat;

import org.json.JSONException;

import java.io.IOException;

public class MasterDao {
    public static EntiteMasterMind getEntites() throws IOException, JSONException {
        return new HttpJsonService().getEntites();
    }

    public static boolean enregistrerStat(Stat stat, boolean newEntree) throws IOException, JSONException {
        return new HttpJsonService().enregistrerStat(stat, newEntree);
    }

}