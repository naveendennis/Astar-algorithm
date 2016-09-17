package launcher;

import java.util.List;
import java.util.Scanner;

import beans.State;
import core.AStar;
import util.StateValidatorUtil;

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
		try {
			launcher();
		} catch (Exception e) {
			System.out.println("I am knapping! Do not disturb! :P");
		}
	}

	public static void launcher() {
		System.out.println("Enter the grid size: ");
		rowSize = in.nextInt();
		colSize = in.nextInt();
		System.out.println(" - - Initial State - - ");
		State initialState = getState();
		System.out.println(" - - Goal State - - ");
		State goalState = getState();
		AStar obj = new AStar(initialState, goalState, rowSize, colSize);
		StateValidatorUtil.setRowSize(rowSize);
		StateValidatorUtil.setColumnSize(colSize);
		List<State> result = obj.search();
		for (State eachState : result) {
			printStateValues(eachState.getStateValues());
			System.out.println();
		}
		System.out.println("Closed List: " + obj.getClosedList().size());
		System.out.println("Open List: " + obj.getOpenList().size());
		System.out.println("Number of steps: "+result.size());
	}

	public static void printStateValues(Integer[][] stateValues) {
		for (int outer = 0; outer < rowSize; outer++) {
			for (int inner = 0; inner < colSize; inner++) {
				System.out.print(stateValues[outer][inner] + " ");
			}
			System.out.println();
		}
	}

}
