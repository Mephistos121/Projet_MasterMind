package com.benjamin_thomas_simon.mastermind.modele.entite;

import java.util.List;

public class EntiteMasterMind {
    private List<String> couleurs;
    private List<Code> code;
    private List<Stat> stat;


    public EntiteMasterMind(List<String> couleurs, List<Code> code, List<Stat> stat) {
        this.couleurs = couleurs;
        this.code = code;
        this.stat = stat;
    }

    public List<String> getColor() {
        return couleurs;
    }

    public void setColor(List<String> couleurs) {
        this.couleurs = couleurs;
    }

    public List<String> getColor(int nbr) {
        if (nbr > this.couleurs.size() - 1) {
            nbr = this.couleurs.size() - 1;
        }
        for (int i = couleurs.size() - 1; i > nbr; i--) {
            this.couleurs.remove(this.couleurs.get(i));

        }
        return this.couleurs;
    }

    public List<Code> getCode() {
        return code;
    }

    public void setCode(List<Code> code) {
        this.code = code;
    }

    public List<Stat> getStat() {
        return stat;
    }

    public void setStat(List<Stat> stat) {
        this.stat = stat;
    }

    public String getColorSpecifique(int nbr) {
        return couleurs.get(nbr);
    }
}
