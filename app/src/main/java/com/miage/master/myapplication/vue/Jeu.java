package com.miage.master.myapplication.vue;

import android.content.Context;

import com.miage.master.myapplication.model.GrilleDeJeu;
import com.miage.master.myapplication.service.Generator;

/**
 * Created by Vincent  Destrieux on 13/04/2017.
 * Classe représentant la zone de jeu du sudoku
 */
public class Jeu {
    private static Jeu jeu;

    private GrilleDeJeu grid = null;

    private int position_X = -1, position_Y = -1;

    private Jeu() {
    }

    /**
     *
     * @return
     */
    public static Jeu getJeu() {
        if (jeu == null) {
            jeu = new Jeu();
        }
        return jeu;
    }

    /**
     *
     * @param context
     * @param niveau
     */
    public void creerGrille(Context context, String niveau) {
        int[][] sudoku = null;
        switch (niveau) {
            case "1":
                sudoku = Generator.getInstance().generateGrid(1);
                break;
            case "2":
                sudoku = Generator.getInstance().generateGrid(2);
                break;
            case "3":
                sudoku = Generator.getInstance().generateGrid(3);
                break;
            case "4":
                sudoku = Generator.getInstance().generateGrid(4);
                break;
            case "5":
                sudoku = Generator.getInstance().generateGrid(5);
                break;
        }
        grid = new GrilleDeJeu(context);
        grid.setGrilleDeJeu(sudoku);
    }

    public GrilleDeJeu getGrid() {
        return grid;
    }

    /**
     *
     * @param pos_x
     * @param pos_y
     */
    public void setSelectedPos(int pos_x, int pos_y) {
        position_X = pos_x;
        position_Y = pos_y;
    }

    /**
     *
     * @param chiffre
     */
    public void setChiffre(int chiffre) {
        if (position_X != -1 && position_Y != -1) {
            grid.setItem(position_X, position_Y, chiffre);
        }
        //Ici vérifier que le jeu n'est pas terminé et donc gagné fonction a implementer
        //grid.checkGame();
    }
}