/**
 * COPYRIGHTS. All rights reserved. THis belongs to Naveen Dennis B from UNC Charlotte
 */
package core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

import beans.State;
import util.StateValidatorUtil;
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
	 * @param initState
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
		openList = new PriorityQueue<State>(1500, new Comparator<State>() {
			@Override
			public int compare(State o1, State o2) {
				return o1.getF() - o2.getF();
			}
		});
	}

	/**
	 * @return the number of states explored during the search process.
	 */
	public List<State> getClosedList() {
		return closedList;
	}

	/**
	 * @return the number of states that are to be explored in the next level.
	 */
	public PriorityQueue<State> getOpenList() {
		return openList;
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

	/**
	 * searches the state space tree and returns the path to the goal state.
	 * 
	 * @return path to the goal state.
	 */
	public List<State> search() {
		/**
		 * Checks if the currentState is the goalState and loops through the
		 * successors until the goal is reached.
		 */
		while (!goalTest()) {
			/**
			 * (Step 1): Generate the successors.
			 */
			List<State> fringe = generateSuccessors(currentState, goalState);
			/**
			 * (Step 2): Add them all to the priority Queue.
			 */
			openList.addAll(fringe);
			/**
			 * (Step 3): Poll the state with the lowest f(x) score and set that
			 * as currentState.
			 */
			currentState = openList.poll();
			/**
			 * (Step 4): Add the currentstate to the closedList.
			 */
			closedList.add(currentState);
		}
		/**
		 * Construct the path from the initial state to the goal state and
		 * return the path.
		 */

		List<State> path = StateUtil.getPath(currentState);
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
		/**
		 * At first it finds the position of zero in the grid which is the blank
		 * space that can be occupied in the state.
		 */
		int[] swapPosition = StateUtil.gridSearch(0, currentState);
		List<State> successorStates = new ArrayList<State>();
		/**
		 * Then find out the valid adjacent positions to the swapPosition.
		 */
		List<Integer[]> adjacentPositions = StateValidatorUtil.getAdjacentPositions(swapPosition[0], swapPosition[1]);
		/**
		 * For each position iterate over them.
		 */
		for (Integer[] eachPosition : adjacentPositions) {
			/**
			 * Create a new state for each iteration and assign the value of the
			 * current state.
			 */
			State newState = new State(currentState);
			if (!adjacentPositions.isEmpty()) {
				/**
				 * Assign the value of the blank to the adjacent space and swap
				 * the blank it the adjacent nodes' current position.
				 */
				int temp = newState.getStateValues()[swapPosition[0]][swapPosition[1]];
				newState.setParent(currentState);
				newState.setStateValue(currentState.getStateValues()[eachPosition[0]][eachPosition[1]], swapPosition[0],
						swapPosition[1]);
				newState.setStateValue(temp, eachPosition[0], eachPosition[1]);
				/**
				 * For each successor calculate g(x) as the g(x) of the ancestor
				 * incremented by 1
				 */
				newState.setG(currentState.getG() + 1);
				/**
				 * For each state created calculate the heuristics, h(x). The
				 * one used here is the sum of manhattan distances of the
				 * misplaced tiles.
				 */
				newState.setH(StateUtil.calculateH(newState, goalState));
			}
			/**
			 * If the adjacent position is already in the closed list it is not
			 * considered as a part of the successor states.
			 */
			if (!closedList.contains(newState)) {
				successorStates.add(newState);
			}
		}
		return successorStates;
	}

}
