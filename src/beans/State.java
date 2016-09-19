package beans;

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

	State parent;

	/**
	 * It initializes the ancestors object along with object creation.
	 */
	public State() {
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
		this.setStateValues(e);

		this.g = e.g;
		this.h = e.h;
		this.parent = null;
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

	public State getParent() {
		return parent;
	}

	public void setParent(State parent) {
		this.parent = parent;
	}

	public void setStateValues(State state) {
		this.stateValues = new Integer[state.stateValues.length][state.stateValues[0].length];
		for (int xIterator = 0; xIterator < state.stateValues.length; xIterator++) {
			for (int yIterator = 0; yIterator < state.stateValues[xIterator].length; yIterator++) {
				this.stateValues[xIterator][yIterator] = state.stateValues[xIterator][yIterator];
			}
		}
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
