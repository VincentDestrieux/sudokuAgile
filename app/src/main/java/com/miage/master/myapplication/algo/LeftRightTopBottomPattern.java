package com.miage.master.myapplication.algo;

import com.miage.master.myapplication.model.PairCoord;

public class LeftRightTopBottomPattern extends VirtualPattern {

    public LeftRightTopBottomPattern() {
        super();
    }

    @Override
//Do this with global values
    public PairCoord Next() {
        if (isOver()) {
            return null;
        }
        PairCoord tmp;
        caseLeft--;
        if (row < 10)
            return new PairCoord(row++, column);
        row = 1;
        return new PairCoord(row++, ++column);
    }
}