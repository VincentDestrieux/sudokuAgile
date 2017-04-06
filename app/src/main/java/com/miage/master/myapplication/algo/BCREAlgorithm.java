package com.miage.master.myapplication.algo;

import com.miage.master.myapplication.model.Grid;
import com.miage.master.myapplication.model.PairCoord;

/**
 * Created by rémi on 02/04/2017.
 */
public class BCREAlgorithm extends VirtualAlgorithm {
    public BCREAlgorithm() {
        super();
    }

    public BCREAlgorithm(MovingPattern pattern) {
        super(pattern);
    }

    @Override
    public boolean compute(Grid grid) {
        System.out.println("START \n **********************************************************\n\n");
        System.out.println(grid);
        //Number of cell validated in this loop
        int validatedCell, singletonValue;
        PairCoord currentPos;
        int cmp = 1;
        do {
            validatedCell = 0;
            while ((currentPos = move.Next()) != null) {
                //On analyse case a case - si une case est un singleton, alors on update la case a la valeur de ce singleton
                // -> L'update dans la classe com.miage.master.myapplication.algo.PossibilitiesGrid va alors modifier colonne, grille et subgrid.
                if ((singletonValue = grid.getSingleton(currentPos)) > 0)
                    if (grid.setDigit(currentPos, singletonValue))
                        validatedCell++;
            }
            move.toBegin();
            System.out.println("Iteration " + (cmp++) + ":\n" + grid);
        } while (validatedCell > 0);
        // Check if ok
        while ((currentPos = move.Next()) != null) {
            if ((grid.getSingleton(currentPos) < 0)) {
                System.out.println("ECHEC \n");
                System.out.println(grid.toString());
                return false;
            }
        }
        System.out.println(grid.toString());
        return true;
    }
}
