package com.benjamin_thomas_simon.mastermind.vues;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.benjamin_thomas_simon.mastermind.R;
import com.benjamin_thomas_simon.mastermind.modele.Modele;
import com.benjamin_thomas_simon.mastermind.modele.ModeleManager;
import com.benjamin_thomas_simon.mastermind.modele.Parametre;
import com.benjamin_thomas_simon.mastermind.presenteur.PresenteurMasterMind;

import java.util.regex.Pattern;

public class Accueil extends AppCompatActivity implements View.OnClickListener {
    private PresenteurMasterMind presenteurMasterMind;
    private Button btnJouer;
    private Button btnConfig;
    private Button btnHist;
    private Button btnEmail;
    private EditText editTextTextEmailAddress;
    private String email="";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        presenteurMasterMind = new PresenteurMasterMind(this);
        this.presenteurMasterMind.obtenirEntite();

        btnJouer = findViewById(R.id.button_jouer);
        btnJouer.setEnabled(false);
        btnConfig = findViewById(R.id.button_config);
        btnHist = findViewById(R.id.button_historique);
        btnEmail = findViewById(R.id.button_email);
        editTextTextEmailAddress = findViewById(R.id.editTextTextEmailAddress);

        btnJouer.setOnClickListener(this);
        btnHist.setOnClickListener(this);
        btnConfig.setOnClickListener(this);
        btnEmail.setOnClickListener(this);
        Parametre.getInstance();
    }

    @Override
    public void onClick(View v) {
        final int i = v.getId();
        Intent intention;

        if(i==R.id.button_jouer){
                intention = new Intent(this, Jeu.class);
                startActivity(intention);
        }
        else  if(i==R.id.button_config){
            intention = new Intent(this,Configuration.class);
            startActivity(intention);
        }else if (i==R.id.button_historique){
            intention = new Intent(this,Historique.class);
            startActivity(intention);
        }else if (i==R.id.button_email) {
            email=editTextTextEmailAddress.getText().toString();
            String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";
            if(patternMatches(email,regexPattern)){
                btnJouer.setEnabled(true);
                presenteurMasterMind.setEmail(editTextTextEmailAddress.getText().toString());
            }
            else{
                btnJouer.setEnabled(false);
            }
        }
    }
    public static boolean patternMatches(String emailAddress, String regexPattern) {
        return Pattern.compile(regexPattern)
                .matcher(emailAddress)
                .matches();
    }

    public void afficherMessage(String message) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
    }
}