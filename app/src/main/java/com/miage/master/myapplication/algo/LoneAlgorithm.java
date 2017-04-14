package com.miage.master.myapplication.algo;

import com.miage.master.myapplication.model.*;

import java.util.ArrayList;

/**
 * Created by rémi on 06/04/2017.
 * Cet Algorithme est un algorithme de vérification utilisé dans l'algorithme BCRELoneAlgorithm
 * qui est appelé si le BCREAlgorithm ne suffit pas à résoudre la grille
 */
public class LoneAlgorithm extends VirtualAlgorithm {
    private static final ArrayList<PairCoord> CornerSG = new ArrayList<PairCoord>() {
        {
            add(new PairCoord(1, 1));
            add(new PairCoord(4, 1));
            add(new PairCoord(7, 1));
            add(new PairCoord(1, 4));
            add(new PairCoord(4, 4));
            add(new PairCoord(7, 4));
            add(new PairCoord(1, 7));
            add(new PairCoord(4, 7));
            add(new PairCoord(7, 7));
        }
    };

    public LoneAlgorithm() {
        super();
    }

    public LoneAlgorithm(MovingPattern pattern) {
        super(pattern);
    }

    @Override
    public Grid compute(Grid grid) {
        //compute on line
        for (int i = 1; i < 10; i++)
            if (grid.LoneRow(new PairCoord(1, i)))
                grid.setChangeWithAlg(true);
        //if not complete, perform on column
        if (!grid.checkComplete()) {
            for (int j = 1; j < 10; j++)
                if (grid.LoneColumn(new PairCoord(j, 1)))
                    grid.setChangeWithAlg(true);
            if (!grid.checkComplete()) {
                for (PairCoord corner : CornerSG)
                    if (grid.LoneBlock(corner))
                        grid.setChangeWithAlg(true);
                grid.checkComplete();
            }
        }
        //System.out.print(grid.toString());
        return grid;
    }
}
