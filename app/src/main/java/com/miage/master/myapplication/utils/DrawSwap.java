package com.miage.master.myapplication.utils;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.miage.master.myapplication.R;
import com.miage.master.myapplication.vue.DrawNumber;
import com.miage.master.myapplication.vue.Jeu;

public class DrawSwap extends Activity {
    int value;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_draw_swap);
        getDrawValue();
    }

    public void getDrawValue()
    {
        Intent i = new Intent(this, DrawNumber.class);
        startActivityForResult(i, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        // Check which request we're responding to
        if (requestCode == 1) {
            // Make sure the request was successful
            if (resultCode == RESULT_OK) {
                value = data.getIntExtra("result_int",0);
                if(value>0)
                    Jeu.getJeu().setChiffre(value);
            }
        }
        finish();
    }
}
