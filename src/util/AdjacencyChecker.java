package util;
import java.util.ArrayList;
import java.util.List;

/**
 * This class will help AStar to compute the adjacent elements of a given
 * vertex. Only singleton object of this class is made available for use to
 * AStar.
 * 
 * @author dennis
 *
 */
public class AdjacencyChecker {
	/**
	 * It holds the rowSize of the grid
	 */
	static private int rowSize;
	/**
	 * It holds the columnSize of the grid
	 */
	static private int columnSize;

	/**
	 * LIMIT_FLAG enum is used to limit X or Y
	 * 
	 * @author dennis
	 *
	 */
	enum LIMIT_FLAG {
		XLIMIT, YLIMIT;
	}

	private AdjacencyChecker() {

	}

	public static int getRowSize() {
		return rowSize;
	}

	public static void setRowSize(int r) {
		rowSize = r;
	}

	public static int getColumnSize() {
		return columnSize;
	}

	public static void setColumnSize(int c) {
		columnSize = c;
	}

	public static List<Integer[]> getAdjacentPositions(int rowIndex, int columnIndex) {
		List<Integer[]> result = new ArrayList<Integer[]>();
		int offsets[] = { 1, -1 };
		for (int xIterator = 0; xIterator < 4; xIterator++) {
			Integer[] potentialPoint = new Integer[2];
			if (xIterator < 2) {
				potentialPoint[0] = rowIndex + offsets[xIterator];
				potentialPoint[1] = columnIndex;
				if (!isValid(potentialPoint[0], LIMIT_FLAG.XLIMIT)) {
					continue;
				}
			} else {
				potentialPoint[1] = columnIndex + offsets[xIterator % 2];
				potentialPoint[0] = rowIndex;
				if (!isValid(potentialPoint[1], LIMIT_FLAG.YLIMIT)) {
					continue;
				}
			}
			result.add(potentialPoint);
		}
		return result;
	}

	/**
	 * Checks if the given value is valid or invalid
	 * 
	 * @param value
	 * @param flag
	 *            It is 0 when the limit check is done for X and 1 when the
	 *            limit check is done for Y
	 * @return
	 */
	private final static boolean isValid(int value, LIMIT_FLAG flag) {
		int limit = (flag == LIMIT_FLAG.XLIMIT) ? getRowSize() : getColumnSize();
		return value >= 0 && value < limit;
	}

}