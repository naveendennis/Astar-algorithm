package beans;

import java.util.ArrayList;
import java.util.List;

public class State {
	
	private Integer[][] stateValues;
	private int g;
	private int h;

	List<State> ancestors;

	public State() {
		ancestors = new ArrayList<State>();
	}

	public State(State e) {
		//TODO fix this
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

	public void setAncestors(List<State> ancestors) {
		this.ancestors = ancestors;
	}

	public void addToAncestors(State state) {
		this.ancestors.add(state);
	}

	public void addAllAncestors(List<State> states) {
		this.ancestors.addAll(states);
	}

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
