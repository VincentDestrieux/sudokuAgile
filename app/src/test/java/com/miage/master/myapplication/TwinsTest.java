package com.miage.master.myapplication;

import com.miage.master.myapplication.algo.TwinsAlgorithm;
import com.miage.master.myapplication.algo.TwinsAlgorithm;
import com.miage.master.myapplication.model.Grid;

import org.junit.Test;

public class TwinsTest {

    @Test
    public void TwinsTest() throws Exception {
        easyTestGrid();
        //mediumTestGrid();
        //hardTestGrid();
        //demoniacTestGrid();
    }

    public void easyTestGrid() {
        long t0 = System.currentTimeMillis();
        Grid easyGrid = TestModel.easyGrille();
        TwinsAlgorithm twins = new TwinsAlgorithm();
        try {
            Grid res = twins.compute(easyGrid);
            System.out.println("------FINISH EASY GRID-------");
            System.out.println(res.toString());
        } catch (Exception e) {
            System.out.println("ERROR FAIL EASY TWINS" + e);
        }

        long t1 = System.currentTimeMillis();
        TestModel.messageDeFin(t0, t1);
    }

    public void mediumTestGrid() {
        long t0 = System.currentTimeMillis();
        Grid mediumGrid = TestModel.mediumGrille();
        TwinsAlgorithm twins = new TwinsAlgorithm();
        try {
            Grid res = twins.compute(mediumGrid);
            System.out.println("------FINISH MEDIUM GRID-------");
            System.out.println(res.toString());
        } catch (Exception e) {
            System.out.println("ERROR FAIL MEDIUM TWINS" + e);
        }

        long t1 = System.currentTimeMillis();
        TestModel.messageDeFin(t0, t1);
    }

    public void hardTestGrid() {
        long t0 = System.currentTimeMillis();
        Grid hardGrid = TestModel.hardGrille();
        TwinsAlgorithm twins = new TwinsAlgorithm();
        try {
            Grid res = twins.compute(hardGrid);
            System.out.println("------FINISH HARD GRID-------");
            System.out.println(res.toString());
        } catch (Exception e) {
            System.out.println("ERROR FAIL HARD TWINS" + e);
        }

        long t1 = System.currentTimeMillis();
        TestModel.messageDeFin(t0, t1);
    }

    public void demoniacTestGrid() {
        long t0 = System.currentTimeMillis();
        Grid demoniacGrid = TestModel.demoniacGrille();
        TwinsAlgorithm twins = new TwinsAlgorithm();
        try {
            Grid res = twins.compute(demoniacGrid);
            System.out.println("------FINISH DEMONIAC GRID-------");
            System.out.println(res.toString());
        } catch (Exception e) {
            System.out.println("ERROR FAIL DEMONIAC TWINS" + e);
        }

        long t1 = System.currentTimeMillis();
        TestModel.messageDeFin(t0, t1);
    }
}