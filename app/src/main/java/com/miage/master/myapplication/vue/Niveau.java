package com.miage.master.myapplication.vue;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.miage.master.myapplication.R;

public class Niveau extends Activity {

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_niveau);
    }

    /**
     *
     * @param v
     */
    public void start(View v) {
        Intent intent = new Intent(Niveau.this, Grille.class);

        String niveau = v.getTag().toString();
        Bundle bundle = new Bundle();
        bundle.putString("niveau", niveau);

        intent.putExtras(bundle);
        startActivity(intent);
    }

    /**
     *
     * @param v
     */
    public void reconnaissance(View v) {
        Intent intent = new Intent(Niveau.this, Reconnaissance.class);
        startActivity(intent);
    }

    public void retour(View v) {
        finish();
    }

}
