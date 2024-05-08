package com.benjamin_thomas_simon.mastermind.modele.entite;

import com.benjamin_thomas_simon.mastermind.modele.Feedback;

public class MasterMind {
    private final String[] code;
    private final Feedback[] feedbacks;
    private final int tentatives;

    public MasterMind(String[] code, Feedback[] feedbacks, int tentatives) {
        this.code = code;
        this.tentatives = tentatives;
        this.feedbacks = feedbacks;
    }
}
