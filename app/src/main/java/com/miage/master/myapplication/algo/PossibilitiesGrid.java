package com.miage.master.myapplication.algo;

import com.miage.master.myapplication.model.Grid;
import com.miage.master.myapplication.model.PairCoord;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unchecked")
public class PossibilitiesGrid {

    private ArrayList<Integer>[][] possibilitiesGrid;

    // Contain the corner of 9 SG
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

    //with empty constructor, all number are available on each cell
    public PossibilitiesGrid() {
        this.possibilitiesGrid = (ArrayList<Integer>[][]) Array.newInstance(ArrayList.class, 9, 9);
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                //System.out.println(possibilitiesGrid[i][j].size());
                this.possibilitiesGrid[i][j] = new ArrayList<Integer>(9);
                this.possibilitiesGrid[i][j].addAll(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9));
            }
            System.out.println("\n");
        }
    }

    //When a digit is inserted, we had to drop this number on column, row and subgrid possibilities
    public boolean update(PairCoord pos, int number) {
        //Step 1 : Maybe useless, but if we manually put a number in a case & solve it after, it's
        // necessary to manualy put this singleton
        possibilitiesGrid[pos.getY() - 1][pos.getX() - 1].clear();
        possibilitiesGrid[pos.getY() - 1][pos.getX() - 1].add(number);
        //System.out.println(pos);
        //System.out.println(this.toString());
        boolean returnRow = this.removeFromRow(pos, number);
        boolean returnCol = this.removeFromColumn(pos, number);
        boolean returnSG = this.removeFromSG(pos, number);
        return (returnCol || returnRow || returnSG);
    }

    private boolean removeFromRow(PairCoord pos, int number) {
        boolean change = false;
        System.out.println("Delete " + number + " from " + pos.getY() + " row\n");
        for (int i = 0; i < 9; i++) {
            //Si changement dans une case et pas encore de changement noté
            if (!(this.isSingleton(new PairCoord(i + 1, pos.getY())))) {
                System.out.println("Before :  (" + (i + 1) + "," + pos.getY() + ") -> " + possibilitiesGrid[pos.getY() - 1][i]);
                if (possibilitiesGrid[pos.getY() - 1][i].remove(new Integer(number))) {
                    System.out.println("After :  (" + (i + 1) + "," + pos.getY() + ") -> " + possibilitiesGrid[pos.getY() - 1][i]);
                    change = true;
                }
            }
        }
        System.out.println("\n");
        return change;
    }

    private boolean removeFromColumn(PairCoord pos, int number) {
        boolean change = false;
        System.out.println("Delete " + number + " from " + pos.getX() + " column\n");
        for (int i = 0; i < 9; i++) {
            if (!(this.isSingleton(new PairCoord(pos.getX(), i + 1)))) {
                System.out.println("Before :  (" + pos.getX() + "," + (i + 1) + ") -> " + possibilitiesGrid[i][pos.getX() - 1]);
                if (possibilitiesGrid[i][pos.getX() - 1].remove(new Integer(number))) {
                    System.out.println("After :  (" + pos.getX() + "," + (i + 1) + ") -> " + possibilitiesGrid[i][pos.getX() - 1]);
                    change = true;
                }
            }
        }
        System.out.println("\n");
        return change;
    }

    private boolean removeFromSG(PairCoord pos, int number) {
        //step 1 : find the subgrid associated to this pos
        System.out.println(pos);
        System.out.println(Grid.getSubGrid(pos));
        PairCoord cornerAssociatedPos = PossibilitiesGrid.CornerSG.get(Grid.getSubGrid(pos));

        System.out.println("Delete " + number + " from " + cornerAssociatedPos + " SG");
        boolean change = false;
        //step 2 : remove number on subgrid
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                //IF SINGLETON
                if (!(this.isSingleton(new PairCoord(cornerAssociatedPos.getX() + j, cornerAssociatedPos.getY() + i)))) {
                    System.out.println("Before :  (" + (cornerAssociatedPos.getX() + i) + "," + (cornerAssociatedPos.getY() + j) + ") -> " + possibilitiesGrid[cornerAssociatedPos.getY() + i - 1][cornerAssociatedPos.getX() + j - 1]);
                    if (possibilitiesGrid[cornerAssociatedPos.getY() + i - 1][cornerAssociatedPos.getX() + j - 1].remove(new Integer(number)))
                        change = true;
                    System.out.println("After :  (" + (cornerAssociatedPos.getX() + i) + "," + (cornerAssociatedPos.getY() + j) + ") -> " + possibilitiesGrid[cornerAssociatedPos.getY() + i - 1][cornerAssociatedPos.getX() + j - 1]);
                }
            }
        }
        System.out.println("\n");
        return change;
    }

    public boolean isSingleton(PairCoord pos) {
        //System.out.println(this.possibilitiesGrid[pos.getY()-1][pos.getX()-1].size());
        return this.possibilitiesGrid[pos.getY() - 1][pos.getX() - 1].size() == 1;
    }

    public int getSingleton(PairCoord pair) {
        return (this.isSingleton(pair)) ? possibilitiesGrid[pair.getY() - 1][pair.getX() - 1].get(0) : -1;
    }

    public String toString() {
        StringBuilder _string = new StringBuilder();
        for (int i = 0; i < 9; i++) {
            for (int j = 0; j < 9; j++) {
                _string.append("(" + (j + 1) + "," + (i + 1) + ") - ");
                _string.append(possibilitiesGrid[i][j]);
                _string.append("\n");
            }
            _string.append("\n");
        }
        return _string.toString();
    }

/*
public void LoneColumn(com.miage.master.myapplication.model.PairCoord pos)
{
	//Initialise
	ArrayList<ArrayList<com.miage.master.myapplication.model.PairCoord>> run = new ArrayList<>(9);
	for(int i=0;i<9;i++)
	{
		run.add(new ArrayList<com.miage.master.myapplication.model.PairCoord>());
	}
	//Run on column -> if a value appears only one if this column, it's the only option to solve this grid.
	for(int i=0;i<9;i++)
	{
		if (!(this.isSingleton(new com.miage.master.myapplication.model.PairCoord(pos.getX(), i + 1)))) {
			for (Integer value : possibilitiesGrid[i][pos.getX() - 1])
				run.get(value).add(new com.miage.master.myapplication.model.PairCoord(pos.getX(), i + 1));
		}
	}
	//Now we check if some values appears only
	for(ArrayList<com.miage.master.myapplication.model.PairCoord> numberOfAppears : run)
	{
		if(numberOfAppears.size() == 1)
		{

		}
	}
*/


}

