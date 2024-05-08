package com.benjamin_thomas_simon.mastermind.presenteur;

import android.app.Activity;
import android.util.Log;

import com.benjamin_thomas_simon.mastermind.modele.DBUtil;
import com.benjamin_thomas_simon.mastermind.modele.Feedback;
import com.benjamin_thomas_simon.mastermind.modele.Modele;
import com.benjamin_thomas_simon.mastermind.modele.ModeleManager;
import com.benjamin_thomas_simon.mastermind.modele.Parametre;
import com.benjamin_thomas_simon.mastermind.modele.controleur.Controleur;
import com.benjamin_thomas_simon.mastermind.modele.dao.MasterDao;
import com.benjamin_thomas_simon.mastermind.modele.dao.PartieDao;
import com.benjamin_thomas_simon.mastermind.modele.entite.Code;
import com.benjamin_thomas_simon.mastermind.modele.entite.EntiteMasterMind;
import com.benjamin_thomas_simon.mastermind.modele.entite.Partie;
import com.benjamin_thomas_simon.mastermind.modele.entite.Stat;
import com.benjamin_thomas_simon.mastermind.vues.Accueil;
import com.benjamin_thomas_simon.mastermind.vues.Jeu;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;

public class PresenteurMasterMind {


    private final Activity activite;
    private final Modele modele;
    private PartieDao partieDao;

    public PresenteurMasterMind(Activity activite) {
        this.activite = activite;
        this.modele = ModeleManager.getModele();
    }

    public void startGame() {
        ModeleManager.setCode();
    }

    public void obtenirEntite() {
        new Thread() {
            @Override
            public void run() {

                try {
                    //Demander au DAO de récupérer la liste des comptes bancaires :
                    EntiteMasterMind entiteMasterMind = MasterDao.getEntites();
                    //Injecter la liste dans le modèle :
                    Log.d("yessir", "its working!!!!");
                    modele.setEntiteMasterMind(entiteMasterMind);
                    //Demander à la vue (activité) de rafraichir le ListView :
                    ((Accueil) activite).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {

                        }
                    });
                } catch (JSONException e) {
                    ((Accueil) activite).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((Accueil) activite).afficherMessage("Problème dans le JSON des comptes");
                        }
                    });
                } catch (IOException e) {
                    ((Accueil) activite).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((Accueil) activite).afficherMessage("Problème d'accès à l'API");
                        }
                    });
                }
            }
        }.start();
    }

    public void modifierStats() {
        new Thread() {
            @Override
            public void run() {

                try {
                    EntiteMasterMind entiteMasterMind = MasterDao.getEntites();
                    //Injecter la liste dans le modèle :
                    Log.d("yessir", "its working!!!!");
                    modele.setEntiteMasterMind(entiteMasterMind);

                    String email = modele.getEmail();
                    int idCode = modele.getCode().getId();
                    int tentative = modele.getNombre_de_tentative();
                    Stat stat = new Stat(idCode, tentative, email);

                    if (stat.isRecordBattu()) {
                        //Demander au DAO de sauvegarder le compte bancaire :
                        boolean newEntree = modele.getStats().size() + 1 == stat.getId();
                        boolean sauvegardeReussie = MasterDao.enregistrerStat(stat, newEntree);

                        //Demander à la vue (activité) d'afficher un message en conséquence :
                        ((Jeu) activite).runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                if (sauvegardeReussie) {
                                    ((Jeu) activite).afficherMessage("Stat sauvé");
                                } else
                                    ((Jeu) activite).afficherMessage("Problème de sauvegarde de stat");
                            }
                        });
                    }

                } catch (IOException e) {
                    ((Jeu) activite).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((Jeu) activite).afficherMessage("Problème d'accès à l'API");
                        }
                    });
                } catch (JSONException e) {
                    ((Jeu) activite).runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            ((Jeu) activite).afficherMessage("Problème JSON du stat");
                            e.printStackTrace();
                        }
                    });
                }
            }
        }.start();
    }

    public EntiteMasterMind getEntiteMasterMind() {
        return this.modele.getEntiteMasterMind();
    }

    public List<Stat> getStats() {
        return this.modele.getStats();
    }

    public Code getCode(int num) {
        return this.modele.getCode(num);
    }

    public List<Code> getCodes() {
        return this.modele.getCodes();
    }

    public int[] model_validation_entree(String[] entree) {
        int nombre_de_tentative = modele.getNombre_de_tentative();
        nombre_de_tentative++;
        modele.setNombre_de_tentative(nombre_de_tentative);
        Code code = modele.getCode();
        if (code == null) {
            modele.chooseRandomCode(Parametre.getLongueur_du_code(), Parametre.getNombre_de_couleur());
            code = modele.getCode();
        }
        String[] codeCopie = code.getCode().clone();
        Feedback feedback = new Feedback(entree, codeCopie);
        int[] reponse = feedback.getFeedback();
        return reponse;
    }

    public int getNombre_de_tentative() {
        return modele.getNombre_de_tentative();
    }

    public String getCouleursSpecifique(int nbr) {
        EntiteMasterMind entiteMasterMind = modele.getEntiteMasterMind();
        List<String> couleurs = entiteMasterMind.getColor();
        return couleurs.get(nbr);
    }

    public boolean tentative_epuise() {
        int nombre_de_tentative = modele.getNombre_de_tentative();
        return nombre_de_tentative >= Parametre.getNombre_maximal_tentative();
    }

    public void newGame() {
        modele.setNombre_de_tentative(0);
        ModeleManager.setCode();
    }

    public void finPartie(Activity activity, String resultat) {
        int idCode = modele.getCode().getId();
        String courriel = modele.getEmail();
        int nbCouleur = Parametre.getNombre_de_couleur();
        int nbTentative = modele.getNombre_de_tentative();
        Partie partie = new Partie(idCode, courriel, nbCouleur, nbTentative, resultat);
        Controleur controleur = new Controleur(activity);
        DBUtil db = Controleur.getDBUtil();
        db.ajouterPartie(partie);
        if (resultat.compareTo("R") == 0) {
            this.modifierStats();
        }

    }

    public String getEmail() {
        return modele.getEmail();
    }

    public void setEmail(String email) {
        modele.setEmail(email);
    }
}