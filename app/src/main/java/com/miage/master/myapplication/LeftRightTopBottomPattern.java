public class LeftRightTopBottomPattern extends VirtualPattern{

	public LeftRightTopBottomPattern() {
		super();
	}

	@Override
//Do this with global values
	public PairCoord Next() {
		if (isOver()) {
			row = column = 1;
			caseLeft = 9 * 9;
		}
		PairCoord tmp;
		caseLeft--;
		if (row < 10)
			return new PairCoord(row++, column);
		row = 1;
		return new PairCoord(row++, ++column);
	}
}