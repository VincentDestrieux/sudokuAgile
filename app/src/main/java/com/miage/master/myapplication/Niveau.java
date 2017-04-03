package com.miage.master.myapplication;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Niveau extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveau);
    }
    public void start(View v){
        Intent intent = new Intent(Niveau.this, Grille.class);
        startActivity(intent);
    }

    public void retour(View v){
        finish();
    }

}
