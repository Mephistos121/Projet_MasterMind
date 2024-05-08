package com.benjamin_thomas_simon.mastermind.modele;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

import com.benjamin_thomas_simon.mastermind.modele.entite.HistoriqueSQL;
import com.benjamin_thomas_simon.mastermind.modele.entite.Partie;

import java.util.ArrayList;

public class DBUtil extends SQLiteOpenHelper {
    public DBUtil(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Création de la base de données "parties.db"
        try {
            String requeteCreation = String.format("CREATE TABLE %s (%s INTEGER PRIMARY KEY AUTOINCREMENT, %s TEXT, %s INTEGER, %s INTEGER, %s INTEGER, %s TEXT)", HistoriqueSQL.TABLE_NAME, HistoriqueSQL.Colonnes.ID, HistoriqueSQL.Colonnes.IDCODE, HistoriqueSQL.Colonnes.COURRIEL, HistoriqueSQL.Colonnes.NBCOULEUR, HistoriqueSQL.Colonnes.NBTENTATIVE, HistoriqueSQL.Colonnes.RESULTAT);
            db.execSQL(requeteCreation);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        String requeteModification = String.format("ALTER TABLE %s ADD %s int not null", HistoriqueSQL.TABLE_NAME, "NOUVELLE_COLONNE"); // par exemple
        db.execSQL(requeteModification);
    }

    public void ajouterPartie(Partie partie) {
        SQLiteDatabase db = getWritableDatabase();
        try {
            ContentValues valeur = new ContentValues();
            valeur.put(HistoriqueSQL.Colonnes.IDCODE, partie.getIdCode());
            valeur.put(HistoriqueSQL.Colonnes.COURRIEL, partie.getCourriel());
            valeur.put(HistoriqueSQL.Colonnes.NBCOULEUR, partie.getNbCouleur());
            valeur.put(HistoriqueSQL.Colonnes.NBTENTATIVE, partie.getNbTentative());
            valeur.put(HistoriqueSQL.Colonnes.RESULTAT, partie.getResultat());
            long rowId = db.insert(HistoriqueSQL.TABLE_NAME, null, valeur);
            if (rowId == -1) System.out.println("Une erreur s'est produite lors de l'insertion");
            db.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
            db.close();
        }
    }


    @SuppressLint("Range")
    public ArrayList<Partie> retournerListeParties() {
        SQLiteDatabase db = this.getWritableDatabase();
        ArrayList<Partie> productList = new ArrayList<>();
        String query = "SELECT _id, _idcode, _courriel, _nbcouleur, _nbtentative, _resultat FROM " + HistoriqueSQL.TABLE_NAME;
        Cursor cursor = db.rawQuery(query, null);
        try {
            while (cursor.moveToNext()) {
                Partie partie = new Partie();


                partie.setId(Integer.parseInt(cursor.getString(cursor.getColumnIndex(HistoriqueSQL.Colonnes.ID))));
                partie.setIdCode(Integer.parseInt(cursor.getString(cursor.getColumnIndex(HistoriqueSQL.Colonnes.IDCODE))));
                partie.setCourriel(cursor.getString(cursor.getColumnIndex(HistoriqueSQL.Colonnes.COURRIEL)));
                partie.setNbCouleur(Integer.parseInt(cursor.getString(cursor.getColumnIndex(HistoriqueSQL.Colonnes.NBCOULEUR))));
                partie.setNbTentative(Integer.parseInt(cursor.getString(cursor.getColumnIndex(HistoriqueSQL.Colonnes.NBTENTATIVE))));
                partie.setResultat((cursor.getString(cursor.getColumnIndex(HistoriqueSQL.Colonnes.RESULTAT))));
                productList.add(partie);
            }
            db.close();
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            db.close();
        }
        return productList;
    }
}
