package com.benjamin_thomas_simon.mastermind.vues;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.benjamin_thomas_simon.mastermind.R;
import com.benjamin_thomas_simon.mastermind.modele.dao.PartieDao;
import com.benjamin_thomas_simon.mastermind.modele.entite.Partie;
import com.benjamin_thomas_simon.mastermind.vues.adaptateur.PartieAdaptateur;

import java.util.List;

public class Historique extends AppCompatActivity implements View.OnClickListener{

    private List<Partie> lesParties;
    private PartieDao dao;
    private ListView lvParties;
    private PartieAdaptateur adaptateur;
    private ImageButton imageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_historique);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        imageButton = findViewById(R.id.back_arrow_historique);
        imageButton.setOnClickListener(this);
        lvParties = (ListView) findViewById(R.id.liste_historique);

        dao=new PartieDao(this);

        lesParties=dao.getParties();

        adaptateur = new PartieAdaptateur(this,R.layout.liste_historique, lesParties);
        lvParties.setAdapter(adaptateur);

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id==R.id.back_arrow_historique){
            finish();
        }
    }
}