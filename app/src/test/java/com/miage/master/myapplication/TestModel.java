package com.miage.master.myapplication;

import com.miage.master.myapplication.model.Grid;

/**
 * Created by Vincent  Destrieux on 12/04/2017.
 * Cette classe est le model sur lequel se basent tous les tests des algorithmes.
 */

public class TestModel {

    public static Grid easyGrille() {
        System.out.println("------START EASY GRID-------");
        int[][] easy = {{0, 0, 9, 7, 0, 6, 0, 3, 1},
                {0, 0, 3, 4, 0, 9, 6, 0, 2},
                {0, 0, 0, 0, 0, 0, 0, 0, 9},
                {1, 6, 0, 0, 0, 4, 0, 0, 7},
                {0, 9, 5, 1, 0, 8, 2, 6, 0},
                {3, 0, 0, 9, 0, 0, 0, 4, 5},
                {5, 0, 0, 0, 0, 0, 0, 0, 0},
                {8, 0, 6, 2, 0, 7, 5, 0, 0},
                {9, 4, 0, 6, 0, 5, 7, 0, 0}};
        Grid easyGrid = new Grid(easy);
        System.out.println("------INITIATE-------");
        System.out.println(easyGrid.toString());
        System.out.println("------LOAD-------");
        return easyGrid;
    }

    public static Grid mediumGrille() {
        System.out.println("------START MEDIUM GRID-------");
        int[][] medium = {{4, 0, 9, 8, 5, 6, 7, 0, 0},
                {0, 0, 7, 4, 0, 2, 6, 8, 0},
                {5, 0, 8, 0, 0, 7, 0, 0, 4},
                {7, 0, 5, 0, 6, 0, 0, 0, 0},
                {6, 0, 4, 2, 0, 3, 5, 0, 0},
                {3, 8, 0, 0, 7, 0, 0, 0, 6},
                {0, 5, 6, 9, 4, 1, 0, 0, 0},
                {9, 0, 0, 0, 2, 8, 0, 5, 0},
                {2, 0, 0, 0, 0, 0, 0, 0, 8}};
        Grid mediumGrid = new Grid(medium);
        System.out.println("------INITIATE-------");
        System.out.println(mediumGrid.toString());
        System.out.println("------LOAD-------");
        return mediumGrid;
    }

    public static Grid hardGrille() {
        System.out.println("------START HARD GRID-------");
        int[][] hard = {{0, 0, 0, 0, 2, 0, 6, 5, 0},
                {0, 3, 1, 7, 0, 0, 4, 0, 0},
                {8, 0, 0, 0, 0, 0, 0, 1, 0},
                {0, 0, 0, 6, 0, 9, 5, 0, 0},
                {6, 0, 0, 0, 0, 0, 0, 0, 3},
                {0, 0, 2, 4, 0, 1, 0, 0, 0},
                {0, 4, 0, 0, 0, 0, 0, 0, 1},
                {0, 0, 5, 0, 0, 7, 8, 9, 0},
                {0, 9, 8, 0, 6, 0, 0, 0, 0}};
        Grid hardGrid = new Grid(hard);
        System.out.println("------INITIATE-------");
        System.out.println(hardGrid.toString());
        System.out.println("------LOAD-------");
        return hardGrid;
    }

    public static Grid demoniacGrille() {
        System.out.println("------START DEMONIAC GRID-------");
        int[][] demoniac = {{0, 0, 0, 5, 6, 7, 0, 0, 0},
                {0, 0, 1, 2, 0, 3, 4, 0, 0},
                {2, 0, 0, 0, 8, 0, 0, 0, 3},
                {0, 9, 0, 0, 7, 0, 0, 3, 0},
                {4, 0, 7, 0, 0, 0, 9, 0, 8},
                {0, 3, 0, 0, 2, 0, 0, 4, 0},
                {5, 0, 0, 0, 3, 0, 0, 0, 4},
                {0, 0, 3, 8, 0, 1, 2, 0, 0},
                {0, 0, 0, 7, 5, 6, 0, 0, 0}};
        Grid demoniacGrid = new Grid(demoniac);
        System.out.println("------INITIATE-------");
        System.out.println(demoniacGrid.toString());
        System.out.println("------LOAD-------");
        return demoniacGrid;
    }

    public static void messageDeFin(long t0, long t1) {
        System.out.println("#################################");
        System.out.println("TEMPS D'EXECUTION: " + (t1 - t0) + "ms");
        System.out.println("#################################");
    }
}

/* Grille a multiple solution
int[][] sudoku = {{0, 0, 0, 0, 0, 0, 0, 0, 0},
                {0, 4, 7, 6, 9, 1, 8, 2, 0},
                {0, 3, 0, 0, 0, 0, 0, 4, 0},
                {0, 6, 0, 3, 0, 2, 0, 7, 0},
                {0, 8, 0, 0, 7, 0, 0, 6, 0},
                {0, 2, 0, 1, 0, 5, 0, 3, 0},
                {0, 9, 0, 0, 0, 0, 0, 8, 0},
                {0, 7, 6, 8, 1, 4, 9, 5, 0},
                {0, 0, 0, 0, 0, 0, 0, 0, 0}};
                */
