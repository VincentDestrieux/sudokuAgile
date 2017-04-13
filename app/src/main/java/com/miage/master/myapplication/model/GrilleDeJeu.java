package com.miage.master.myapplication.model;

import android.content.Context;
import android.view.View;

import com.miage.master.myapplication.vue.Case;

/**
 * Created by Vincent  Destrieux on 13/04/2017.
 */

public class GrilleDeJeu {

    //On créé un tableau de Case représentant notre grille de sudoku
    private Case[][] sudoku = new Case[9][9];
    private Context context;

    public GrilleDeJeu(Context context) {
        this.context = context;
        int x_pos, y_pos;
        //Boucle permettant de dessiner la grille du sudoku avec toutes ses cases
        for (x_pos = 0; x_pos < 9; x_pos++) {
            for (y_pos = 0; y_pos < 9; y_pos++) {
                sudoku[x_pos][y_pos] = new Case(context);
            }
        }
    }

    public void setGrilleDeJeu(int[][] grille) {
        int x_pos, y_pos;
        for (x_pos = 0; x_pos < 9; x_pos++) {
            for (y_pos = 0; y_pos < 9; y_pos++) {
                sudoku[x_pos][y_pos].setValeurInitiale(grille[y_pos][x_pos]);
                //Si la valeur est instanciée dans la grille au départ alors
                //elle n'est pas modifiable par le joueur.
                if (grille[x_pos][y_pos] != 0) {
                    sudoku[x_pos][y_pos].setPasModifiable();
                }
            }
        }
    }

    public Case[][] getGrilleDeJeu() {
        return sudoku;
    }

    public void setItem(int selectedPosX, int selectedPosY, int chiffre) {
        sudoku[selectedPosX][selectedPosY].setValeur(chiffre);
    }

    public Case getItem(int x, int y) {
        return sudoku[x][y];
    }

    public Case getItem(int cursor) {
        int x_pos = cursor % 9;
        int y_pos = cursor / 9;

        return sudoku[x_pos][y_pos];
    }


}
