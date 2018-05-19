package FloodFill;

public class Cell {
	private boolean isObstacle;
	private boolean isEmpty;
	private boolean isFlooded;
	
	public Cell() {
		isObstacle = false;
		isEmpty = true;
		isFlooded = false;
	}

	public boolean isObstacle() {
		return isObstacle;
	}

	public void setObstacle() {
		isObstacle = true;
		isEmpty = false;
		isFlooded = false;
	}

	public boolean isEmpty() {
		return isEmpty;
	}

	public void setEmpty() {
		isEmpty = true;
		isFlooded = false;
		isObstacle = false;
	}

	public boolean isFlooded() {
		return isFlooded;
	}

	public void setFlooded() {
		isFlooded = true;
		isEmpty = false;
		isObstacle = false;
	}
}
