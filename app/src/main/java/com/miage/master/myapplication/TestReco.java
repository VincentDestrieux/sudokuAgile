package com.miage.master.myapplication;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.RelativeLayout;

public class TestReco extends Activity {
    private static final int ECRAN = 900;

    private RelativeLayout rl;
    private ZoneDeDessin z;
    private Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ancienne version mets en plein écran
        //setContentView(new ZoneDeDessin(this));
        setContentView(R.layout.activity_testreco);
        b = (Button) findViewById(R.id.button);
        addZoneDeDessin();

        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    //Cette méthode permet de creer la Zone de dessin et de l'inclure dans la Vue.
    private void addZoneDeDessin() {
        rl = (RelativeLayout) findViewById(R.id.relativeL);

        z = new ZoneDeDessin(this);
        z.setBackgroundColor(Color.WHITE);
        z.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ECRAN));

        rl.addView(z);
    }

}
