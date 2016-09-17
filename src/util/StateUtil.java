package util;
import java.util.HashMap;
import java.util.Map;

import beans.State;

public class StateUtil {

	/**
	 * Calculates the manhattan distance between the two states passed
	 * 
	 * @param currentState
	 * @param goalState
	 * @return
	 */
	public static int calculateH(State currentState, State goalState) {
		Map<Integer, Integer[]> currStateValues = getStateValues(currentState);
		Map<Integer, Integer[]> goalStateValues = getStateValues(goalState);
		int h = 0;
		for (Integer key : currStateValues.keySet()) {
			Integer[] currPos = currStateValues.get(key);
			Integer[] goalPos = goalStateValues.get(key);
			if (!key.equals(0)) {
				h += Math.abs(currPos[0] - goalPos[0]) + Math.abs(currPos[1] - goalPos[1]);
			}
		}
		return h;
	}

	/**
	 * This function populates Map with the values of the consideredState along
	 * with their positions in the grid.
	 * 
	 * @return the map with all the positions of the state's values.
	 */
	public static Map<Integer, Integer[]> getStateValues(State consideredState) {
		Map<Integer, Integer[]> goalPositions = new HashMap<Integer, Integer[]>();
		Integer[][] stateValues = consideredState.getStateValues();
		for (int xIterator = 0; xIterator < stateValues.length; xIterator++) {
			for (int yIterator = 0; yIterator < stateValues[xIterator].length; yIterator++) {
				goalPositions.put(consideredState.getStateValues()[xIterator][yIterator],
						new Integer[] { xIterator, yIterator });
			}
		}
		return goalPositions;
	}

	/**
	 * This function searches for a value in the given State.
	 * 
	 * @param value
	 *            - the value that is being searched.
	 * @param currentState
	 *            - the state on which the search is performed.
	 * @return the position of the value in the currentState
	 */
	public static int[] gridSearch(int value, State currentState) {
		Integer[][] testState = currentState.getStateValues();
		for (int xIterator = 0; xIterator < testState.length; xIterator++) {
			for (int yIterator = 0; yIterator < testState[xIterator].length; yIterator++) {
				if (currentState.getStateValues()[xIterator][yIterator] == value) {
					return new int[] { xIterator, yIterator };
				}
			}
		}
		return null;
	}
}