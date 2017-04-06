package com.miage.master.myapplication.vue;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import com.miage.master.myapplication.model.ZoneDeDessin;

public class TestReco extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //Ancienne version mets en plein écran
        //setContentView(new ZoneDeDessin(this));

        creerVue();
    }

    private void creerVue() {
        RelativeLayout r = new RelativeLayout(this);
        r.setBackgroundColor(Color.parseColor("#81ffdf"));
        r.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        ZoneDeDessin z = new ZoneDeDessin(this);
        z.setBackgroundColor(Color.WHITE);
        z.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,900));

        r.addView(z);
        setContentView(r);
    }

}
