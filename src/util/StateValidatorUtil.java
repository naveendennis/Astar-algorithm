package util;

import java.util.ArrayList;
import java.util.List;

/**
 * This class will help AStar to compute the adjacent elements of a given
 * vertex. Only singleton object of this class is made available for use to
 * AStar. It will provide functions that helps to validate the state and its
 * values.
 * 
 * @author dennis
 *
 */
public class StateValidatorUtil {
	/**
	 * It holds the rowSize of the grid
	 */
	static private int rowSize;
	/**
	 * It holds the columnSize of the grid
	 */
	static private int columnSize;

	/**
	 * LIMIT_FLAG enum is used to limit X or Y to just the valid values. At the
	 * moment, it is used in AdjacencyChecker.
	 * 
	 * @author dennis
	 *
	 */
	enum LIMIT_FLAG {
		XLIMIT, YLIMIT;
	}

	private StateValidatorUtil() {

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

	/**
	 * This function is used to find out the valid adjacent positions from the
	 * current position
	 * 
	 * @param rowIndex
	 *            current position row
	 * @param columnIndex
	 *            current position column
	 * @return the list of Integers of length 2 [x,y] such that each Integer
	 *         array is a position that is a valid adjacent position
	 */
	public static List<Integer[]> getAdjacentPositions(int rowIndex, int columnIndex) {
		List<Integer[]> result = new ArrayList<Integer[]>();
		/**
		 * One of the adjacent positions is always x+1, x-1, y+1, y-1 where its
		 * counterpart is not modified. And using this logic offsets are used to
		 * find out these four positions. And if the position is not valid then
		 * it is not added to the list of valid adjacent positions that are to
		 * be returned by this function.
		 */
		int offsets[] = { -1, 1 };
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
	 * @return true if the value is valid and no if not
	 */
	private final static boolean isValid(int value, LIMIT_FLAG flag) {
		int limit = (flag == LIMIT_FLAG.XLIMIT) ? getRowSize() : getColumnSize();
		return value >= 0 && value < limit;
	}

	/**
	 * Validates the state values with a given range
	 * @param value the value of the state (The matrix of values)
	 * @return true if it is valid and false if it is not
	 */
	public final static boolean isValidValue(int value, int rowSize, int colSize) {
		return value >= 0 && value <= rowSize*colSize-1;
	}
	
	/**
	 * Checks whether the value entered is an integer
	 * @param value token passed
	 * @return true if the token is an integer and false otherwise
	 */
	public final static boolean isInteger(String value){
		boolean result = true;
		try{
			Integer.parseInt(value); 
		}catch(Exception e){
			result = false;
		}
		return result;
	}

}
