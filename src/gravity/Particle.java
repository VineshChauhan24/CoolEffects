package gravity;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import utils.Vector2f;

public class Particle {
	private Vector2f pos;
	private Vector2f vel;
	private Vector2f acc;
	private Color color;
	private int size;
	private final static float DELTA_TIME = 0.65f; // It is 1/60 in the real world,
	                                              // but that makes things way too slow

	public Particle() {
		reset();
	}
	
	private void reset() {
		Random rnd = new Random(); 
		
		pos = new Vector2f(rnd.nextInt(GravityMain.WIDTH), GravityMain.HEIGHT);
		int impulse = rnd.nextInt(10) + 8;
		vel = new Vector2f(0f, -impulse); // There is an initial impulse to the top
		                                  // (which in screen coordinates is towards -Y)
		acc = new Vector2f(0f, 0.2f); // Meanwhile, there is a constant acceleration in the 
		                           // opposite direction (gravity)
		
		int col = rnd.nextInt(4);
		int alpha = 255;
		if (col == 0)
			color = new Color(255, 255, 0, alpha);
		else if (col == 1)
			color = new Color(255, 0, 0, alpha);
		else if (col == 2)
			color = new Color(0, 255, 0, alpha);
		else if (col == 3)
			color = new Color(0, 0, 255, alpha);
		
		size = 6 + rnd.nextInt(8);
	}
	
	public void tick() {
		// Each call to tick() means a deltaTime has occurred (in this
		// case, it is called 60 times per second, so deltaTime = 1/60 = 16.66ms.
		
		// Position has changed as: deltaPosition = vel * deltaTime:
		pos.incX(vel.getX() * DELTA_TIME);      
		pos.incY(vel.getY() * DELTA_TIME); 
		
		// Similarly, velocity has changed as: deltaVelocity = acc * deltaTime: 
		vel.incX(acc.getX() * DELTA_TIME); 
		vel.incY(acc.getY() * DELTA_TIME); 
		
		if (pos.getY() > GravityMain.HEIGHT)
			reset();
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillOval((int) pos.getX(), (int) pos.getY(), size, size);
	}
}
