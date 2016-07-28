import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Random;

public class GOL {

	public int[][] buildMatrix() {

		Scanner scan = new Scanner(System.in);
		System.out.println("Please enter the amount of rows.");
		int rows = scan.nextInt();
		System.out.println("Please enter the amount of columns.");
		int colmns = scan.nextInt();

		int[][] matrix = new int[rows][colmns];

		return matrix;
	}

	public int checkOptions() {
		Scanner scan = new Scanner(System.in);
		System.out
				.println("Would you like to randomly fill matrix or read from file? \n1 = random \n0 = file");
		int response = scan.nextInt();
		return response;
	}

	public int[][] fillMatrix(int option, int[][] matrix) {
		if (option == 1) {
			Random generator = new Random();
			for (int x = 0; x < matrix.length; x++) {
				for (int y = 0; y < matrix[x].length; y++) {
					matrix[x][y] = generator.nextInt(2);
				}
			}

		} else {

			Scanner scan = new Scanner(System.in);
			System.out
					.println("Please enter the filename \n*Note - Make sure your file is in the same project folder*");
			String filename = scan.next();

			File file = new File(filename);
			try {
				Scanner fileScan = new Scanner(file);
				for (int x = 0; x < matrix.length; x++) {
					for (int y = 0; y < matrix[x].length; y++) {
						if (fileScan.hasNext()) {
							String line = fileScan.next();
							String[] temp = line.split("\\s+");
							matrix[x][y] = Integer.parseInt(line);
						}
					}
				}
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block e.printStackTrace();
			}

		}
		return matrix;
	}

	public void isAlive(int[][] matrix, int row, int col) {
		int neighbors = 0;
		int numOfRows = matrix.length - 1;
		int numOfCols = matrix[row].length - 1;

		// Gets all the cells that don't require cells in other parts of the
		// matrix.
		for (int i = Math.max(0, row - 1); i <= Math.min((row + 1), numOfRows); i++) {
			for (int j = Math.max(0, col - 1); j <= Math.min((col + 1),
					numOfCols); j++) {
				// System.out.println("("+i+","+j+") = " + matrix[i][j]);
				if ((matrix[i][j] == 1) && !((row == i) && (col == j))) {
					neighbors++;
				}
			}
		}
		// If the top left corner cell is passed in
		if (row == 0 && col == 0) {
			for (int tmp = 0; tmp <= row + 1; tmp++) {
				if (matrix[numOfRows][tmp] == 1) {
					neighbors++;
				}
			}
			for (int tmp = 0; tmp <= row + 1; tmp++) {
				if (matrix[tmp][numOfCols] == 1) {
					neighbors++;
				}
			}
			if (matrix[numOfRows][numOfCols] == 1) {
				neighbors++;
			}
		} else if (row == numOfRows && col == 0) { // Bottom left corner
			for (int tmp = 0; tmp <= col + 1; tmp++) {
				if (matrix[col][tmp] == 1) {
					neighbors++;
				}
			}
			for (int tmp = row - 1; tmp <= row; tmp++) {
				if (matrix[tmp][numOfCols] == 1) {
					neighbors++;
				}
			}
			if (matrix[col][row] == 1) {
				neighbors++;
			}

		} else if (row == numOfRows && col == numOfCols) { // bottom right
															// corner
			for (int tmp = (row - 1); tmp <= numOfRows; tmp++) {
				if (matrix[row][0] == 1) {
					neighbors++;
				}
				if (matrix[0][row] == 1) {
					neighbors++;
				}
			}
			if (matrix[row - numOfRows][col - numOfCols] == 1) {
				neighbors++;
			}

		} else if (row == 0 && col == numOfCols) { // top right corner
			for (int tmp = col - 1; tmp <= numOfCols; tmp++) {
				if (matrix[col][tmp] == 1) {
					neighbors++;
				}
			}
			for (int tmp = row; tmp <= row + 1; tmp++) {
				if (matrix[tmp][row] == 1) {
					neighbors++;
				}
			}
			if (matrix[col][row] == 1) {
				neighbors++;
			}
		} else if (row == 0 && col > 0) { // the top cells that are not corner
											// cell
			for (int tmp = col - 1; tmp <= (col + 1); tmp++) {
				if (matrix[numOfRows][tmp] == 1) {
					neighbors++;
				}
			}
		} else if (row > 0 && col == 0) { // the left cells that are not corner
											// cells
			for (int tmp = (row - 1); tmp <= (row + 1); tmp++) {
				if (matrix[tmp][numOfCols] == 1) {
					neighbors++;
				}
			}

		} else if (row == numOfRows && col > 0) {// Bottom cells that are not
													// corner cells
			for (int tmp = (col - 1); tmp <= (col + 1); tmp++) {
				if (matrix[0][tmp] == 1) {
					neighbors++;
				}
			}

		} else if (row > 0 && col == numOfCols) {// the right celss that are not
													// corner cells
			for (int tmp = (row - 1); tmp <= (row + 1); tmp++) {
				if (matrix[tmp][0] == 1) {
					neighbors++;
				}
			}
		}

		if (neighbors >= 2 && neighbors <= 3) { // neighbor count checking
			matrix[row][col] = 1;
		} else {
			matrix[row][col] = 0;
		}

	}

	public void printMatrix(int[][] matrix) {
		for (int x = 0; x < matrix.length; x++) {
			for (int y = 0; y < matrix[x].length; y++) {
				System.out.print(matrix[x][y] + " ");
			}
			System.out.print("\n");
		}
		System.out.println("");
	}

	public static void main(String[] args) {
		int matrix[][];
		int totalCells = 0;
		GOL life = new GOL();
		matrix = life.buildMatrix();
		// Check options to determine if random or for file
		int option = life.checkOptions();
		if (option <= 1 && !(option < 0)) {
			matrix = life.fillMatrix(option, matrix);
		} else {
			System.out.println("Invalid response");
		}
		//life.printMatrix(matrix);
		long start = System.currentTimeMillis();
		for (int i = 0; i < matrix.length; i++) {
			for (int j = 0; j < matrix[i].length; j++) {
				life.isAlive(matrix, i, j);
				// System.out.println("----------GRAPH--------------------");
				// life.printMatrix(matrix);
				// System.out.print("(" + i + "," + j + ")\n");
				// System.out.println("-----------------------------------");
			}
		}
		//life.printMatrix(matrix);
		long stop = System.currentTimeMillis();
		double elapsed = (stop - start) / 1000.0;
		System.out.println("Time for completetion:  " + elapsed + " seconds");

		// life.printMatrix(matrix);

	}

}
