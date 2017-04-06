package com.miage.master.myapplication.algo;

import com.miage.master.myapplication.algo.LeftRightTopBottomPattern;
import com.miage.master.myapplication.algo.MovingPattern;
import com.miage.master.myapplication.algo.SolvingAlgorithm;
import com.miage.master.myapplication.model.Grid;

/**
 * Created by rémi on 02/04/2017.
 */
public abstract class VirtualAlgorithm implements SolvingAlgorithm {
    protected MovingPattern move;

    public VirtualAlgorithm() {
        move = new LeftRightTopBottomPattern();
    }

    public VirtualAlgorithm(MovingPattern pattern) {
        move = pattern;
    }

    @Override
    public void setPattern(MovingPattern pattern) {
        move = pattern;
    }

    @Override
    public String getPattern() {
        return getClass().getName();
    }

    public abstract boolean compute(Grid grid);
}
