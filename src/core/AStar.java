package core;

/**
 * COPYRIGHTS. All rights reserved. THis belongs to Naveen Dennis B from UNC Charlotte
 */
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import beans.State;
import util.AdjacencyChecker;
import util.StateUtil;

/**
 * This class contains the code for the A* algorithm.
 * 
 * @author dennis
 *
 */
public class AStar {
	/**
	 * The history of the state space visited to the destination is stored in a
	 * local variable history.
	 */
	private List<State> closedList;

	/**
	 * The currentState hold the currentState of the problem. It is initialized
	 * with the initial state.
	 */
	private State currentState;
	/**
	 * The goalState holds the goal state of the problem. This variable is used
	 * as part of goalTest().
	 */
	private State goalState;

	/**
	 * The priority queue that is used to
	 */
	private PriorityQueue<State> openList;

	/**
	 * Initializing AStar algorithm by initializing the history variable, the
	 * initState and goalState.
	 * 
	 * @param currentState
	 *            The begin state of the 8 puzzle problem.
	 * @param goalState
	 *            The goal state of the 8 puzzle problem which is used in
	 *            goalTest().
	 */
	public AStar(State initState, State goalState, int rowSize, int columnSize) {
		closedList = new ArrayList<State>();
		this.currentState = initState;
		/**
		 * Here the G value for the current state is set to 1 since it is the
		 * initial state. And for each successive state the g value is added
		 * with 1.
		 */
		currentState.setG(1);
		currentState.setH(StateUtil.calculateH(currentState, goalState));
		closedList.add(currentState);
		this.goalState = goalState;
		openList = new PriorityQueue<State>(10, new Comparator<State>() {
			@Override
			public int compare(State o1, State o2) {
				return o1.getF() - o2.getF();
			}
		});
	}

	/**
	 * checks if the currentState is the goalState the goalTest succeeds and
	 * fails otherwise.
	 * 
	 * @return result of the goal test
	 */
	private boolean goalTest() {
		return currentState.equals(goalState);
	}

	public List<State> search() {
		while (!goalTest()) {
			List<State> fringe = generateSuccessors(currentState, goalState);
			openList.addAll(fringe);
			currentState = openList.poll();
			closedList.add(currentState);
		}

		List<State> path = currentState.getAncestors();
		path.add(currentState);
		return path;

	}

	/**
	 * Generates the successor states from the current state. It does not
	 * calculate the heuristics function of the state.
	 * 
	 * @param currentState
	 *            parent to the successor states
	 * @return the generated states (successors)
	 */
	private List<State> generateSuccessors(State currentState, State goalState) {
		int[] swapPosition = StateUtil.gridSearch(0, currentState);
		List<State> successorStates = new ArrayList<State>();
		List<Integer[]> adjacentPositions = AdjacencyChecker.getAdjacentPositions(swapPosition[0], swapPosition[1]);
		for (Integer[] eachPosition : adjacentPositions) {
			State newState = new State(currentState);
			if (!adjacentPositions.isEmpty()) {
				int temp = newState.getStateValues()[swapPosition[0]][swapPosition[1]];
				newState.addAllAncestors(currentState.getAncestors());
				newState.addToAncestors(currentState);
				newState.setStateValue(currentState.getStateValues()[eachPosition[0]][eachPosition[1]], swapPosition[0],
						swapPosition[1]);
				newState.setStateValue(temp, eachPosition[0], eachPosition[1]);
				newState.setG(currentState.getG() + 1);
				newState.setH(StateUtil.calculateH(newState, goalState));
			}
			if (!closedList.contains(newState)) {
				successorStates.add(newState);
			}
		}
		return successorStates;
	}

}
