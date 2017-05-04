package com.miage.master.myapplication.vue;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.miage.master.myapplication.model.GrilleDeJeu;
import com.miage.master.myapplication.service.GenerationGrille;

/**
 * Created by Vincent  Destrieux on 13/04/2017.
 * Classe représentant la zone de jeu du sudoku
 */
public class Jeu extends Activity {
    private static Jeu jeu;
    private int[][] sudoku;
    private GrilleDeJeu grid = null;

    private int position_X = -1, position_Y = -1;
    private GenerationGrille generationGrille = new GenerationGrille();

    private Jeu() {
    }

    /**
     * @return
     */
    public static Jeu getJeu() {
        if (jeu == null) {
            jeu = new Jeu();
        }
        return jeu;
    }

    /**
     * @param context
     * @param niveau
     */
    public void creerGrille(Context context, String niveau) {
        int[][] sudoku = null;
        switch (niveau) {
            case "1":
                sudoku = GenerationGrille.getInstance().genereGrille(1);
                break;
            case "2":
                sudoku = GenerationGrille.getInstance().genereGrille(2);
                break;
            case "3":
                sudoku = GenerationGrille.getInstance().genereGrille(3);
                break;
            case "4":
                sudoku = GenerationGrille.getInstance().genereGrille(4);
                break;
            case "5":
                sudoku = GenerationGrille.getInstance().genereGrille(5);
                break;
        }
        /*
        int[][] sudoku = {{2, 5, 9, 7, 8, 6, 4, 3, 1},
                {7, 8, 3, 4, 1, 9, 6, 5, 2},
                {6, 1, 4, 5, 2, 3, 8, 7, 9},
                {1, 6, 2, 3, 5, 0, 9, 8, 7},
                {4, 9, 5, 1, 7, 8, 2, 6, 3},
                {3, 7, 8, 9, 6, 2, 1, 4, 5},
                {5, 2, 7, 8, 4, 1, 3, 9, 6},
                {8, 3, 6, 2, 9, 7, 5, 1, 4},
                {9, 4, 1, 6, 3, 5, 7, 0, 0}};*/

        grid = new GrilleDeJeu(context);
        grid.setGrilleDeJeu(sudoku);

     /*   generationGrille.diggingHole(52,sudoku);

        if (generationGrille.getNbSol() > 1)
            System.out.println("Erreur pruning technique, plus d'une solution !!!!");
*/
    }

    public GrilleDeJeu getGrid() {
        return grid;
    }

    /**
     * @param pos_x
     * @param pos_y
     */
    public void setPositionSelection(int pos_x, int pos_y) {
        position_X = pos_x;
        position_Y = pos_y;
    }

    /**
     * @param chiffre
     */
    public void setChiffre(int chiffre) {
        boolean correct = true;
        GenerationGrille g = new GenerationGrille();
        sudoku = new int[9][9];

        if (chiffre != 0) {
            for (int x = 0; x < 9; x++)
                for (int y = 0; y < 9; y++) {
                    sudoku[x][y] = grid.getItem(x, y).getValeur();
                }

            if (!g.checkInsertionLigne(sudoku, chiffre, position_Y) || !g.checkInsertionColonne(sudoku, chiffre, position_X) || !g.checkInsertionCarre(sudoku, position_X, position_Y, chiffre)) {
                Toast.makeText(GrilleDeJeu.getContext(), "Saisie Incorrecte", Toast.LENGTH_SHORT).show();
                correct = false;
            }

            if (correct == true) {
                grid.setItem(position_X, position_Y, chiffre);
            }

        } else if (chiffre == 0) {
            grid.setItem(position_X, position_Y, chiffre);
        }
        //Actualisation de la grille
        for (int x = 0; x < 9; x++)
            for (int y = 0; y < 9; y++) {
                sudoku[x][y] = grid.getItem(x, y).getValeur();
            }
    }

    public boolean checkGame() {
        sudoku = new int[9][9];
        for (int x = 0; x < 9; x++)
            for (int y = 0; y < 9; y++) {
                sudoku[x][y] = grid.getItem(x, y).getValeur();

            }
        int i, j;
        for (i = 0; i < 9; i++) {
            for (j = 0; j < 9; j++) {
                if (sudoku[i][j] == 0)
                    return false;
            }
        }
        return true;
    }

}

