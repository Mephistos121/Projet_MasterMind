package com.benjamin_thomas_simon.mastermind.modele.entite;


import android.util.Log;

import com.benjamin_thomas_simon.mastermind.modele.ModeleManager;

import java.util.List;

public class Stat {
    private int id;
    private int idCode;
    private int record;
    private String courriel;


    public Stat() {

    }

    public Stat(int idCode, int record, String courriel) {
        this.id = idFinder(idCode);
        this.idCode = idCode;
        this.record = record;
        this.courriel = courriel;
    }

    public int idFinder(int idCode) {
        List<Stat> list = ModeleManager.getModele().getEntiteMasterMind().getStat();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getIdCode() == idCode) {
                return list.get(i).getId();
            }
        }
        return list.get(list.size() - 1).getId() + 1;
    }

    public boolean isRecordBattu() {
        List<Stat> list = ModeleManager.getModele().getEntiteMasterMind().getStat();
        if (list.size() + 1 == this.id) {
            Log.d("what is id1", String.valueOf(this.id));
            Log.d("what is code1", String.valueOf(this.idCode));
            return true;
        } else if (list.get(this.id - 1).getRecord() > this.record) {
            Log.d("what is id2", String.valueOf(this.id));
            Log.d("what is code2", String.valueOf(this.idCode));
            return true;
        }
        return false;
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

    public int getRecord() {
        return record;
    }

    public void setRecord(int record) {
        this.record = record;
    }

    public String getCourriel() {
        return courriel;
    }

    public void setCourriel(String courriel) {
        this.courriel = courriel;
    }

}
