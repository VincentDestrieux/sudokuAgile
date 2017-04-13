package com.miage.master.myapplication;

import com.miage.master.myapplication.algo.LeftRightTopBottomPattern;

import org.junit.Test;

public class LeftRightTopBottomPatternTest {
    @Test
    public void onCreate() throws Exception {

        LeftRightTopBottomPattern move = new LeftRightTopBottomPattern();
        for (int i = 1; i < 82; i++) {
            System.out.println(move.Next());
        }
    }
}