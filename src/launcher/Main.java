package launcher;

import java.util.List;
import java.util.Scanner;

import beans.State;
import core.AStar;
import util.AdjacencyChecker;

public class Main {

	private static Scanner in;
	private static int rowSize;
	private static int colSize;

	static {
		in = new Scanner(System.in);
	}

	public static State getState() {
		System.out.println("Enter the state values:");
		State state = new State();
		Integer[][] stateValues = new Integer[rowSize][colSize];
		for (int iterator = 0; iterator < rowSize; iterator++) {
			for (int innerIterator = 0; innerIterator < colSize; innerIterator++) {
				stateValues[iterator][innerIterator] = in.nextInt();
			}
		}
		state.setStateValues(stateValues);
		return state;
	}

	public static void main(String[] args) {
		prompt();
	}

	public static void prompt() {
		System.out.println("Enter the grid size: ");
		rowSize = in.nextInt();
		colSize = in.nextInt();
		System.out.println(" - - Initial State - - ");
		State initialState = getState();
		System.out.println(" - - Goal State - - ");
		State goalState = getState();
		AStar obj = new AStar(initialState, goalState, rowSize, colSize);
		AdjacencyChecker.setRowSize(rowSize);
		AdjacencyChecker.setColumnSize(colSize);
		List<State> result = obj.search();
		for (State eachState : result) {
			printStateValues(eachState.getStateValues());
		}
	}
	
	public static void printStateValues(Integer[][] stateValues){
		for (int outer = 0; outer < rowSize; outer++) {
			for (int inner = 0; inner < colSize; inner++) {
				System.out.print(stateValues[outer][inner]+ " ");
			}
			System.out.println();
		}
	}

}
