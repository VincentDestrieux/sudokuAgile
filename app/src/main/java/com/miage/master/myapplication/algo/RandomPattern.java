package com.miage.master.myapplication.algo;

import com.miage.master.myapplication.algo.MovingPattern;
import com.miage.master.myapplication.model.PairCoord;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by rémi on 02/04/2017.
 */
public class RandomPattern implements MovingPattern {
    private ArrayList<PairCoord> cellAvailable;
    private Random randomPos;

    public RandomPattern() {
        cellAvailable = new ArrayList<PairCoord>();
        randomPos = new Random();
        for (int i = 1; i < 10; i++)
            for (int j = 1; j < 10; j++)
                cellAvailable.add(new PairCoord(i, j));
    }

    @Override
    public PairCoord Next() {
        if (this.isOver()) {
            PairCoord cell = cellAvailable.remove(randomPos.nextInt(cellAvailable.size()));
            return cell;
        }
        return null;
    }

    @Override
    public boolean isOver() {
        return cellAvailable.isEmpty();
    }

    public void toBegin() {
        cellAvailable.clear();
        for (int i = 1; i < 10; i++)
            for (int j = 1; j < 10; j++)
                cellAvailable.add(new PairCoord(i, j));
    }
}