/**
 * @authon rémi delmas
 */
public interface SolvingAlgorithm {

    //Solve the given grid item
    boolean compute(Grid grid);

    void setPattern(MovingPattern pattern);
    // Default pattern LRTBPattern
    String getPattern();
}
