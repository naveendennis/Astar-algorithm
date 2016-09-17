package beans;

import java.util.ArrayList;
import java.util.List;

/**
 * Each state in the 8 puzzle problem has 8x8 values of numbers from 1-8. The
 * blank position is represented by 0. It will also contain its heuristic and
 * cost function along with its ancestors.
 * 
 * @author dennis
 *
 */
public class State {

	/**
	 * It holds the value of the State
	 */
	private Integer[][] stateValues;
	/**
	 * It holds the cost of the state from initial state to the current state.
	 */
	private int g;
	/**
	 * It holds the cost of the state from the current state to the goal state.
	 */
	private int h;

	/**
	 * This holds the list of ancestors of the state
	 */
	List<State> ancestors;

	/**
	 * It initializes the ancestors object along with object creation.
	 */
	public State() {
		ancestors = new ArrayList<State>();
	}

	/**
	 * Using this constructor one state can be assigned to another by value. The
	 * heuristic, cost and ancestors are set in this constructor.
	 * 
	 * @param e
	 *            the state that is to be assigned to the current state
	 */
	public State(State e) {
		// TODO fix this
		this.stateValues = new Integer[e.stateValues.length][e.stateValues[0].length];
		for (int xIterator = 0; xIterator < e.stateValues.length; xIterator++) {
			for (int yIterator = 0; yIterator < e.stateValues[xIterator].length; yIterator++) {
				this.stateValues[xIterator][yIterator] = e.stateValues[xIterator][yIterator];
			}
		}

		this.g = e.g;
		this.h = e.h;
		this.ancestors = new ArrayList<State>();
	}

	public Integer[][] getStateValues() {
		return stateValues;
	}

	public void setStateValues(Integer[][] stateValues) {
		this.stateValues = stateValues;
	}

	public void setStateValue(Integer value, int rowVal, int columnVal) {
		this.stateValues[rowVal][columnVal] = value;
	}

	public int getG() {
		return g;
	}

	public void setG(int g) {
		this.g = g;
	}

	public int getH() {
		return h;
	}

	public void setH(int h) {
		this.h = h;
	}

	public int getF() {
		return g + h;
	}

	public List<State> getAncestors() {
		return ancestors;
	}

	/**
	 * USed to set the list of ancestors
	 * @param ancestors
	 */
	public void setAncestors(List<State> ancestors) {
		this.ancestors = ancestors;
	}

	/**
	 * Adds a single ancestor to the state
	 * @param state the state to be added to the ancestor list
	 */
	public void addToAncestors(State state) {
		this.ancestors.add(state);
	}

	/**
	 * Add all the ancestors passed to this function
	 * @param states List of states that are ancestors of the current state
	 */
	public void addAllAncestors(List<State> states) {
		this.ancestors.addAll(states);
	}

	/**
	 * Overriding the equals method so that the states can be compared based on
	 * the value of its stateValues
	 */
	@Override
	public boolean equals(Object obj) {
		boolean result = true;
		if (this == obj) {
			result = true;
		} else if (this == null || obj == null) {
			result = false;
		} else {
			State gState = (State) obj;
			Integer[][] goalValue = gState.getStateValues();
			Integer[][] eachState = this.getStateValues();
			if (goalValue.length == eachState.length) {

				for (int xIterator = 0; xIterator < goalValue.length; xIterator++) {
					int yIterator = 0;
					for (; yIterator < goalValue[xIterator].length; yIterator++) {
						if (goalValue[xIterator][yIterator] != eachState[xIterator][yIterator]) {
							result = false;
							break;
						}
					}
					if (yIterator < goalValue[xIterator].length) {
						break;
					}
				}
			} else {
				result = false;
			}
		}
		return result;
	}

}
