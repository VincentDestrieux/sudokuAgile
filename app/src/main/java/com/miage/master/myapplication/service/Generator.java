package com.miage.master.myapplication.service;import java.util.ArrayList;import java.util.Random;public class Generator {    private static Generator instance;    private ArrayList<ArrayList<Integer>> Available = new ArrayList<ArrayList<Integer>>();    private Random rand = new Random();    private int nbSol =0;    private Generator() {    }    public static Generator getInstance() {        if (instance == null) {            instance = new Generator();        }        return instance;    }    public int[][] generateGrid(int niveau) {        int[][] Sudoku = new int[9][9];        int currentPos = 0;        while (currentPos < 81) {            if (currentPos == 0) {                clearGrid(Sudoku);            }            if (Available.get(currentPos).size() != 0) {                int i = rand.nextInt(Available.get(currentPos).size());                int number = Available.get(currentPos).get(i);                if (!checkConflict(Sudoku, currentPos, number)) {                    int xPos = currentPos % 9;                    int yPos = currentPos / 9;                    Sudoku[xPos][yPos] = number;                    Available.get(currentPos).remove(i);                    currentPos++;                } else {                    Available.get(currentPos).remove(i);                }            } else {                for (int i = 1; i <= 9; i++) {                    Available.get(currentPos).add(i);                }                currentPos--;            }        }        int nbHole = 0;        Random r = new Random();        switch (niveau) {            case 1:                nbHole = r.nextInt(31 - 21) + 21;                break;            case 2:                nbHole = r.nextInt(45 - 32) + 32;                break;            case 3:                nbHole = r.nextInt(49 - 46) + 46;                break;            case 4:                nbHole = r.nextInt(53 - 50) + 50;                break;            case 5:                nbHole = r.nextInt(59 - 54) + 54;                break;        }        int score;        diggingHole(nbHole, Sudoku);        switch (countHole(Sudoku)) {            case 5:                score = 1;                break;            case 4:                score = 2;                break;            case 3:                score = 3;                break;            case 2:                score = 4;                break;            case 0:                score = 5;                break;        }        return Sudoku;    }    /**     * @param nbHole le nombre de trou à "creuser"     * @param Sudoku laa grille de sudoku     */    public void diggingHole(int nbHole, int[][] Sudoku) {        double nbCase = 81;        double nbTrou = (double) nbHole;        for (int i = 0; i < 9; i++)            for (int j = 0; j < 9; j++) {                double probaTrou = nbTrou / nbCase;                if (Math.random() <= probaTrou) {                    Sudoku[i][j] = 0;                    nbTrou--;                }                nbCase--;            }    }    private void clearGrid(int[][] Sudoku) {        Available.clear();        for (int y = 0; y < 9; y++) {            for (int x = 0; x < 9; x++) {                Sudoku[x][y] = -1;            }        }        for (int x = 0; x < 81; x++) {            Available.add(new ArrayList<Integer>());            for (int i = 1; i <= 9; i++) {                Available.get(x).add(i);            }        }    }    private boolean checkConflict(int[][] Sudoku, int currentPos, final int number) {        int xPos = currentPos % 9;        int yPos = currentPos / 9;        return checkHorizontalConflict(Sudoku, xPos, yPos, number) || checkVerticalConflict(Sudoku, xPos, yPos, number) || checkRegionConflict(Sudoku, xPos, yPos, number);    }    /**     * Return true if there is a conflict     *     * @param Sudoku     * @param xPos     * @param yPos     * @param number     * @return     */    private boolean checkHorizontalConflict(final int[][] Sudoku, final int xPos, final int yPos, final int number) {        for (int x = xPos - 1; x >= 0; x--) {            if (number == Sudoku[x][yPos]) {                return true;            }        }        return false;    }    private boolean checkVerticalConflict(final int[][] Sudoku, final int xPos, final int yPos, final int number) {        for (int y = yPos - 1; y >= 0; y--) {            if (number == Sudoku[xPos][y]) {                return true;            }        }        return false;    }    private boolean checkRegionConflict(final int[][] Sudoku, final int xPos, final int yPos, final int number) {        int xRegion = xPos / 3;        int yRegion = yPos / 3;        for (int x = xRegion * 3; x < xRegion * 3 + 3; x++) {            for (int y = yRegion * 3; y < yRegion * 3 + 3; y++) {                if ((x != xPos || y != yPos) && number == Sudoku[x][y]) {                    return true;                }            }        }        return false;    }    /**     * @param sudoku     * @return le nombre minimal de trou sur toutes les lignes/colonnes     */    public int countHole(int[][] sudoku) {        int i = 0;        int j = 0;        int nbColonne = 0;        int nbLigne = 0;        int minCol = 9;        int minLig = 9;        for (i = 0; i < 9; i++) {            for (j = 0; j < 9; j++) {                if (sudoku[i][j] == 0) {                    nbColonne++;                }                if (sudoku[j][i] == 0) {                    nbLigne++;                }            }            if (nbColonne < minCol) {                minCol = nbColonne;            }            if (nbLigne < minLig) {                minLig = nbLigne;            }            nbColonne = 0;            nbLigne = 0;        }        if (minCol < minLig) {            return minCol;        } else return minLig;    }    public boolean findNextHole(int x, int y, int[][] sudoku) {        int i,j;        for(i=0;i<9;i++)        {        for(j=0;j<9;j++) {            if (sudoku[i][j] == 0 && i > x && j > y)            {                return true;            }        }        }        return false;    }    public int findnextHoleX(int x, int y, int[][] sudoku ){        int i,j;        int xHole =0;        for(i=0;i<9;i++)        {            for(j=0;j<9;j++) {                if (sudoku[i][j] == 0 && i > x && j > y)                {                    xHole = i;                }            }        }        return xHole;    }    public int findnextHoleY(int x, int y, int[][] sudoku ){        int i,j;        int yHole =0;        for(i=0;i<9;i++)        {            for(j=0;j<9;j++) {                if (sudoku[i][j] == 0 && i > x && j > y)                {                    yHole = j;                }            }        }        return yHole;    }    public boolean exist(int x,int y,int[][]sudoku)    {        int i=1;        if (false)        {            for ( i = 1; i < 10; i++)            {                sudoku[x][y]=i;            }        }        return true;    }public boolean checkConflitLigne(int[][]sudoku){   int[] tab={0, 0, 0, 0, 0, 0, 0, 0, 0, 0};    for (int j =0;j<9;j++){        for(int i=0;i<9;i++){            tab[sudoku[i][j]]++;            if (tab[sudoku[i][j]] != j+1)                return false;        }    }    return true;}    public boolean checkConflitColonne(int[][]sudoku)    {        int[] tab={0, 0, 0, 0, 0, 0, 0, 0, 0, 0};        for (int i =0;i<9;i++){            for(int j=0;j<9;j++){                tab[sudoku[i][j]]++;                if (tab[sudoku[i][j]] != i+1)                    return false;            }        }        return true;    }    public boolean checkConflitCarre(int[][] sudoku){        int[] tab={0, 0, 0, 0, 0, 0, 0, 0, 0, 0};        int iterationY = 1;        int iterationX = 1;        int decalageX = 0;        int decalageY = 0;        int nbCarreCheck = 1;        for(iterationY = 1;iterationY < 4;iterationY++){            for(iterationX = 1;iterationX < 4;iterationX++){                for(int i = 0+decalageX;i <2+decalageX; i++){                    for(int j = 0+decalageY; j <2+decalageY;j++){                        tab[sudoku[i][j]]++;                        if (tab[sudoku[i][j]] != nbCarreCheck)                            return false;                    }                }                decalageX+=3;                nbCarreCheck++;            }            iterationX = 1;            decalageX = 0;            decalageY +=3;            nbCarreCheck++;        }        return true;    }    public boolean checkConflitGrille(int[][] sudoku ){        return (checkConflitCarre(sudoku) && checkConflitColonne(sudoku) && checkConflitLigne(sudoku));    }    public boolean pruningTechnique(int[][] sudoku, int x, int y){        if (findNextHole(x, y, sudoku) == false ) {            for(int i=1;i<10;i++){                sudoku[x][y] = i;                if ( checkConflitGrille(sudoku) == true){                    nbSol++;                    return true;                }            }        }        else {            if (pruningTechnique(sudoku, findnextHoleX(x,y,sudoku), findnextHoleY(x,y,sudoku)) == true){                sudoku[x][y]++;                if (sudoku[x][y] > 9){                    sudoku[x][y] = 1;                    return true;                }                pruningTechnique(sudoku, x,y);            }        }        return true;    }}