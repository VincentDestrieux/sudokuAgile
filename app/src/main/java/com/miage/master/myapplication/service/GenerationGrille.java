package com.miage.master.myapplication.service;import java.util.ArrayList;import java.util.Random;/** * Inspiré du code de  marcellelek : * https://github.com/marcellelek/Sudoku/blob/master/src/com/marcellelek/sudoku/view/sudokugrid/SudokuGridView.java * Cette classe permet de générer la grille de jeu et vérifier sa cohérence */public class GenerationGrille {    private static GenerationGrille instance;    private ArrayList<ArrayList<Integer>> trou_disponible = new ArrayList<ArrayList<Integer>>();    private Random rand = new Random();    private int nbSol = 0;    public GenerationGrille() {    }    public int getNbSol() {        return this.nbSol;    }/*Permet de la création d'une instance de la grille */    public static GenerationGrille getInstance() {        if (instance == null) {            instance = new GenerationGrille();        }        return instance;    }    //Permet de générer la grille en utilisant des niveaux de difficultés    public int[][] genereGrille(int niveau) {        int[][] sudoku = new int[9][9];        int positionCourante = 0;        /*Permet de renseigner toutes les cases de la grille        à la valeur -1 pour eviter un NULLPOINTER*/        while (positionCourante < 81) {            if (positionCourante == 0) {                viderGrille(sudoku);            }            if (trou_disponible.get(positionCourante).size() != 0) {                int i = rand.nextInt(trou_disponible.get(positionCourante).size());                int nombre = trou_disponible.get(positionCourante).get(i);                if (!verifConflit(sudoku, positionCourante, nombre)) {                    int xPos = positionCourante % 9;                    int yPos = positionCourante / 9;                    sudoku[xPos][yPos] = nombre;                    trou_disponible.get(positionCourante).remove(i);                    positionCourante++;                } else {                    trou_disponible.get(positionCourante).remove(i);                }            } else {                for (int i = 1; i <= 9; i++) {                    trou_disponible.get(positionCourante).add(i);                }                positionCourante--;            }        }        int nbHole = 0;        Random r = new Random();        //Permet de générer un nombre de trou en fonction du niveau de difficultés de la grille        switch (niveau) {            case 1:                nbHole = r.nextInt(31 - 21) + 21;                break;            case 2:                nbHole = r.nextInt(45 - 32) + 32;                break;            case 3:                nbHole = r.nextInt(49 - 46) + 46;                break;            case 4:                nbHole = r.nextInt(53 - 50) + 50;                break;            case 5:                nbHole = r.nextInt(59 - 54) + 54;                break;        }        int score;        /* Utilisation de l'algorithme Digging Hole        pour calculer le score et ainsi définir la difficulté        cf PDF (Sudoku Puzzles Generating:from Easy to Evil)*/        diggingHole(nbHole, sudoku);        switch (countHole(sudoku)) {            case 5:                score = 1;                break;            case 4:                score = 2;                break;            case 3:                score = 3;                break;            case 2:                score = 4;                break;            case 0:                score = 5;                break;        }        return sudoku;    }    /**     * Permet de creuser des trous dans la grille     *     * @param nbHole le nombre de trou à "creuser"     * @param Sudoku laa grille de sudoku     */    public void diggingHole(int nbHole, int[][] Sudoku) {        double nbCase = 81;        double nbTrou = (double) nbHole;        for (int i = 0; i < 9; i++)            for (int j = 0; j < 9; j++) {                double probaTrou = nbTrou / nbCase;                if (Math.random() <= probaTrou) {                    int valeurTemp = Sudoku[i][j];                    Sudoku[i][j] = 0;                    int[][] SudokuTemp = Sudoku;                   if (pruningTechnique(Sudoku, i, j) == false)                        Sudoku[i][j] = valeurTemp;                    else                    nbTrou--;                }                nbCase--;            }    }    /* Permet de réinitiliser la grille sudoku */    public void viderGrille(int[][] sudoku) {        trou_disponible.clear();        for (int y = 0; y < 9; y++) {            for (int x = 0; x < 9; x++) {                sudoku[x][y] = -1;            }        }        for (int x = 0; x < 81; x++) {            trou_disponible.add(new ArrayList<Integer>());            for (int i = 1; i <= 9; i++) {                trou_disponible.get(x).add(i);            }        }    }    /* Permet de vérifier les conflits en ligne et en colonne pour générer une grille     sudoku coéherente*/    public boolean verifConflit(int[][] Sudoku, int currentPos, final int number) {        int xPos = currentPos % 9;        int yPos = currentPos / 9;        return conflitenLigne(Sudoku, xPos, yPos, number) || conflitenColonne(Sudoku, xPos, yPos, number) || conflitenCarre(Sudoku, xPos, yPos, number);    }    /**     * Return true s'il existe un conflit en ligne     *     * @param Sudoku     * @param xPos     * @param yPos     * @param number     * @return     */    public boolean conflitenLigne(final int[][] Sudoku, final int xPos, final int yPos, final int number) {        for (int x = xPos - 1; x >= 0; x--) {            if (number == Sudoku[x][yPos]) {                return true;            }        }        return false;    }    /**     * Return true s'il existe un conflit en colonne     *     * @param Sudoku     * @param xPos     * @param yPos     * @param number     * @return     */    public boolean conflitenColonne(final int[][] Sudoku, final int xPos, final int yPos, final int number) {        for (int y = yPos - 1; y >= 0; y--) {            if (number == Sudoku[xPos][y]) {                return true;            }        }        return false;    }    /**     * Return true s'il existe un conflit en carré     *     * @param Sudoku     * @param xPos     * @param yPos     * @param number     * @return     */    public boolean conflitenCarre(final int[][] Sudoku, final int xPos, final int yPos, final int number) {        int xRegion = xPos / 3;        int yRegion = yPos / 3;        for (int x = xRegion * 3; x < xRegion * 3 + 3; x++) {            for (int y = yRegion * 3; y < yRegion * 3 + 3; y++) {                if ((x != xPos || y != yPos) && number == Sudoku[x][y]) {                    return true;                }            }        }        return false;    }    /**     * @param sudoku     * @return le nombre minimal de trou sur toutes les lignes/colonnes     */    public int countHole(int[][] sudoku) {        int i = 0;        int j = 0;        int nbColonne = 0;        int nbLigne = 0;        int minCol = 9;        int minLig = 9;        for (i = 0; i < 9; i++) {            for (j = 0; j < 9; j++) {                if (sudoku[i][j] == 0) {                    nbColonne++;                }                if (sudoku[j][i] == 0) {                    nbLigne++;                }            }            if (nbColonne < minCol) {                minCol = nbColonne;            }            if (nbLigne < minLig) {                minLig = nbLigne;            }            nbColonne = 0;            nbLigne = 0;        }        if (minCol < minLig) {            return minCol;        } else return minLig;    }    public boolean findNextHole(int x, int y, int[][] sudoku) {        int i, j;        for (i = 0; i < 9; i++) {            for (j = 0; j < 9; j++) {                if (sudoku[i][j] == 0 && i > x && j > y) {                    return true;                }            }        }        return false;    }    public int findnextHoleX(int x, int y, int[][] sudoku) {        int i, j;        int xHole = 0;        for (i = 0; i < 9; i++) {            for (j = 0; j < 9; j++) {                if (sudoku[i][j] == 0 && i > x && j > y) {                    xHole = i;                }            }        }        return xHole;    }    public int findnextHoleY(int x, int y, int[][] sudoku) {        int i, j;        int yHole = 0;        for (i = 0; i < 9; i++) {            for (j = 0; j < 9; j++) {                if (sudoku[i][j] == 0 && i > x && j > y) {                    yHole = j;                }            }        }        return yHole;    }    public boolean exist(int x, int y, int[][] sudoku) {        int i = 1;        if (false) {            for (i = 1; i < 10; i++) {                sudoku[x][y] = i;            }        }        return true;    }    public boolean checkConflitLigne(int[][]sudoku)    {        int jalon = 0;       int[] tab={0, 0, 0, 0, 0, 0, 0, 0, 0, 0};        for (int j =0;j<9;j++){            for(int i=0;i<9;i++){                if (sudoku[i][j] != 0)                tab[sudoku[i][j]]++;                //System.out.println("tab" + sudoku[i][j] + " = " + tab[sudoku[i][j]] + " et j : " + j);                if (tab[sudoku[i][j]] > jalon+1 && sudoku[i][j]!=0){                 //   System.out.println("problème ligne");                    return false;                }                jalon++;            }        }        return true;    }    public boolean checkInsertionLigne(int[][] sudoku, int val, int ligne) {        for (int i = 0; i < 9; i++) {           // System.out.println("ligne Vaut : "+ligne  + "val=" +val);            if (sudoku[i][ligne] == val) {                return false;            }        }        return true;    }    public boolean checkInsertionColonne(int[][] sudoku, int val, int yval) {        for (int j = 0; j < 9; j++) {            if (sudoku[yval][j] == val)                return false;        }        return true;    }    public boolean checkInsertionCarre(final int[][] Sudoku, final int xPos, final int yPos, final int number) {        int xRegion = xPos / 3;        int yRegion = yPos / 3;        for (int x = xRegion * 3; x < xRegion * 3 + 3; x++) {            for (int y = yRegion * 3; y < yRegion * 3 + 3; y++) {                if ((x != xPos || y != yPos) && number == Sudoku[x][y]) {                    return true;                }            }        }        return false;    }        public boolean checkConflitColonne(int[][]sudoku)        {            int[] tab={0, 0, 0, 0, 0, 0, 0, 0, 0, 0};            for (int i =0;i<9;i++){                for(int j=0;j<9;j++){                    tab[sudoku[i][j]]++;                    if (tab[sudoku[i][j]] != i+1){                        //System.out.println("problème colonne");                        return false;                    }                }            }            return true;        }        public boolean checkConflitCarre(int[][] sudoku){            int[] tab={0, 0, 0, 0, 0, 0, 0, 0, 0, 0};            int iterationY = 1;            int iterationX = 1;            int decalageX = 0;            int decalageY = 0;            int nbCarreCheck = 1;            for(iterationY = 1;iterationY < 4;iterationY++){                for(iterationX = 1;iterationX < 4;iterationX++){                    for(int i = 0+decalageX;i <3+decalageX; i++) {                        for (int j = 0 + decalageY; j < 3 + decalageY; j++) {                            if(sudoku[i][j]!= 0)                            tab[sudoku[i][j]]++;                            if (tab[sudoku[i][j]] != nbCarreCheck && sudoku[i][j]!= 0) {                                //System.out.println("problème carre");                                return false;                            }                        }                    }                    decalageX+=3;                    nbCarreCheck++;                }                iterationX = 1;                decalageX = 0;                decalageY +=3;                nbCarreCheck++;            }            return true;        }       public boolean checkConflitGrille(int[][] sudoku ){           boolean ligne = checkConflitLigne(sudoku);           boolean colonne = checkConflitColonne(sudoku);           boolean carre = checkConflitCarre(sudoku);           if ( colonne && ligne && carre)               return true;           else               return false;        }        public boolean pruningTechnique(int[][] sudoku, int x, int y){            if (findNextHole(x, y, sudoku) == false ) {                for(int i=1;i<10;i++){                    sudoku[x][y] = i;                    if ( checkConflitGrille(sudoku) == true){                        nbSol++;                        if (nbSol > 2)                        return false;                        else                            return true;                    }                }                sudoku[x][y] = 0;            }            else {                if (pruningTechnique(sudoku, findnextHoleX(x,y,sudoku), findnextHoleY(x,y,sudoku)) == true){                    sudoku[x][y]++;                    if (sudoku[x][y] > 9){                        sudoku[x][y] = 0;                        return true;                    }                    pruningTechnique(sudoku, x,y);                }                else                    return false;            }            return true;        }    public void resetNbSol() {        nbSol = 0;    }}