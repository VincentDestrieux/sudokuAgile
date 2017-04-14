package com.miage.master.myapplication.utils;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

import com.miage.master.myapplication.vue.Jeu;

/**
 * Created by Vincent  Destrieux on 13/04/2017.
 */

class LabelButton extends android.support.v7.widget.AppCompatButton implements View.OnClickListener {

    private int label;

    public LabelButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Jeu.getJeu().setChiffre(label);
    }

    public void setLabel(int label) {
        this.label = label;
    }
}
