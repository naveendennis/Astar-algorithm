package launcher;

import java.util.ArrayList;
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

	/**
	 * Gets the values of the state and validates them
	 * @return the state object that contains the values entered
	 */
	public static State createState() {
		System.out.println("Enter the state values:");
		State state = new State();
		Integer[][] stateValues = new Integer[rowSize][colSize];
		int buffer = 0;
		List<Integer> enteredValues = new ArrayList<Integer>();
		for (int iterator = 0; iterator < rowSize; iterator++) {
			for (int innerIterator = 0; innerIterator < colSize; innerIterator++) {

				while (true) {
					buffer = in.nextInt();
					if (StateValidatorUtil.isValidValue(buffer) && !enteredValues.contains(buffer)) {
						stateValues[iterator][innerIterator] = buffer;
						enteredValues.add(buffer);
						break;
					}
					System.out.print("Value entered is invalid!\n" + "Enter a valid value for(" + iterator + ", "
							+ innerIterator + ") :");
				}
			}
		}
		state.setStateValues(stateValues);
		return state;
	}

	public static void main(String[] args) {
		try {
			launcher();
		} catch (Exception e) {
			System.out.println("Make sure that the blank space is entered as 0 and that all of them are numbers\n"
					+ "If that does not work contact my maker...but don't be too hard on him he worked hard on me");
		}
	}

	public static void launcher() {
		System.out.println("Enter the grid size: ");
		rowSize = in.nextInt();
		colSize = in.nextInt();
		System.out.println("\n - - Initial State - - ");
		State initialState = createState();
		System.out.println("\n - - Goal State - - ");
		State goalState = createState();
		AStar obj = new AStar(initialState, goalState, rowSize, colSize);
		StateValidatorUtil.setRowSize(rowSize);
		StateValidatorUtil.setColumnSize(colSize);
		List<State> result = obj.search();
		System.out.println();
		for (State eachState : result) {
			printStateValues(eachState.getStateValues());
			System.out.println();
		}
		System.out.println("\nClosed List: " + obj.getClosedList().size());
		System.out.println("Open List: " + obj.getOpenList().size());
		System.out.println("Number of states in Path: " + result.size());
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
