public class LeftRightTopBottomPattern implements MovingPattern{

private int caseLeft;
private int row;
private int column;

public LeftRightTopBottomPattern()
{
	row = column = 1;
	caseLeft = 9*9;
}

@Override
//Do this with global values
public PairCoord Next() 
{
	if(isOver())
	{
		row = column = 1;
		caseLeft = 9*9;
	}
	PairCoord tmp;
	if(row<10)
	{
		caseLeft--;
		return new PairCoord(row++,column);
	}
	else
	{
		caseLeft--;
		row = 1;
		return new PairCoord(row++,++column);
	}
}

@Override
public boolean isOver() {
	return (caseLeft == 0);
}
}
