package com.miage.master.myapplication.vue;

import android.app.Activity;
import android.os.Bundle;

import com.miage.master.myapplication.R;

/**
 * Created by Vincent  Destrieux on 13/04/2017.
 * Classe permettant de creer la grille de jeu en fonction du niveau
 */

public class Grille extends Activity {

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grille);

        Bundle bundle = getIntent().getExtras();
        String niveau = bundle.getString("niveau");

        Jeu.getJeu().creerGrille(this, niveau);
    }

}
