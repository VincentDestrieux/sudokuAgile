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
/*
    //Testing main - /!\ Move to Log.d on Android device !
	public static void main (String[] args)
	{
		/*Grille basic
		Grid grid = new Grid();
		System.out.println(grid.toString());
		grid.setDigit(new PairCoord(1,1), 9);
		grid.setDigit(new PairCoord(2,2), 8);
		grid.setDigit(new PairCoord(3,3), 7);
		System.out.println(grid.toString());*/
    //System.out.println(grid.checkSG());

		/*int[][] easy = {{5,6,0,8,4,7,0,0,0},
						{3,0,9,0,0,0,6,0,0},
						{0,0,8,0,0,0,0,0,0},
						{0,1,0,0,8,0,0,4,0},
						{7,9,0,6,0,2,0,1,8},
						{0,5,0,0,3,0,0,9,0},
						{0,0,0,0,0,0,2,0,0},
						{0,0,6,0,0,0,8,0,7},
						{0,0,0,3,1,6,0,5,9}};*/
/*
		int[][] easy = {{0,3,4,0,0,0,0,9,0},
						{6,0,0,0,0,8,2,1,4},
						{0,0,1,0,2,7,0,0,0},
						{4,0,9,0,8,3,0,5,0},
						{5,6,0,0,0,0,0,8,7},
						{0,2,0,5,6,0,4,0,1},
						{0,0,0,2,1,0,8,0,0},
						{8,5,7,9,0,0,0,0,6},
						{0,9,0,0,0,0,3,4,0}};

		int[][] medium = {{4,0,9,8,5,6,7,0,0},
						  {0,0,7,4,0,2,6,8,0},
				    	  {5,0,8,0,0,7,0,0,4},
				          {7,0,5,0,6,0,0,0,0},
				          {6,0,4,2,0,3,5,0,0},
						  {3,8,0,0,7,0,0,0,6},
						  {0,5,6,9,4,1,0,0,0},
						  {9,0,0,0,2,8,0,5,0},
						  {2,0,0,0,0,0,0,0,8}};

		//Grid easyGrid = new Grid(easy);
		Grid easyGrid = new Grid(medium);
		System.out.println(easyGrid);


		/*LeftRightTopBottomPattern move = new LeftRightTopBottomPattern();
		for(int i=1;i<82;i++)
		{
			System.out.println(move.Next());
		}*/

    //System.out.println(easyGrid.toString());

    //BCREAlgorithm algo = new BCREAlgorithm();
//		BCRELoneAlgorithm algo = new BCRELoneAlgorithm();
//		easyGrid = algo.compute(easyGrid);
//		System.out.println(easyGrid.toString());
//	}

}
