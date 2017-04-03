import java.util.Arrays;

/** Classe de manipulation de la grille de jeu, contenant la grille de jeu, et une matrice 9*9 
 * contenant les chiffres pouvant potentiellement être valide à cette position.
 * 
 * @author remidelmas
 *
 */


public class Grid {
	
//Filled with -1 when empty
private int[][] mainGrid;

// A little bit dirty, we could build our own class to manipulate this item.
// Or just a class for this who can take a Grid item for parameter 
private PossibilitiesGrid possibleGrid;

public Grid()
{
	mainGrid = new int[9][9];
	//Initialize main grid
	for (int i = 0; i < 9; i++) 
	{
		Arrays.fill(mainGrid[i],0);
	}
	//Initialize possibilities grid	
	possibleGrid = new PossibilitiesGrid();
}

public Grid(int[][] _matrix)
{
	int tmpVal;
	mainGrid = new int[9][9];
	if(_matrix.length != 9)
		throw new IllegalArgumentException("Matrix must be 9x9");
	for(int i=0;i<9;i++)
	{
		if(_matrix[i].length != 9)
			throw new IllegalArgumentException("Matrix must be 9x9");
		for(int j=0;j<9;j++)
		{
			tmpVal = _matrix[i][j];
			if(tmpVal<-1 || tmpVal>9)
				throw new IllegalArgumentException("Invalid value pos : " + i +","+j);
			mainGrid[i][j]=tmpVal;
		}
	}
	possibleGrid = new PossibilitiesGrid();
}

public void setDigit(PairCoord pair, int value)
{
	mainGrid[pair.getX()-1][pair.getY()-1] = value;
}

//Return -1 if empty
public int getDigit(PairCoord pair)
{
	return mainGrid[pair.getX()-1][pair.getY()-1];
}

@Override
public String toString() {
 StringBuilder _string = new StringBuilder();
 for(int i =0;i<9;i++)
 {
	 for(int j=0;j<9;j++)
	 {
		 _string.append("|");
		 _string.append(getDigit(new PairCoord(i+1,j+1)));
		 _string.append("|");
	 }
	 _string.append("\n");
 }
 return _string.toString();
}

public Grid clone()
{
	int[][] _copyGrid = new int[9][9];
	for(int i=0;i<9;i++)
	{
		_copyGrid[i]=Arrays.copyOf(mainGrid[i],9);
	}
	return new Grid(_copyGrid);
}

public int[] getLine(PairCoord pair)
{ 
	int[] _line = new int[9];
	for(int i=0;i<9;i++)
	{
		_line[i]=getDigit(new PairCoord(i,pair.getY()));
	}
	return _line;
}

public int[] getColumn(PairCoord pair)
{
	int[] _column = new int[9];
	for(int i=0;i<9;i++)
	{
		_column[i]=getDigit(new PairCoord(pair.getX(),i));
	}
	return _column;
}

/* return number between 1 & 9 corresponding to an submatrix index
 * 
 * 1|2|3
 * 4|5|6
 * 7|8|9
 */
public static int getSubGrid(PairCoord pair)
{
	return ((pair.getY() / 3) + (pair.getX() / 3) * 3) + 1; 
}

//Testing main - /!\ Move to Log.d on Android device !
/*public static void main (String[] args){
	Grid grid = new Grid();
	System.out.println(grid.toString());
	grid.setDigit(new PairCoord(1,1), 9);
	grid.setDigit(new PairCoord(2,2), 8);
	grid.setDigit(new PairCoord(3,3), 7);
	System.out.println(grid.toString());
}
*/

}
