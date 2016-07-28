import java.io.File;
import java.io.FileNotFoundException;
import java.util.Iterator;
import java.util.Map;
import java.util.Scanner;
import java.util.Random;
import java.util.Set;

public class parallelGOL extends Thread {
	
	private int TotalCells;
	private int Cell_Per_Processor;
	private int Num_of_Processors;
	private int[][] Matrix;
	private int Start_point;
	private static int Processor_Name = 0;

	public parallelGOL(int NOP, int TC, int CPP, int[][] Matrix, int SP){
		super(""+Processor_Name++);
		this.Num_of_Processors = NOP;
		this.TotalCells= TC;
		this.Cell_Per_Processor = CPP;
		this.Matrix = Matrix;
		this.Start_point = SP;
	}
	
	public parallelGOL() {
		// TODO Auto-generated constructor stub
	}

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

		for (int i = Math.max(0, row - 1); i <= Math.min((row + 1), numOfRows); i++) {
			for (int j = Math.max(0, col - 1); j <= Math.min((col + 1),
					numOfCols); j++) {
				//System.out.println("(" + i + "," + j + ") = " + matrix[i][j]);
				if ((matrix[i][j] == 1) && !((row == i) && (col == j))) {
					neighbors++;
				}
			}
		}

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
		} else if (row == numOfRows && col == 0) {
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

		} else if (row == numOfRows && col == numOfCols) {
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

		} else if (row == 0 && col == numOfCols) {
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
		} else if (row == 0 && col > 0) {
			for (int tmp = col - 1; tmp <= (col + 1); tmp++) {
				if (matrix[numOfRows][tmp] == 1) {
					neighbors++;
				}
			}
		} else if (row > 0 && col == 0) {
			for (int tmp = (row - 1); tmp <= (row + 1); tmp++) {
				if (matrix[tmp][numOfCols] == 1) {
					neighbors++;
				}
			}

		} else if (row == numOfRows && col > 0) {
			for (int tmp = (col - 1); tmp <= (col + 1); tmp++) {
				if (matrix[0][tmp] == 1) {
					neighbors++;
				}
			}

		} else if (row > 0 && col == numOfCols) {
			for (int tmp = (row - 1); tmp <= (row + 1); tmp++) {
				if (matrix[tmp][0] == 1) {
					neighbors++;
				}
			}
		}

		if (neighbors >= 2 && neighbors <= 3) {
			matrix[row][col] = 1;
		} else {
			matrix[row][col] = 0;
		}

	}
	public int[] moveToPosition(int sp){
		int count =0;
		int i = 0;
		int j = 0;
		int[] corrd = new int[2];
		while(i < Matrix.length && (count != sp)){
			while(j < Matrix[i].length && (count != sp)){
				count++;
				j++;
			}
			i++;
			if(j == Matrix[i].length){
				j = 0;
			}
		}
		corrd[0] = i;
		corrd[1] = j;
		
		return corrd;
		
		
	}
	public void run(){
		int[] corrd;
		int i = 0;
		int j = 0;
		int count = 0;
		
		//Move to Start position
		corrd = moveToPosition(Start_point);
		i = corrd[0];
		j = corrd[1];
		//System.out.println("x =" + i + " y ="+ j);
		
		if((Start_point/Num_of_Processors) == (Num_of_Processors - 1) && Num_of_Processors != Matrix.length){
			int temp = Start_point + Cell_Per_Processor;
			temp = TotalCells - temp;
			Cell_Per_Processor = Cell_Per_Processor+temp;
		}
		
		while(i < Matrix.length && Cell_Per_Processor != count){
			while (j < Matrix.length && Cell_Per_Processor != count){
				//System.out.println("(" + i + ","+ j + ")");
				isAlive(Matrix, i, j);
				//System.out.println("----------GRAPH--------------------");
				//printMatrix(Matrix);
				//System.out.println("-----------------------------------");
				count++;
				j++;
			}
			if(j == Matrix[i].length){
				j = 0;
			}
			i++;
		}
		
	}

	public void printMatrix(int[][] matrix) {
		for (int x = 0; x < matrix.length; x++) {
			for (int y = 0; y < matrix[x].length; y++) {
				System.out.print(matrix[x][y] + " ");
			}
			System.out.print("  Processor:" + getName() + "\n");
		}
		System.out.println("");
	}

	public static void main(String[] args) throws InterruptedException {
		int totalCells = 0;
		int sp = 0;
		
		//Gets number of processors
		Runtime rt = Runtime.getRuntime();
		int NUM_OF_PROCESSORS = rt.availableProcessors();
		System.out.println("Number of Processors: " + NUM_OF_PROCESSORS);
		
		//Used to build the matrix
		parallelGOL life = new parallelGOL();
		life.Matrix = life.buildMatrix();
		
		int option = life.checkOptions();
		if (option <= 1 && !(option < 0)) {
			life.Matrix = life.fillMatrix(option, life.Matrix);
		} else {
			System.out.println("Invalid response");
		}
		
		//life.printMatrix(life.Matrix);
		
		long start = System.currentTimeMillis();
		totalCells = life.Matrix.length*life.Matrix.length;
		int CPP = (int)(totalCells/NUM_OF_PROCESSORS);
		//Used to create the threads 
		System.out.println(start);
		for (int i = 0; i < NUM_OF_PROCESSORS; i++) {
			sp = i*CPP;
			parallelGOL newlife = new parallelGOL(NUM_OF_PROCESSORS, totalCells, CPP, life.Matrix, sp);
			newlife.start();
			//newlife.join(); //Basically would turn this into serial code
		}
		 //life.printMatrix(life.Matrix);
		 long stop = System.currentTimeMillis();
		 System.out.println(stop);
		 double elapsed = (stop - start) / 1000.0;
		 System.out.println("Time for completetion::  " + elapsed + " seconds");
	}
	

}
