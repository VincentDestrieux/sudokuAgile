package com.miage.master.myapplication.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.AttributeSet;
import android.view.View;

import com.miage.master.myapplication.vue.Jeu;
import com.miage.master.myapplication.vue.MainActivity;
import com.miage.master.myapplication.vue.Mode;
import com.miage.master.myapplication.vue.Niveau;

/**
 * Created by Vincent  Destrieux on 13/04/2017.
 */
class LabelButton extends android.support.v7.widget.AppCompatButton implements View.OnClickListener {

    private int label;

    /**
     * @param context
     * @param attrs
     */
    public LabelButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setOnClickListener(this);
    }

    /**
     * @param v
     */
    @Override
    public void onClick(View v) {
        Jeu.getJeu().setChiffre(label);
        if (Jeu.getJeu().checkGame() == true) {

            AlertDialog.Builder builder = new AlertDialog.Builder(this.getContext());
            // Set the dialog title
            builder.setTitle("Bravo ! Vous avez gagné !")
                    // Set the action buttons
                    .setPositiveButton("Rejouer", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(getContext(), Mode.class);
                            getContext().startActivities(new Intent[]{intent});
                        }
                    })
                    .setNegativeButton("Quitter", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int id) {
                            Intent intent = new Intent(getContext(), MainActivity.class);
                            getContext().startActivities(new Intent[]{intent});
                        }
                    });

            AlertDialog alertDialog = builder.create();
            alertDialog.show();
        }

    }

    /**
     * @param label
     */
    public void setLabel(int label) {
        this.label = label;
    }

}
