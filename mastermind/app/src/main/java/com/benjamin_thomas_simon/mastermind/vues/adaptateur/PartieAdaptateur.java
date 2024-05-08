package com.benjamin_thomas_simon.mastermind.vues.adaptateur;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.graphics.drawable.DrawableCompat;

import com.benjamin_thomas_simon.mastermind.R;
import com.benjamin_thomas_simon.mastermind.modele.entite.Code;
import com.benjamin_thomas_simon.mastermind.modele.entite.Partie;
import com.benjamin_thomas_simon.mastermind.modele.entite.Stat;
import com.benjamin_thomas_simon.mastermind.presenteur.PresenteurMasterMind;

import java.util.List;
import java.util.Objects;

public class PartieAdaptateur extends ArrayAdapter<Partie> {
    private List<Partie> parties;
    private Context contexte;
    private int viewResourceId;
    private Resources ressources;
    private int reccordHolder;


    public PartieAdaptateur(@NonNull Context context, int resource, @NonNull List<Partie> parties) {
        super(context, resource, parties);

        this.contexte = context;
        this.viewResourceId = resource;
        this.ressources = contexte.getResources();
        this.parties = parties;
        reccordHolder = 0;
        setRecordHolder();
    }
    @SuppressLint("NewApi")
    @NonNull
    @Override
    public View getView(int position, @Nullable View view, @NonNull ViewGroup parent) {

        if (view == null) {
            LayoutInflater layoutInflater = (LayoutInflater) contexte.getSystemService(Context.
                    LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(this.viewResourceId, parent, false);
        }

        final Partie partie = parties.get(position);


        if (partie != null) {
            String utilisateur = partie.getCourriel();

            final TextView tvUtilisateur = (TextView) view.findViewById(R.id.utilisateur);

            tvUtilisateur.setText(utilisateur);
            if(position == reccordHolder){
                tvUtilisateur.setTextColor(Color.YELLOW);
            }

            PresenteurMasterMind presenteurMasterMind = new PresenteurMasterMind((Activity) contexte);

            Code code = presenteurMasterMind.getCode(partie.getIdCode());

            switch (code.getCode().length) { //affiche cercles

                case 6 :
                    ImageView hc6 = (ImageView) view.findViewById(R.id.hc6);
                    hc6.setImageResource(R.drawable.cercle_jeu);
                    hc6.setColorFilter(Color.parseColor("#"+code.getCode()[5]));
                case 5 :
                    ImageView hc5 = (ImageView) view.findViewById(R.id.hc5);
                    hc5.setImageResource(R.drawable.cercle_jeu);
                    hc5.setColorFilter(Color.parseColor("#"+code.getCode()[4]));
                case 4 :
                    ImageView hc4 = (ImageView) view.findViewById(R.id.hc4);
                    hc4.setImageResource(R.drawable.cercle_jeu);
                    hc4.setColorFilter(Color.parseColor("#"+code.getCode()[3]));
                case 3 :
                    ImageView hc3 = (ImageView) view.findViewById(R.id.hc3);
                    hc3.setImageResource(R.drawable.cercle_jeu);
                    hc3.setColorFilter(Color.parseColor("#"+code.getCode()[2]));
                case 2 :
                    ImageView hc2 = (ImageView) view.findViewById(R.id.hc2);
                    hc2.setImageResource(R.drawable.cercle_jeu);
                    hc2.setColorFilter(Color.parseColor("#"+code.getCode()[1]));
                    ImageView hc1 = (ImageView) view.findViewById(R.id.hc1);
                    hc1.setImageResource(R.drawable.cercle_jeu);
                    hc1.setColorFilter(Color.parseColor("#"+code.getCode()[0]));

                    break;
            }

            String res = null;

            switch (partie.getResultat()) { //affiche cercles

                case "R" :
                    res = "R";
                    break;
                case "E" :
                    res = "E";
                    break;
                case "A" :
                    res = "A";
                    break;
            }

            String resultat = partie.getNbCouleur() + " / " + res + " / " + partie.getNbTentative();

            if(position == reccordHolder){
                resultat += " Rec";
            }

            final  TextView tvResultat = (TextView) view.findViewById(R.id.resultat);

            tvResultat.setText(resultat);

        }
        return view;
    }

    @Override public int getCount(){
        return super.getCount();
    }

    public void setRecordHolder(){
        for(int i = 0; i < parties.size(); i++){
            if(Objects.equals(parties.get(i).getResultat(), "R")
                    && parties.get(i).getNbTentative() < parties.get(reccordHolder).getNbTentative())
                    reccordHolder = i;
            }

    }
}
