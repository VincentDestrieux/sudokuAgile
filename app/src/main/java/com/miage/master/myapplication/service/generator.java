package com.miage.master.myapplication.service;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Halim on 31/03/2017.
 */

public class generator {
    private static generator instance;

    private ArrayList<ArrayList<Integer>> Available = new ArrayList<ArrayList<Integer>>();

    private Random rand = new Random();

    private generator() {
    }

    public static generator getInstance() {
        if (instance == null) {
            instance = new generator();
        }
        return instance;
    }

    public int[][] generateGrid(int niveau) {
        int[][] Sudoku = new int[9][9];

        int currentPos = 0;


        while (currentPos < 81) {
            if (currentPos == 0) {
                clearGrid(Sudoku);
            }

            if (Available.get(currentPos).size() != 0) {
                int i = rand.nextInt(Available.get(currentPos).size());
                int number = Available.get(currentPos).get(i);

                if (!checkConflict(Sudoku, currentPos, number)) {
                    int xPos = currentPos % 9;
                    int yPos = currentPos / 9;

                    Sudoku[xPos][yPos] = number;

                    Available.get(currentPos).remove(i);

                    currentPos++;
                } else {
                    Available.get(currentPos).remove(i);
                }

            } else {
                for (int i = 1; i <= 9; i++) {
                    Available.get(currentPos).add(i);
                }
                currentPos--;
            }
        }
        int nbHole=0;
        Random r = new Random();

        switch(niveau){
            case 1:
                nbHole = r.nextInt(31 - 21) + 21;
                break;
            case 2:
                nbHole = r.nextInt(45 - 32) + 32;
                break;
            case 3:
            nbHole = r.nextInt(49 - 46) + 46;
            break;
            case 4:
                nbHole = r.nextInt(53 - 50) + 50;
                break;
            case 5:
                nbHole = r.nextInt(59 - 54) + 54;
                break;

        }
            diggingHole(nbHole, Sudoku);
        return Sudoku;
    }

    public void diggingHole(int nbHole,int[][] Sudoku)
    {
        double nbCase = 81;
        double nbTrou = (double)nbHole;

        for(int i=0;i<9;i++)
            for(int j=0;j<9;j++)
            {
                double probaTrou = nbTrou/nbCase;
                if(Math.random() <= probaTrou)
                {
                    Sudoku[i][j] = 0;
                    nbTrou--;
                }
                nbCase--;
            }
    }
    private void clearGrid(int[][] Sudoku) {
        Available.clear();

        for (int y = 0; y < 9; y++) {
            for (int x = 0; x < 9; x++) {
                Sudoku[x][y] = -1;
            }
        }

        for (int x = 0; x < 81; x++) {
            Available.add(new ArrayList<Integer>());
            for (int i = 1; i <= 9; i++) {
                Available.get(x).add(i);
            }
        }
    }

    private boolean checkConflict(int[][] Sudoku, int currentPos, final int number) {
        int xPos = currentPos % 9;
        int yPos = currentPos / 9;

        if (checkHorizontalConflict(Sudoku, xPos, yPos, number) || checkVerticalConflict(Sudoku, xPos, yPos, number) || checkRegionConflict(Sudoku, xPos, yPos, number)) {
            return true;
        }

        return false;
    }

    /**
     * Return true if there is a conflict
     *
     * @param Sudoku
     * @param xPos
     * @param yPos
     * @param number
     * @return
     */
    private boolean checkHorizontalConflict(final int[][] Sudoku, final int xPos, final int yPos, final int number) {
        for (int x = xPos - 1; x >= 0; x--) {
            if (number == Sudoku[x][yPos]) {
                return true;
            }
        }

        return false;
    }

    private boolean checkVerticalConflict(final int[][] Sudoku, final int xPos, final int yPos, final int number) {
        for (int y = yPos - 1; y >= 0; y--) {
            if (number == Sudoku[xPos][y]) {
                return true;
            }
        }

        return false;
    }

    private boolean checkRegionConflict(final int[][] Sudoku, final int xPos, final int yPos, final int number) {
        int xRegion = xPos / 3;
        int yRegion = yPos / 3;

        for (int x = xRegion * 3; x < xRegion * 3 + 3; x++) {
            for (int y = yRegion * 3; y < yRegion * 3 + 3; y++) {
                if ((x != xPos || y != yPos) && number == Sudoku[x][y]) {
                    return true;
                }
            }
        }

        return false;
    }
}