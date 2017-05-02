package com.miage.master.myapplication.model;

import com.miage.master.myapplication.algo.*;

import java.util.Arrays;

/**
 * Classe de manipulation de la grille de jeu, contenant la grille de jeu, et une matrice 9*9
 * contenant les chiffres pouvant potentiellement être valide à cette position.
 *
 * @author remidelmas
 */


public class Grid {

    //Filled with -1 when empty
    private int[][] mainGrid;

    // A little bit dirty, we could build our own class to manipulate this item.
    // Or just a class for this who can take a Grid item for parameter
    private PossibilitiesGrid possibleGrid;
    private boolean isComplete;
    private boolean changeWithAlg;


	/* CONSTRUCTORS */

    public Grid() {
        mainGrid = new int[9][9];

        //Initialize main grid
        for (int i = 0; i < 9; i++) {
            Arrays.fill(mainGrid[i], 0);
        }
        //Initialize possibilities grid
        possibleGrid = new PossibilitiesGrid();
        //System.out.println(possibleGrid.toString());

    }

    public Grid(int[][] _matrix) {
        //Init
        int tmpVal;

        mainGrid = new int[9][9];
        possibleGrid = new PossibilitiesGrid();
        //Proceed
        if (_matrix.length != 9)
            throw new IllegalArgumentException("Matrix must be 9x9");
        for (int i = 0; i < 9; i++) {
            if (_matrix[i].length != 9)
                throw new IllegalArgumentException("Matrix must be 9x9");
            for (int j = 0; j < 9; j++) {
                tmpVal = _matrix[i][j];
                if (tmpVal < -1 || tmpVal > 9)
                    throw new IllegalArgumentException("Invalid value pos : " + i + "," + j);
                mainGrid[i][j] = tmpVal;
                //System.out.println(possibleGrid);
                if (tmpVal > 0) {
                    possibleGrid.update(new PairCoord(j + 1, i + 1), tmpVal);
                    //System.out.println("("+ (i+1) +","+ (j+1) + ") \n ****************************************************************************\n");
                    //System.out.println(possibleGrid.toString());
                }
            }
        }
    }

	/* GET & SET  */

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public boolean setDigit(PairCoord pair, int value) {
        mainGrid[pair.getY() - 1][pair.getX() - 1] = value;
        return possibleGrid.update(pair, value);
    }

    public void setMainGrid(int i, int j, int value) {
        mainGrid[i][j] = value;
    }

    public boolean isChangeWithAlg() {
        return changeWithAlg;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setChangeWithAlg(boolean changeWithAlg) {
        this.changeWithAlg = changeWithAlg;
    }

    //Return -1 if empty
    public int getDigit(PairCoord pair) {
        return mainGrid[pair.getY() - 1][pair.getX() - 1];
    }

    public Grid clone() {
        int[][] _copyGrid = new int[9][9];
        for (int i = 0; i < 9; i++) {
            _copyGrid[i] = Arrays.copyOf(mainGrid[i], 9);
        }
        return new Grid(_copyGrid);
    }

	/* Core function */

    public int[] getLine(PairCoord pair) {
        int[] _line = new int[9];
        for (int i = 0; i < 9; i++) {
            _line[i] = getDigit(new PairCoord(i, pair.getY()));
        }
        return _line;
    }

    public int[] getColumn(PairCoord pair) {
        int[] _column = new int[9];
        for (int i = 0; i < 9; i++) {
            _column[i] = getDigit(new PairCoord(pair.getX(), i));
        }
        return _column;
    }

    /* return number between 1 & 9 corresponding to an submatrix index
     *
     * 0/1/2
     * 3/4/5
     * 6/7/8
     *
     */
    public static int getSubGrid(PairCoord pair) {
        int col = (pair.getX() - 1) / 3;
        int row = (pair.getY() - 1) / 3;
        return col + row * 3;
    }


    @Override
    public String toString() {
        StringBuilder _string = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                _string.append("|");
                _string.append(getDigit(new PairCoord(j + 1, i + 1)));
                _string.append("|");
            }
            _string.append("\n");
        }
        _string.append("\n");
        //_string.append(possibleGrid.toString());
        return _string.toString();
    }

    // -> Wrapping possibilitiesGrid -> return -1 if it's not a singleton

    public int getSingleton(PairCoord pair) {
        return possibleGrid.getSingleton(pair);
    }

    public String checkSG() {
        StringBuilder _string = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                _string.append("|");
                _string.append(getSubGrid(new PairCoord(i + 1, j + 1)));
                _string.append("|");
            }
            _string.append("\n");
        }
        return _string.toString();
    }

    public boolean checkComplete() {
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 9; j++)
                if (mainGrid[i][j] < 1) {
                    this.isComplete = false;
                    return false;
                }
        this.isComplete = true;
        return true;
    }

    public boolean LoneColumn(PairCoord pos) {
        possibleGrid.LoneColumn(pos);
        return this.checkComplete();
    }

    public boolean LoneRow(PairCoord pos) {
        possibleGrid.LoneRow(pos);
        return this.checkComplete();
    }

    public boolean LoneBlock(PairCoord pos) {
        possibleGrid.LoneBlock(pos);
        return this.checkComplete();
    }

    public PossibilitiesGrid getPossibleGrid() {
        return possibleGrid;
    }
}
