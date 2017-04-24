package com.miage.master.myapplication.vue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.miage.master.myapplication.R;

/**
 * Created by Vincent  Destrieux on 24/04/2017.
 * Cette classe permet de choisir le mode de jeu
 */

public class Mode extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode);
    }

    public void startMode1(View v) {
        Intent intent = new Intent(Mode.this, Niveau.class);
        String classique = "classique";
        intent.putExtra("mode", classique);
        startActivity(intent);
    }

    public void startMode2(View v) {
        Intent intent = new Intent(Mode.this, Niveau.class);
        String graphique = "graphique";
        intent.putExtra("mode", graphique);
        startActivity(intent);
    }

    public void retour(View v) {
        finish();
    }
}
