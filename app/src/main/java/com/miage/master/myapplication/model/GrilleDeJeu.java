package com.miage.master.myapplication.model;

import android.content.Context;

import com.miage.master.myapplication.vue.Case;

/**
 * Created by Vincent  Destrieux on 13/04/2017.
 */
public class GrilleDeJeu {

    //On créé un tableau de Case représentant notre grille de sudoku
    private Case[][] sudoku = new Case[9][9];
    private static Context context;

    /**
     * @param context
     */
    public GrilleDeJeu(Context context) {
        this.context = context;
        int x_pos, y_pos;
        //Chaque case dispose d'un id unique pour définir leurs positions (commence a 1)
        int id = 1;
        //Boucle permettant de dessiner la grille du sudoku avec toutes ses cases
        for (x_pos = 0; x_pos < 9; x_pos++) {
            for (y_pos = 0; y_pos < 9; y_pos++) {
                sudoku[x_pos][y_pos] = new Case(context, id);
                id++;
            }
        }
    }

    /**
     * @param grille
     */
    public void setGrilleDeJeu(int[][] grille) {
        int x_pos, y_pos;
        for (x_pos = 0; x_pos < 9; x_pos++) {
            for (y_pos = 0; y_pos < 9; y_pos++) {
                // Attention on doit inverser la grille pour avoir notre sudoku dans l'ordre
                sudoku[x_pos][y_pos].setValeurInitiale(grille[y_pos][x_pos]);
                // Si la valeur est instanciée dans la grille au départ alors
                // elle n'est pas modifiable par le joueur.
                // Attention ici aussi il faut l'inverser sinon les cases modifiables
                // seront décalées par rapport aux cases non modifiables.
                if (grille[y_pos][x_pos] != 0) {
                    sudoku[x_pos][y_pos].setPasModifiable();
                }
            }
        }
    }

    /**
     * @return
     */
    public Case[][] getGrilleDeJeu() {
        return sudoku;
    }

    /**
     * @param position_X
     * @param position_Y
     * @param chiffre
     */
    public void setItem(int position_X, int position_Y, int chiffre) {
        sudoku[position_X][position_Y].setValeur(chiffre);
    }

    /**
     * @param x
     * @param y
     * @return
     */
    public Case getItem(int x, int y) {
        return sudoku[x][y];
    }

    /**
     * @param cursor
     * @return
     */
    public Case getItem(int cursor) {
        int x_pos = cursor % 9;
        int y_pos = cursor / 9;

        return sudoku[x_pos][y_pos];
    }

    public Case[][] getSudoku() {
        return sudoku;
    }

    public static Context getContext() {
        return context;
    }


    public void setSudoku(Case[][] sudoku) {
        this.sudoku = sudoku;
    }

}
