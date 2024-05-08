package com.benjamin_thomas_simon.mastermind.vues;

import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.Spinner;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.benjamin_thomas_simon.mastermind.R;
import com.benjamin_thomas_simon.mastermind.modele.Parametre;

public class Configuration extends AppCompatActivity implements  View.OnClickListener{
    Spinner code;
    Spinner couleur;
    Spinner tentative;
    ImageButton imageButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_configuration);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imageButton = findViewById(R.id.back_arrow_configuration);
        imageButton.setOnClickListener(this);
        code = findViewById(R.id.spinner_code);
        couleur = findViewById(R.id.spinner_couleurs);
        tentative = findViewById(R.id.spinner_tentative);

       Resources resources = getResources();
       String [] codeList = resources.getStringArray(R.array.longueur_code);
       String [] couleurList = resources.getStringArray(R.array.nombre_couleur);
       String [] tentativeList = resources.getStringArray(R.array.nombre_maximal_tentative);

       ArrayAdapter adapter1 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,codeList);
       ArrayAdapter adapter2 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,couleurList);
       ArrayAdapter adapter3 = new ArrayAdapter(this, android.R.layout.simple_list_item_1,tentativeList);
       code.setAdapter(adapter1);
       couleur.setAdapter(adapter2);
       tentative.setAdapter(adapter3);

       code.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               Parametre.setLongueur_du_code(Integer.parseInt(code.getItemAtPosition(position).toString()));
           }
           @Override
           public void onNothingSelected(AdapterView<?> parentView) {
               Parametre.setLongueur_du_code(4);
           }

       });
       couleur.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
           @Override
           public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
               Parametre.setNombre_de_couleur(Integer.parseInt(couleur.getItemAtPosition(position).toString()));
           }
           @Override
           public void onNothingSelected(AdapterView<?> parentView) {
               Parametre.setNombre_de_couleur(8);
           }

       });
        tentative.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                Parametre.setNombre_maximal_tentative(Integer.parseInt(tentative.getItemAtPosition(position).toString()));
            }
            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                Parametre.setNombre_maximal_tentative(10);
            }

        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.back_arrow_configuration){
            finish();
        }
    }
}