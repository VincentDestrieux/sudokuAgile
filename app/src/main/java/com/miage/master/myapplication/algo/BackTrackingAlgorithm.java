package com.miage.master.myapplication.algo;

import com.miage.master.myapplication.model.Grid;
import com.miage.master.myapplication.model.PairCoord;

/**
 * Created by Vincent Destrieux on 11/04/2017.
 */

public class BackTrackingAlgorithm extends VirtualAlgorithm {

    private final int squareSize = 3;
    private final int gridSize = 9;
    // Tableau des valeurs initiales
    private boolean[][] fixed;
    // La grille a resoudre
    private int[][] solution;
    // Tableau de ligne, colonne, et cube (3*3)
    private boolean[][] lockrow, lockcol, locksqr;

    public BackTrackingAlgorithm() {
        super();
    }

    public BackTrackingAlgorithm(MovingPattern pattern) {
        super(pattern);
    }

    // initialise la structure
    private void initialize() {
        this.solution = new int[gridSize][gridSize];
        this.fixed = new boolean[gridSize][gridSize];
        this.lockrow = new boolean[gridSize][gridSize];
        this.lockcol = new boolean[gridSize][gridSize];
        this.locksqr = new boolean[gridSize][gridSize];
    }

    //charge la grille
    private void load(Grid grid) {
        PairCoord currentPos;
        int cpt = 0;
        while ((currentPos = move.Next()) != null) {
            int val = grid.getDigit(currentPos);
            int row = cpt / gridSize, col = cpt % gridSize;
            cpt++;
            if (val < 1) continue;
            solution[row][col] = val;
            fixed[row][col] = true;
            setlock(row, col, val, true);
        }
    }

    private void setlock(int row, int col, int val, boolean state) {
        int sqr = (col / this.squareSize) + this.squareSize * (row / this.squareSize);
        this.lockrow[row][val - 1] = state;
        this.lockcol[col][val - 1] = state;
        this.locksqr[sqr][val - 1] = state;
    }

    private boolean islocked(int row, int col, int val) {
        if (this.lockrow[row][val - 1]) return true;
        if (this.lockcol[col][val - 1]) return true;
        int sqr = (col / this.squareSize) + this.squareSize * (row / this.squareSize);
        if (this.locksqr[sqr][val - 1]) return true;
        return false;
    }

    private void fillTab() {
        boolean backtrack = false;
        int position = 0;
        while (position >= 0 && position < gridSize * gridSize) {

            int row = position / this.gridSize;
            int col = position % this.gridSize;

            if (fixed[row][col]) {
                if (backtrack) {
                    position--;
                } else {
                    position++;
                }
                continue;
            }

            if (solution[row][col] > 0) setlock(row, col, solution[row][col], false);

            int val = solution[row][col] + 1;
            while (val <= this.gridSize && islocked(row, col, val)) val++;

            if (val <= this.gridSize) {
                // S il y a valeur on l'ajoute a notre tableau de solution
                solution[row][col] = val;
                // puis on change la lock
                setlock(row, col, val, true);
                // on descend l'arbre
                backtrack = false;
                position++;
            } else {
                // pas de valeur dans le backtrack
                solution[row][col] = 0;
                // on remonte l'arbre
                backtrack = true;
                position--;
            }
        }
        if (position < 0) {
            System.out.println("no solution");
            return;
        }
    }

    // Méthode permettant de visualiser la grille pendant le traitement
    private void visualGrid() {
        StringBuffer sb = new StringBuffer();
        for (int r = 0; r < gridSize; r++) {
            for (int c = 0; c < gridSize; c++)
                sb.append((solution[r][c] == 0) ? "." : solution[r][c]).append(" ");
            sb.append("\n");
        }
        System.out.println(sb.toString());
    }

    @Override
    public Grid compute(Grid grid) {
        //initialise les tableaux.
        initialize();
        //Charge les tableaux aux valeurs de la grille
        load(grid);
        //On rempli notre tableau de solution
        fillTab();
        //On charge la Grille avec notre tableau de solution
        Grid resultat = new Grid();
        /*
        for (int i = 0; i < gridSize; i++) {
            for (int j = 0; j < gridSize; j++) {
                resultat.setMainGrid(i, j, solution[i][j]);
            }
        }*/
        PairCoord currentPos;
        move.toBegin();
        while ((currentPos = move.Next()) != null) {
            resultat.setDigit(currentPos, solution[currentPos.getY() - 1][currentPos.getX() - 1]);
        }

        return resultat;
    }
}
