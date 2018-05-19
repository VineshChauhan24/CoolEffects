package utils;

public class Vector2f {
	float x;
	float y;
	
	public Vector2f(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public Vector2f() {
		x = 0f;
		y = 0f;
	}
	
	public Vector2f(Vector2f p) {
		x = p.getX();
		y = p.getY();
	}

	public float getX() {
		return x;
	}

	public void setX(float x) {
		this.x = x;
	}

	public float getY() {
		return y;
	}

	public void setY(float y) {
		this.y = y;
	}
	
	public void incX(float delta) {
		x = x + delta;
	}
	
	public void incY(float delta) {
		y = y + delta;
	}
}
