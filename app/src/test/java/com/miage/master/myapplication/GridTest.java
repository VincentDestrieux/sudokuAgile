package com.miage.master.myapplication;

import com.miage.master.myapplication.model.Grid;
import com.miage.master.myapplication.model.PairCoord;

import org.junit.Test;

public class GridTest {

    @Test
    public void onCreate() throws Exception {

        Grid grid = new Grid();
        System.out.println(grid.toString());
        grid.setDigit(new PairCoord(1, 1), 9);
        grid.setDigit(new PairCoord(2, 2), 8);
        grid.setDigit(new PairCoord(3, 3), 7);
        System.out.println(grid.toString());
        System.out.println(grid.checkSG());

        int[][] easy = {{5, 6, 0, 8, 4, 7, 0, 0, 0},
                {3, 0, 9, 0, 0, 0, 6, 0, 0},
                {0, 0, 8, 0, 0, 0, 0, 0, 0},
                {0, 1, 0, 0, 8, 0, 0, 4, 0},
                {7, 9, 0, 6, 0, 2, 0, 1, 8},
                {0, 5, 0, 0, 3, 0, 0, 9, 0},
                {0, 0, 0, 0, 0, 0, 2, 0, 0},
                {0, 0, 6, 0, 0, 0, 8, 0, 7},
                {0, 0, 0, 3, 1, 6, 0, 5, 9}};

        int[][] medium = {{4, 0, 9, 8, 5, 6, 7, 0, 0},
                {0, 0, 7, 4, 0, 2, 6, 8, 0},
                {5, 0, 8, 0, 0, 7, 0, 0, 4},
                {7, 0, 5, 0, 6, 0, 0, 0, 0},
                {6, 0, 4, 2, 0, 3, 5, 0, 0},
                {3, 8, 0, 0, 7, 0, 0, 0, 6},
                {0, 5, 6, 9, 4, 1, 0, 0, 0},
                {9, 0, 0, 0, 2, 8, 0, 5, 0},
                {2, 0, 0, 0, 0, 0, 0, 0, 8}};

        Grid easyGrid = new Grid(easy);
        Grid mediumGrid = new Grid(medium);
        System.out.println(easyGrid);
        System.out.println(mediumGrid);
    }
}