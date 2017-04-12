package com.miage.master.myapplication;

import com.miage.master.myapplication.algo.BackTrackingAlgorithm;
import com.miage.master.myapplication.model.Grid;

import org.junit.Test;

public class BacktrackingTest {

    @Test
    public void backtrackingTest() throws Exception {
        long t0=System.currentTimeMillis();
        System.out.println("------START-------");
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
        BackTrackingAlgorithm bta = new BackTrackingAlgorithm();
        try {
            Grid res = bta.compute(easyGrid);
            System.out.println("------FINISH-------");
            System.out.println(res.toString());
        }catch (Exception e){
            System.out.println("ERROR FAIL BACKTRACKING" + e);
        }

        long t1=System.currentTimeMillis();
        System.out.println("#################################");
        System.out.println("TEMPS D'EXECUTION: "+(t1-t0)+"ms");
        System.out.println("#################################");
    }

}