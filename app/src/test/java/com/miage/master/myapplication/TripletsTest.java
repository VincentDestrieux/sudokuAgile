package com.miage.master.myapplication;

import com.miage.master.myapplication.algo.BackTrackingAlgorithm;
import com.miage.master.myapplication.algo.TripletsAlgorithm;
import com.miage.master.myapplication.model.Grid;

import org.junit.Test;

public class TripletsTest {

    @Test
    public void TripletsTest() throws Exception {
        easyTestGrid();
        //mediumTestGrid();
        //hardTestGrid();
        //demoniacTestGrid();
    }

    public void easyTestGrid() {
        long t0 = System.currentTimeMillis();
        Grid easyGrid = TestModel.easyGrille();
        TripletsAlgorithm triplet = new TripletsAlgorithm();
        try {
            Grid res = triplet.compute(easyGrid);
            System.out.println("------FINISH EASY GRID-------");
            System.out.println(res.toString());
        } catch (Exception e) {
            System.out.println("ERROR FAIL EASY TRIPLETS" + e);
        }

        long t1 = System.currentTimeMillis();
        TestModel.messageDeFin(t0, t1);
    }

    public void mediumTestGrid() {
        long t0 = System.currentTimeMillis();
        Grid mediumGrid = TestModel.mediumGrille();
        TripletsAlgorithm triplet = new TripletsAlgorithm();
        try {
            Grid res = triplet.compute(mediumGrid);
            System.out.println("------FINISH MEDIUM GRID-------");
            System.out.println(res.toString());
        } catch (Exception e) {
            System.out.println("ERROR FAIL MEDIUM TRIPLET" + e);
        }

        long t1 = System.currentTimeMillis();
        TestModel.messageDeFin(t0, t1);
    }

    public void hardTestGrid() {
        long t0 = System.currentTimeMillis();
        Grid hardGrid = TestModel.hardGrille();
        TripletsAlgorithm triplet = new TripletsAlgorithm();
        try {
            Grid res = triplet.compute(hardGrid);
            System.out.println("------FINISH HARD GRID-------");
            System.out.println(res.toString());
        } catch (Exception e) {
            System.out.println("ERROR FAIL HARD TRIPLETS" + e);
        }

        long t1 = System.currentTimeMillis();
        TestModel.messageDeFin(t0, t1);
    }

    public void demoniacTestGrid() {
        long t0 = System.currentTimeMillis();
        Grid demoniacGrid = TestModel.demoniacGrille();
        TripletsAlgorithm triplet = new TripletsAlgorithm();
        try {
            Grid res = triplet.compute(demoniacGrid);
            System.out.println("------FINISH DEMONIAC GRID-------");
            System.out.println(res.toString());
        } catch (Exception e) {
            System.out.println("ERROR FAIL DEMONIAC TRIPLETS" + e);
        }

        long t1 = System.currentTimeMillis();
        TestModel.messageDeFin(t0, t1);
    }
}