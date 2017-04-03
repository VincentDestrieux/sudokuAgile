
/**
 * @author remidelmas
 *
 */

public class PairCoord {

private int x;
private int y;

public PairCoord()
{
	x=y=-1;
}

public PairCoord(int x, int y) throws IllegalArgumentException 
{
	if(x<1 || x>9 || y<1 || y>9)
	{
		throw new IllegalArgumentException("Invalid pos for sudoku x: " + x + " y : " + y);
	}
	this.x = x;
	this.y = y;
}



public int getX() 
{
	return x;
}

public void setX(int x) 
{
	if(x<1 || x>9)
	{
		throw new IllegalArgumentException("Invalid pos for sudoku x: " + x);
	}
	this.x = x;
}

public int getY() 
{
	return y;
}

public void setY(int y) 
{
	if(y<1 || y>9)
	{
		throw new IllegalArgumentException("Invalid pos for sudoku y: " + y);
	}
	this.y = y;
}

@Override
public String toString() {
	return "PairCoord [x=" + x + ", y=" + y + "]";
}

@Override
public int hashCode() {
	final int prime = 31;
	int result = 1;
	result = prime * result + x;
	result = prime * result + y;
	return result;
}

@Override
public boolean equals(Object obj) {
	if (this == obj)
		return true;
	if (obj == null)
		return false;
	if (getClass() != obj.getClass())
		return false;
	PairCoord other = (PairCoord) obj;
	if (x != other.x)
		return false;
	if (y != other.y)
		return false;
	return true;
}

public PairCoord clone()
{
	return new PairCoord(x,y);
}

}


