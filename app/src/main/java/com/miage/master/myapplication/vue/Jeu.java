package com.miage.master.myapplication.vue;

import android.content.Context;

import com.miage.master.myapplication.model.GrilleDeJeu;
import com.miage.master.myapplication.service.GenerationGrille;

/**
 * Created by Vincent  Destrieux on 13/04/2017.
 * Classe représentant la zone de jeu du sudoku
 */
public class Jeu {
    private static Jeu jeu;

    private GrilleDeJeu grid = null;

    private int position_X = -1, position_Y = -1;
    private GenerationGrille generationGrille = new GenerationGrille();

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

      /*int[][] sudoku = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 4, 7, 6, 9, 1, 8, 2, 0},
                {0, 3, 0, 0, 0, 0, 0, 4, 0},
                {0, 6, 0, 3, 0, 2, 0, 7, 0},
                {0, 8, 0, 0, 7, 0, 0, 6, 0},
                {0, 2, 0, 1, 0, 5, 0, 3, 0},
                {0, 9, 0, 0, 0, 0, 0, 8, 0},
                {0, 7, 6, 8, 1, 4, 9, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}};*/

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
     *
     * @param pos_x
     * @param pos_y
     */
    public void setPositionSelection(int pos_x, int pos_y) {
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