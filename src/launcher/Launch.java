/**
 * COPYRIGHTS. All rights reserved. THis belongs to Naveen Dennis B from UNC Charlotte
 */
package launcher;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import beans.State;
import core.AStar;
import util.StateValidatorUtil;

public class Launch {

	private static Scanner in;
	private static int rowSize;
	private static int colSize;

	static {
		in = new Scanner(System.in);
	}

	/**
	 * Gets the values of the state and validates them
	 * 
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
					if (StateValidatorUtil.isValidValue(buffer, rowSize, colSize) && !enteredValues.contains(buffer)) {
						stateValues[iterator][innerIterator] = buffer;
						enteredValues.add(buffer);
						break;
					}
					System.out.print("Value entered is invalid!\n" + "Enter a valid value for (" + iterator + ", "
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
			System.out.println("Oops something went wrong!\n"
					+ "Make sure that the blank space is entered as 0 in the grid and that all of them are numbers\n");
		} finally {
			System.out.println("Program ends...");
			in.close();
		}
	}

	private static char getChoice() {
		return in.next().toLowerCase().charAt(0);
	}

	/**
	 * All the input and output operations are invoked from this function. AStar
	 * is invoked from this method after all the initial parameters are set
	 */
	private static void launcher() {
		System.out.println("Continue with the grid size as 3x3 (Y/N): ");
		char choice = Launch.getChoice();
		if (choice == 'y') {
			rowSize = colSize = 3;
		} else {
			System.out.println("Enter the grid size: ");
			String rBuffer = in.next();
			while (!StateValidatorUtil.isInteger(rBuffer)) {
				System.out.println("Enter a valid row grid size:");
				rBuffer = in.next();
			}
			String cBuffer = in.next();
			while (!StateValidatorUtil.isInteger(cBuffer)) {
				System.out.println("Enter a valid column grid size:");
				cBuffer = in.next();
			}
			rowSize = Integer.valueOf(rBuffer);
			colSize = Integer.valueOf(cBuffer);
		}

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
		System.out.println("Number of states in Path: " + result.size());

	}

	/**
	 * It prints the values of the matrix passed to it.
	 * 
	 * @param stateValues
	 *            the inputs that are to be printed.
	 */
	public static void printStateValues(Integer[][] stateValues) {
		for (int outer = 0; outer < rowSize; outer++) {
			for (int inner = 0; inner < colSize; inner++) {
				System.out.print(stateValues[outer][inner] + " ");
			}
			System.out.println();
		}
	}

}
