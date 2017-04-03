import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

@SuppressWarnings("unchecked")
public class PossibilitiesGrid {
	
private ArrayList<Integer>[][] possibilitiesGrid;

// Contain the corner of 9 SG
private static ArrayList<PairCoord> CornerSG = new ArrayList<PairCoord>()
{
	{
		add(new PairCoord(1,1));
		add(new PairCoord(4,1));
		add(new PairCoord(7,1));
		add(new PairCoord(1,4));
		add(new PairCoord(4,4));
		add(new PairCoord(7,4));
		add(new PairCoord(1,7));
		add(new PairCoord(4,7));
		add(new PairCoord(7,7));
	}
};

//with empty constructor, all number are available on each cell
public PossibilitiesGrid()
{
	this.possibilitiesGrid = (ArrayList<Integer>[][]) Array.newInstance(ArrayList.class,9,9);
	for(int i=0;i<9;i++)
	{
		for(int j=0;j<9;i++)
		{
			this.possibilitiesGrid[i][j].addAll(Arrays.asList(1,2,3,4,5,6,7,8,9));
		}
	}
}

//When a digit is inserted, we had to drop this number on column, row and subgrid possibilities
public void update(PairCoord pos, int number)
{
	//Step 1 : Maybe useless, but if we manually put a number in a case & solve it after, it's 
	// necessary to manualy put this singleton
	possibilitiesGrid[pos.getX()][pos.getY()].clear();
	possibilitiesGrid[pos.getX()][pos.getY()].add(number);
	//Step 2 : remove this number, if he's here on column, row & SG
	this.removeFromColumn(pos, number);
	this.removeFromRow(pos, number);
	this.removeFromSG(pos, number);
}

private void removeFromRow(PairCoord pos, int number)
{
	for(int i=0;i<9;i++)
	{
		possibilitiesGrid[i][pos.getY()].remove((Object) number);
	}
}

private void removeFromColumn(PairCoord pos, int number)
{
	for(int i=0;i<9;i++)
	{
		possibilitiesGrid[pos.getX()][i].remove((Object) number);
	}
}

private void removeFromSG(PairCoord pos, int number)
{
	//step 1 : find the subgrid associated to this pos
	PairCoord cornerAssociatedPos = PossibilitiesGrid.CornerSG.get(Grid.getSubGrid(pos));
	//step 2 : remove number on subgrid 
	for(int i=0;i<3;i++)
	{
		for(int j=0;j<3;j++)
		{
			possibilitiesGrid[cornerAssociatedPos.getX()+i][cornerAssociatedPos.getY()+j].remove((Object) number);
		}
	}
}


}
