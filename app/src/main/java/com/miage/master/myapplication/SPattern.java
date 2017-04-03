import java.util.ArrayList;
import java.util.Random;

/**
 * @author rémidelmas
 */
public class SPattern extends VirtualPattern{

    public SPattern(){super();}

    public PairCoord Next(){
        if (isOver()) {
            row = column = 1;
            caseLeft = 9 * 9;
        }
        PairCoord tmp;
        caseLeft--;
        //If odd - left2right
        if(column%2==1)
            return (row < 10 ) ? new PairCoord(row++, column): new PairCoord(row, column++);
        return (row>1) ? new PairCoord(row--, column) : new PairCoord(row, column++);
    }
}
