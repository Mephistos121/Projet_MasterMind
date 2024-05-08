package com.benjamin_thomas_simon.mastermind.modele;

import com.benjamin_thomas_simon.mastermind.modele.entite.Code;
import com.benjamin_thomas_simon.mastermind.modele.entite.EntiteMasterMind;
import com.benjamin_thomas_simon.mastermind.modele.entite.Stat;

import java.util.ArrayList;
import java.util.List;

public class Modele {
    private Code code;
    private String email;
    private int nombre_de_tentative = 0;
    private EntiteMasterMind entiteMasterMind;

    public EntiteMasterMind getEntiteMasterMind() {
        return entiteMasterMind;
    }

    public void setEntiteMasterMind(EntiteMasterMind entiteMasterMind) {
        this.entiteMasterMind = entiteMasterMind;
    }

    public List<Stat> getStats() {
        return entiteMasterMind.getStat();
    }

    public List<Code> getCodes() {
        return entiteMasterMind.getCode();
    }

    public List<String> getCouleurs() {
        return entiteMasterMind.getColor();
    }

    public List<String> getCouleurs(int nbr) {
        return entiteMasterMind.getColor(nbr);
    }

    public String getCouleursSpecifique(int nbr) {
        return entiteMasterMind.getColorSpecifique(nbr);
    }

    public Code getCode(int num) {

        for (Code c : entiteMasterMind.getCode()) {
            if (c.getId() == num) {
                return c;
            }
        }
        return null;
    }

    public void chooseRandomCode(int longueur_code, int nombre_couleur) {
        nombre_de_tentative = 0;
        ArrayList<Code> codesValide = new ArrayList<>();
        List<Code> codes = this.getCodes();
        for (Code code : codes) {
            if (code.getCode().length == longueur_code && code.getNbCouleurs() == nombre_couleur) {
                codesValide.add(code);
            }
        }
        int index_choisi = (int) (Math.random() * codesValide.size() - 1);

        code = codesValide.get(index_choisi);
    }

    public int getNombre_de_tentative() {
        return nombre_de_tentative;
    }

    public void setNombre_de_tentative(int nbr) {
        this.nombre_de_tentative = nbr;
    }

    public Code getCode_clone() {
        return code;
    }

    public Code getCode() {
        return this.code;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

}
