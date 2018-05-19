package fireworks;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import utils.Vector2f;

public class Particle {	
	private Vector2f pos, vel, acc;
	private Color color;
	private boolean destroyed;
	private int size;
	private final static float DELTA_TIME = 0.65f; // See comment in class Firework
	
	public Particle(Vector2f position, Color color) {
		Random rnd = new Random();
		
		destroyed = false;
		size = rnd.nextInt(8) + 4;
		this.color = color;

		pos = new Vector2f(position);
		
		// Initial impulse in random direction
		int signX = rnd.nextInt(2);
		if (signX == 0) signX = -1;
		int signY = rnd.nextInt(2);
		if (signY == 0) signY = -1;
		vel = new Vector2f(signX * (rnd.nextInt(6) + 4), signY * (rnd.nextInt(6) + 4));
		acc = new Vector2f(0f, 0.2f); // Gravity
	}
	
	boolean isDestroyed() {
		return destroyed;
	}
	
	public void tick() {
		vel.incX(acc.getX() * DELTA_TIME);
		vel.incY(acc.getY() * DELTA_TIME); 
		
		pos.incX(vel.getX() * DELTA_TIME);       
		pos.incY(vel.getY() * DELTA_TIME);
		
		// When it reaches the floor it is destroyed
		if (pos.getY() > FireworksMain.HEIGHT) {
			destroyed = true;
		}
	}
		
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect((int) pos.getX(), (int) pos.getY(), size, size);
	}
}
