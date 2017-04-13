package com.miage.master.myapplication.utils;

import android.content.Context;
import android.view.View;

/**
 * Created by Vincent  Destrieux on 13/04/2017.
 * Cette classe est le modèle de chaque case de la grille de sudoku
 * Case.java hérite de cette classe
 */

public class CustomViewCase extends View {

    private int valeur;
    // Permet de définir si une case est modifiable ou non
    private boolean modifiable = true;

    public CustomViewCase(Context context) {
        super(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, widthMeasureSpec);
    }

    //Permet de mettre les cases a pas modifiable
    public void setPasModifiable() {
        modifiable = false;
    }

    public void setValeurInitiale(int val) {
        this.valeur = val;
    }

    public void setValeur(int valeur) {
        //Si ce n'est pas une case de base alors on peut la modifier
        if (modifiable) {
            this.valeur = valeur;
        }
        //Méthode de la classe View permettant de forcer un affichage si possible
        invalidate();
    }

    public int getValeur() {
        return valeur;
    }

}
