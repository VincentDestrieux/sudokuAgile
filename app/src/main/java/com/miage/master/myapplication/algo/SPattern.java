package com.miage.master.myapplication.algo;

import com.miage.master.myapplication.model.PairCoord;

/**
 * @author rémidelmas
 */
public class SPattern extends VirtualPattern {

    public SPattern() {
        super();
    }

    public PairCoord Next() {
        if (isOver()) {
            return null;
        }
        PairCoord tmp;
        caseLeft--;
        //If odd - left2right
        if (column % 2 == 1)
            return (row < 10) ? new PairCoord(row++, column) : new PairCoord(row, column++);
        return (row > 1) ? new PairCoord(row--, column) : new PairCoord(row, column++);
    }
}
