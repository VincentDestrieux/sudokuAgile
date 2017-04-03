/**
 * Created by rémi on 02/04/2017.
 */
public abstract class VirtualPattern implements MovingPattern{
    protected int caseLeft;
    protected int row;
    protected int column;

    public VirtualPattern()
    {
        row = column = 1;
        caseLeft = 9*9;
    }

    public abstract PairCoord Next();

    public boolean isOver() {
        return (caseLeft == 0);
    }
}
