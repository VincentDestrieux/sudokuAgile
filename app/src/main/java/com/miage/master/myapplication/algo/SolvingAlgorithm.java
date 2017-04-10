package com.miage.master.myapplication.algo;

import com.miage.master.myapplication.algo.MovingPattern;
import com.miage.master.myapplication.model.Grid;

/**
 * @authon rémi delmas
 */
public interface SolvingAlgorithm {

    //Solve the given grid item
    Grid compute(Grid grid);

    void setPattern(MovingPattern pattern);
    // Default pattern LRTBPattern
    String getPattern();
}
