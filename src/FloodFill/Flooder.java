package FloodFill;

import java.util.Random;

public class Flooder implements Runnable {
	
	private Cell grid[][];
	private int initRow, initCol;
	private int counter;
	private boolean done;
	
	public Flooder(Cell grid[][]) {
		this.grid = grid;
		
		// Pick a cell (which is not an obstacle) to start flooding
		boolean found = false;		
		Random rnd = new Random();
		
		while (!found) {
			initRow = rnd.nextInt(Grid.ROWS);
			initCol = rnd.nextInt(Grid.COLS);
			if (grid[initRow][initCol].isEmpty()) {
				found = true;
				grid[initRow][initCol].setFlooded();
			}
		}
		counter = 0;
		done = false;
	}

	public void run() {
		// Start flooding with the initial cell
		if (!done) {
			flood(initRow, initCol);
			System.out.println("");
			System.out.println("====> DONE!");
			done = true;
		}
	}
	
	private void flood(int row, int col) {
		counter++;
		System.out.println("Counter: " + counter + " enters");
		// Wait so we can see the progress in the screen
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
		// North
		if ((row - 1) >= 0) {
			if (grid[row - 1][col].isEmpty()) {
				grid[row - 1][col].setFlooded();
				flood(row - 1, col);
			}
		}
		// South
		if ((row + 1) < Grid.ROWS) {
			if (grid[row + 1][col].isEmpty()) {
				grid[row + 1][col].setFlooded();
				flood(row + 1, col);
			}
		}
		// East
		if ((col + 1) < Grid.COLS) {
			if (grid[row][col + 1].isEmpty()) {
				grid[row][col + 1].setFlooded();
				flood(row, col + 1);
			}
		}
		// West
		if ((col - 1) >= 0) {
			if (grid[row][col - 1].isEmpty()) {
				grid[row][col - 1].setFlooded();
				flood(row, col - 1);
			}
		}
		System.out.println("Counter: " + counter + " leaves");
	}
}
