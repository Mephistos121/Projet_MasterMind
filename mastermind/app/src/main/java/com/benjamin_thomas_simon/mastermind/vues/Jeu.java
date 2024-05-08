package com.benjamin_thomas_simon.mastermind.vues;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.media.Image;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.ContextCompat;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.gridlayout.widget.GridLayout;
import com.benjamin_thomas_simon.mastermind.R;
import com.benjamin_thomas_simon.mastermind.modele.Parametre;
import com.benjamin_thomas_simon.mastermind.presenteur.PresenteurMasterMind;
import java.util.ArrayList;


public class Jeu extends AppCompatActivity implements View.OnClickListener {
    private Button abandonner;
    private Button nouvelle_partie;
    private Button valider;
    private ImageButton couleur1;
    private GridLayout jeu;
    private ImageButton focused_button;
    private int indexJeu=0;
    private int indexInferieurLimite = 0;
    private int indexSuperieurLimite= Parametre.getLongueur_du_code();
    private int dernier_index_validé = 0;
    private int nombre_couleur = Parametre.getNombre_de_couleur();
    private ScrollView scrollView;


    private ArrayList<ImageButton> bouton_palette = new ArrayList<ImageButton>();

    private LinearLayout palette;
    private LinearLayout palette_rangee1;
    private LinearLayout palette_range2;
    private PresenteurMasterMind presenteurMasterMind;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_jeu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //debut de la partie set le code
        presenteurMasterMind = new PresenteurMasterMind(this);
        presenteurMasterMind.startGame();


        //assignation des boutons
        abandonner = findViewById(R.id.abandonner);
        nouvelle_partie = findViewById(R.id.nouvelle_partie);
        valider = findViewById(R.id.valider);
        activer_valide();

        abandonner.setOnClickListener(this);
        nouvelle_partie.setOnClickListener(this);
        valider.setOnClickListener(this);

        palette = findViewById(R.id.palette);
        palette_rangee1 = (LinearLayout) palette.getChildAt(0);
        palette_range2 = (LinearLayout) palette.getChildAt(1);
        jeu = findViewById(R.id.gridLayout);

        jeu.setRowCount(Parametre.getNombre_maximal_tentative());
        jeu.setColumnCount(Parametre.getLongueur_du_code()+1);
        populer_array();
        jeu_builder();
    }


    @Override
    public void onClick(View v) {
        final int i = v.getId();
        if(i==R.id.abandonner){
                new AlertDialog.Builder(this)
                    .setTitle("Abandonner la partie")
                    .setMessage("Êtes vous sûr de vouloir quitter la partie? Votre progression sera perdue.")
                    .setPositiveButton("Yes", new DialogInterface.OnClickListener(){

                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    fin_de_partie("A",presenteurMasterMind.getNombre_de_tentative(),Parametre.getNombre_de_couleur());
                                    finish();
                                }
                            })
                    .setNegativeButton("No", null)
                    .show();
        }
        else if(i==R.id.nouvelle_partie){
            fin_de_partie("A",presenteurMasterMind.getNombre_de_tentative(),Parametre.getNombre_de_couleur());
            nouvellePartie();

        }
        else if(i==R.id.valider){
            dernier_index_validé = indexJeu;
            activer_valide();
            verifierCode();
            indexInferieurLimite += Parametre.getLongueur_du_code()+1;
            indexSuperieurLimite += Parametre.getLongueur_du_code()+1;
            System.out.println("Validation");
        }
        else if(i==R.id.imageButton){
            if(indexJeu<indexSuperieurLimite){//Empecher daller plus loin que la limite de la row actuelle
                focused_button.setColorFilter(((ImageButton)(palette_rangee1.getChildAt(0))).getColorFilter());
                focused_button.setTag("c"+0);
                indexJeu++;
                if(jeu.getChildAt(indexJeu).getAccessibilityClassName().toString().compareTo("android.widget.ImageButton")==0){
                    focused_button = (ImageButton) jeu.getChildAt(indexJeu);
                }else{
                    indexJeu++;
                    focused_button = (ImageButton) jeu.getChildAt(indexJeu);
                }
                activer_valide();
            }
        }
        else if(i==R.id.imageButton2){
            if(indexJeu<indexSuperieurLimite){//Empecher daller plus loin que la limite de la row actuelle
                focused_button.setColorFilter(((ImageButton)(palette_rangee1.getChildAt(1))).getColorFilter());
                focused_button.setTag("c"+1);
                indexJeu++;
                if(jeu.getChildAt(indexJeu).getAccessibilityClassName().toString().compareTo("android.widget.ImageButton")==0){
                    focused_button = (ImageButton) jeu.getChildAt(indexJeu);
                }else{
                    indexJeu++;
                    focused_button = (ImageButton) jeu.getChildAt(indexJeu);
                }
                activer_valide();
            }

        }
        else if(i==R.id.imageButton3){
            if(indexJeu<indexSuperieurLimite){//Empecher daller plus loin que la limite de la row actuelle
                focused_button.setColorFilter(((ImageButton)(palette_rangee1.getChildAt(2))).getColorFilter());
                focused_button.setTag("c"+2);
                indexJeu++;
                if(jeu.getChildAt(indexJeu).getAccessibilityClassName().toString().compareTo("android.widget.ImageButton")==0){
                    focused_button = (ImageButton) jeu.getChildAt(indexJeu);
                }else{
                    indexJeu++;
                    focused_button = (ImageButton) jeu.getChildAt(indexJeu);
                }
            }
            activer_valide();

        }
        else if(i==R.id.imageButton4){
            if(indexJeu<indexSuperieurLimite){//Empecher daller plus loin que la limite de la row actuelle
                focused_button.setColorFilter(((ImageButton)(palette_rangee1.getChildAt(3))).getColorFilter());
                focused_button.setTag("c"+3);
                indexJeu++;
                if(jeu.getChildAt(indexJeu).getAccessibilityClassName().toString().compareTo("android.widget.ImageButton")==0){
                    focused_button = (ImageButton) jeu.getChildAt(indexJeu);
                }else{
                    indexJeu++;
                    focused_button = (ImageButton) jeu.getChildAt(indexJeu);
                }
                activer_valide();
            }
        }
        else if(i==R.id.imageButton5){
            if(indexJeu<indexSuperieurLimite){//Empecher daller plus loin que la limite de la row actuelle
                focused_button.setColorFilter(((ImageButton)(palette_range2.getChildAt(0))).getColorFilter());
                focused_button.setTag("c"+4);
                indexJeu++;
                if(jeu.getChildAt(indexJeu).getAccessibilityClassName().toString().compareTo("android.widget.ImageButton")==0){
                    focused_button = (ImageButton) jeu.getChildAt(indexJeu);
                }else{
                    indexJeu++;
                    focused_button = (ImageButton) jeu.getChildAt(indexJeu);
                }
                activer_valide();
            }
        }
        else if(i==R.id.imageButton6){
            if(indexJeu<indexSuperieurLimite){//Empecher daller plus loin que la limite de la row actuelle
                focused_button.setColorFilter(((ImageButton)(palette_range2.getChildAt(1))).getColorFilter());
                focused_button.setTag("c"+5);
                indexJeu++;
                if(jeu.getChildAt(indexJeu).getAccessibilityClassName().toString().compareTo("android.widget.ImageButton")==0){
                    focused_button = (ImageButton) jeu.getChildAt(indexJeu);
                }else{
                    indexJeu++;
                    focused_button = (ImageButton) jeu.getChildAt(indexJeu);
                }
                activer_valide();
            }
        }
        else if(i==R.id.imageButton7){
            if(indexJeu<indexSuperieurLimite){//Empecher daller plus loin que la limite de la row actuelle
                focused_button.setColorFilter(((ImageButton)(palette_range2.getChildAt(2))).getColorFilter());
                focused_button.setTag("c"+6);
                indexJeu++;
                if(jeu.getChildAt(indexJeu).getAccessibilityClassName().toString().compareTo("android.widget.ImageButton")==0){
                    focused_button = (ImageButton) jeu.getChildAt(indexJeu);
                }else{
                    indexJeu++;
                    focused_button = (ImageButton) jeu.getChildAt(indexJeu);
                }
                activer_valide();
            }
        }
        else if(i==R.id.imageButton8){
            if(indexJeu<indexSuperieurLimite){//Empecher daller plus loin que la limite de la row actuelle
                focused_button.setColorFilter(((ImageButton)(palette_range2.getChildAt(3))).getColorFilter());
                focused_button.setTag("c"+7);
                indexJeu++;
                focused_button= (ImageButton) jeu.getChildAt(0);
                if(jeu.getChildAt(indexJeu).getAccessibilityClassName().toString().compareTo("android.widget.ImageButton")==0){
                    focused_button = (ImageButton) jeu.getChildAt(indexJeu);
                }else{
                    indexJeu++;
                    focused_button = (ImageButton) jeu.getChildAt(indexJeu);
                }
                activer_valide();
            }
        }
    }
    private void verifierCode(){
        int verifier_index = indexJeu-1-Parametre.getLongueur_du_code();
        String [] entree = new String[Parametre.getLongueur_du_code()];
        for(int i = 0; i<entree.length; i++){//recupere les couleurs entrees et les mets dans un tableau
            ImageButton input = (ImageButton) jeu.getChildAt(verifier_index);
            String color =  ((String) input.getTag());
            entree[i] = presenteurMasterMind.getCouleursSpecifique(Integer.parseInt(color.substring(1)));
            verifier_index++;
        }
        int [] reponse = presenteurMasterMind.model_validation_entree(entree); //envoie au modele l'entree pour correction
        System.out.println("BONS:"+reponse[0]+"MYEN:"+reponse[1]);
        if(reponse[0]==Parametre.getLongueur_du_code()){
            fin_de_partie("R",presenteurMasterMind.getNombre_de_tentative(),Parametre.getNombre_de_couleur());
        }
        else if(presenteurMasterMind.tentative_epuise()){
            fin_de_partie("E", presenteurMasterMind.getNombre_de_tentative(), Parametre.getNombre_de_couleur());
        }
        else{
            ImageView imageView = (ImageView) jeu.getChildAt(verifier_index); //feedback
            LayerDrawable feedback = (LayerDrawable) imageView.getDrawable(); //get le drawable de feedback
            int index =0;
            for (int i = 0 ; i< reponse[0]; i++) {
                Drawable cercle = feedback.getDrawable(index);
                System.out.println("colorier le cercle #"+ (index));
                index++;
                cercle.setTint(Color.parseColor("#"+presenteurMasterMind.getCouleursSpecifique(0)));
            }
            for( int i = 0 ; i < reponse[1]; i++){
                Drawable cercle = feedback.getDrawable(index);
                System.out.println("colorier le cercle #"+ (index));
                index++;
                cercle.setTint(Color.parseColor("#"+presenteurMasterMind.getCouleursSpecifique(7)));
            }
        }
    }
    private void populer_array(){
        int couleur_index = 0;
        for(int i=8;i>nombre_couleur;i--){
            View v = palette.getChildAt(i);
            palette.removeView(v);
        }
        for(int i = 0 ; i < palette.getChildCount() ; i++){
            LinearLayout rangee = (LinearLayout) palette.getChildAt(i);
            int nbr = rangee.getChildCount();
           for(int j =0 ; j < nbr ; j++){
               ImageButton img = (ImageButton) rangee.getChildAt(j);

               img.setColorFilter(Color.parseColor("#"+presenteurMasterMind.getCouleursSpecifique(couleur_index)));
               img.setTag("c"+couleur_index);
               couleur_index++;
           }
        }
    switch (nombre_couleur){
        case 8:
            bouton_palette.add(findViewById(R.id.imageButton8));
            findViewById(R.id.imageButton8).setVisibility(View.VISIBLE);
        case 7:
            bouton_palette.add(findViewById(R.id.imageButton7));
            findViewById(R.id.imageButton7).setVisibility(View.VISIBLE);
        case 6:
            bouton_palette.add(findViewById(R.id.imageButton6));
            findViewById(R.id.imageButton6).setVisibility(View.VISIBLE);
        case 5:
            bouton_palette.add(findViewById(R.id.imageButton5));
            findViewById(R.id.imageButton5).setVisibility(View.VISIBLE);
        case 4:
            bouton_palette.add(findViewById(R.id.imageButton4));
            findViewById(R.id.imageButton4).setVisibility(View.VISIBLE);
        case 3:
            bouton_palette.add(findViewById(R.id.imageButton3));
            findViewById(R.id.imageButton3).setVisibility(View.VISIBLE);
        case 2:
            bouton_palette.add(findViewById(R.id.imageButton2));//Minimum de couleurs est 2 donc 1 et 2 ensemble
            findViewById(R.id.imageButton2).setVisibility(View.VISIBLE);
            bouton_palette.add(findViewById(R.id.imageButton));
            findViewById(R.id.imageButton).setVisibility(View.VISIBLE);
    }









        for (ImageButton bouton: bouton_palette ) {
            bouton.setOnClickListener(this);
        }
    }


    public void afficherMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
    private void jeu_builder(){

        ImageButton imageButton = new ImageButton(this);
        ImageView imageView;

        for(int j = 0; j<jeu.getRowCount();j++){
            for(int i=0;i<jeu.getColumnCount();i++){

                if(i+1==jeu.getColumnCount()){
                    imageView = new ImageView(this);
                    imageView = getImageView();
                    imageView.setPadding(100,10,10,10);
                    jeu.addView(imageView);
                }
                else{
                    imageButton = new ImageButton(this);
                    imageButton.setPadding(0,10,50,10);
                    imageButton.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            System.out.println(jeu.indexOfChild(v));
                            if(jeu.indexOfChild(v)>indexInferieurLimite-1&&jeu.indexOfChild(v)<indexSuperieurLimite){
                                indexJeu= jeu.indexOfChild(v);
                                focused_button = (ImageButton) v;
                            }

                        }
                    });
                    imageButton.setImageResource(R.drawable.cercle_jeu);
                    jeu.addView(imageButton);
                }
            }
       }
        focused_button= (ImageButton) jeu.getChildAt(0);


    }

    @NonNull
    private ImageView getImageView() {
        ImageView imageView = new ImageView(this);
        if(Parametre.getLongueur_du_code()==2){imageView.setImageResource(R.drawable.feedback2);}
        else if(Parametre.getLongueur_du_code()==3){imageView.setImageResource(R.drawable.feedback3);}
        else if(Parametre.getLongueur_du_code()==4){imageView.setImageResource(R.drawable.feedback4);}
        else if(Parametre.getLongueur_du_code()==5){imageView.setImageResource(R.drawable.feedback5);}
        else if(Parametre.getLongueur_du_code()==6){imageView.setImageResource(R.drawable.feedback6);}
        return imageView;
    }
    private void fin_de_partie(String raison, int tentative, int nombre_couleur){
        System.out.println("FIN DE PARTIE! Raison="+raison+" tentative="+tentative+" nombre de couleurs:"+nombre_couleur);
        if(raison.compareTo("E")==0){
            presenteurMasterMind.finPartie(this,raison);
            new AlertDialog.Builder(this)
                    .setTitle("Perdu")
                    .setMessage("Vos tentatives sont épuisées.")
                    .setPositiveButton("Nouvelle partie", new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            nouvellePartie();


                        }
                    })
                    .setNegativeButton("Accueil", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        }
        else if(raison.compareTo("R")==0){
            presenteurMasterMind.finPartie(this,raison);

            new AlertDialog.Builder(this)
                    .setTitle("Victoire")
                    .setMessage("Vous avez gagné.")
                    .setPositiveButton("Nouvelle partie", new DialogInterface.OnClickListener(){

                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            nouvellePartie();


                        }
                    })
                    .setNegativeButton("Accueil", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            finish();
                        }
                    })
                    .show();
        }
        else if(raison.compareTo("A")==0){
            presenteurMasterMind.finPartie(this,raison);
        }
        Drawable cercle_jeu = AppCompatResources.getDrawable(this,R.drawable.cercle_jeu);
        cercle_jeu.setTint(Color.parseColor("#FF999999"));//il faut reset les couleurs apres chaque partie


    }
    private void activer_valide(){
        System.out.println("index"+indexJeu+"derniervalide"+dernier_index_validé);
        if(indexJeu%(Parametre.getLongueur_du_code()+1)==0&&indexJeu!=dernier_index_validé){
            valider.setEnabled(true);
        }
        else{
            valider.setEnabled(false);
        }
    }
    private void nouvellePartie(){
        indexJeu=0;
        dernier_index_validé = 0;
        indexInferieurLimite = 0;
        indexSuperieurLimite= Parametre.getLongueur_du_code();
        presenteurMasterMind.newGame();
        System.out.println("new game");
        jeu.removeAllViews();
        jeu_builder();
    }
}