package com.miage.master.myapplication.algo;

import com.miage.master.myapplication.model.*

/**
 * Created by azown on 06/04/2017.
 */
public class BCRELoneAlgorithm extends VirtualAlgorithm
{

    public BCRELoneAlgorithm() {super();}
    public BCRELoneAlgorithm(MovingPattern pattern) {super(pattern);}

    public Grid compute(Grid grid)
    {
        SolvingAlgorithm solverBCRE = new BCREAlgorithm(this.move);
        SolvingAlgorithm solverLone = new LoneAlgorithm(this.move);

        do
        {
            grid = solverBCRE.compute(grid);
            if(!grid.isComplete())
            {
                grid = solverLone.compute(grid);
            }
        }while(!grid.isComplete() && grid.isChangeWithAlg());
        return grid;
    }
}
