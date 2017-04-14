package com.miage.master.myapplication;

import com.miage.master.myapplication.algo.BackTrackingAlgorithm;
import com.miage.master.myapplication.model.Grid;

import org.junit.Test;

public class BacktrackingTest {

    @Test
    public void backtrackingTest() throws Exception {
        easyTestGrid();
        mediumTestGrid();
        hardTestGrid();
        demoniacTestGrid();
    }

    public void easyTestGrid() {
        long t0 = System.currentTimeMillis();
        Grid easyGrid = TestModel.easyGrille();
        BackTrackingAlgorithm bta = new BackTrackingAlgorithm();
        try {
            Grid res = bta.compute(easyGrid);
            System.out.println("------FINISH EASY GRID-------");
            System.out.println(res.toString());
        } catch (Exception e) {
            System.out.println("ERROR FAIL EASY BACKTRACKING" + e);
        }

        long t1 = System.currentTimeMillis();
        TestModel.messageDeFin(t0, t1);
    }

    public void mediumTestGrid() {
        long t0 = System.currentTimeMillis();
        Grid mediumGrid = TestModel.mediumGrille();
        BackTrackingAlgorithm bta = new BackTrackingAlgorithm();
        try {
            Grid res = bta.compute(mediumGrid);
            System.out.println("------FINISH MEDIUM GRID-------");
            System.out.println(res.toString());
        } catch (Exception e) {
            System.out.println("ERROR FAIL MEDIUM BACKTRACKING" + e);
        }

        long t1 = System.currentTimeMillis();
        TestModel.messageDeFin(t0, t1);
    }

    public void hardTestGrid() {
        long t0 = System.currentTimeMillis();
        Grid hardGrid = TestModel.hardGrille();
        BackTrackingAlgorithm bta = new BackTrackingAlgorithm();
        try {
            Grid res = bta.compute(hardGrid);
            System.out.println("------FINISH HARD GRID-------");
            System.out.println(res.toString());
        } catch (Exception e) {
            System.out.println("ERROR FAIL HARD BACKTRACKING" + e);
        }

        long t1 = System.currentTimeMillis();
        TestModel.messageDeFin(t0, t1);
    }

    public void demoniacTestGrid() {
        long t0 = System.currentTimeMillis();
        Grid demoniacGrid = TestModel.demoniacGrille();
        BackTrackingAlgorithm bta = new BackTrackingAlgorithm();
        try {
            Grid res = bta.compute(demoniacGrid);
            System.out.println("------FINISH DEMONIAC GRID-------");
            System.out.println(res.toString());
        } catch (Exception e) {
            System.out.println("ERROR FAIL DEMONIAC BACKTRACKING" + e);
        }

        long t1 = System.currentTimeMillis();
        TestModel.messageDeFin(t0, t1);
    }
}