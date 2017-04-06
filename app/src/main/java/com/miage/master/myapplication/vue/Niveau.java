package com.miage.master.myapplication.vue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.miage.master.myapplication.R;

public class Niveau extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveau);
    }

    public void start(View v) {
        Intent intent = new Intent(Niveau.this, Grille.class);
        startActivity(intent);
    }

    public void test(View v) {
        Intent intent = new Intent(Niveau.this, TestReco.class);
        startActivity(intent);
    }

    public void retour(View v) {
        finish();
    }

}
