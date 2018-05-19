package utils;

public class Vector2i {
	int x;
	int y;
	
	public Vector2i(int x, int y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2i() {
		x = 0;
		y = 0;
	}
	
	public Vector2i(Vector2i p) {
		x = p.getX();
		y = p.getY();
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public void incX(float delta) {
		x = (int) (x + delta);
	}
	
	public void incY(float delta) {
		y = (int) (y + delta);
	}
}
