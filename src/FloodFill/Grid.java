package FloodFill;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import static FloodFill.FloodFillMain.WIDTH;
import static FloodFill.FloodFillMain.HEIGHT;

public class Grid {
	private final static int SZ = 20;
	public final static int COLS = (WIDTH / SZ);
	public final static int ROWS = (HEIGHT / SZ);
	private Cell grid[][];
	private Thread flooder;
	
	public Grid() {
		reset();
		
		// Call the flooder so things can start.
		// It runs in a separate thread so we can pause it to see the progress
		flooder = new Thread(new Flooder(grid));
		flooder.start();
	}
	
	public void reset() {
		Random rnd = new Random();
		
		grid = new Cell[ROWS][COLS];
		// Init grid with obstacles (=black cells)
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				grid[row][col] = new Cell();
				if (row == col)
					grid[row][col].setObstacle();
				else {
				//else if (row > col) {
					if (rnd.nextInt(5) == 0)
						grid[row][col].setObstacle();
				}
			}
		}
	}
	
	public void render(Graphics g) {
		for (int row = 0; row < ROWS; row++) {
			for (int col = 0; col < COLS; col++) {
				// Draw each cell's borders
				g.setColor(Color.BLACK);
				g.drawRect(col * SZ, row * SZ, SZ, SZ);
				// Draw each cell's body
				if (grid[row][col].isObstacle()) {
					g.setColor(Color.BLACK);
				}
				else if (grid[row][col].isEmpty()) {
					g.setColor(Color.WHITE);
				} 
				else {
					g.setColor(Color.YELLOW);
				}
				g.fillRect(col * SZ, row * SZ, SZ - 1, SZ - 1);
			}
		}
	}
}
