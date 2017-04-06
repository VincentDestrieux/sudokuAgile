package com.miage.master.myapplication.algo;

import com.miage.master.myapplication.model.PairCoord;

public interface MovingPattern {
    //Retourne la case suivante
    PairCoord Next();

    boolean isOver();

    //curseur au début
    void toBegin();
}
