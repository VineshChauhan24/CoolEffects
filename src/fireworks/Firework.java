package fireworks;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;
import utils.Vector2f;

public class Firework {
	private Vector2f pos;
	private Vector2f vel;
	private Vector2f acc;
	private Color color, pColor;
	private Particle parts[];
	private Random rnd;
	private final static float DELTA_TIME = 0.65f; // It is 1/60 in the real world,
                                                   // but that makes things way too slow
	private final static int NUM_PARTICLES = 32;

	public Firework() {
		rnd = new Random();
		reset();
	}
	
	private void reset() {
		pos = new Vector2f(rnd.nextInt(FireworksMain.WIDTH), FireworksMain.HEIGHT);
		int impulse = rnd.nextInt(10) + 8;
		vel = new Vector2f(0f, -impulse); // There is an initial impulse to the top
		                                  // (which in screen coordinates is towards -Y)
		acc = new Vector2f(0f, 0.2f); // Meanwhile, there is a constant acceleration in the 
		                              // opposite direction (gravity)
		
		color = new Color(128, 128, 128, 255);
		
		parts = null;
		// Choose a color for the particles of this firework
		int c = rnd.nextInt(4);
		int alpha = 64 + rnd.nextInt(128);
		pColor = null;
		if (c == 0) pColor = new Color(255, 0, 0, alpha);
		else if (c == 1) pColor = new Color(0, 255, 0, alpha);
		else if (c == 2) pColor = new Color(0, 0, 255, alpha);
		else if (c == 3) pColor = new Color(255, 255, 0, alpha);
	}

	private void update() {
		pos.incX(vel.getX() * DELTA_TIME);       
		pos.incY(vel.getY() * DELTA_TIME); 
		
		vel.incX(acc.getX() * DELTA_TIME); 
		vel.incY(acc.getY() * DELTA_TIME); 
		
		// When it reaches the floor we create a new onw
		if (pos.getY() > FireworksMain.HEIGHT)
			reset();
		
		// Detect the highest point in the trajectory, to make it explode then
		if ((parts == null) && (vel.getY() > 0)) {
			// Create the particles of the explosion
			parts = new Particle[NUM_PARTICLES];
			for (int i = 0; i < NUM_PARTICLES; i++) {								
				parts[i] = new Particle(pos, pColor);
			}
		}
	}
	
	public void tick() {
		update();
		if (parts != null) {
			for (int i = 0; i < NUM_PARTICLES; i++) {
				if (!parts[i].isDestroyed()) 
					parts[i].tick();
			}
		}
	}
	
	public void render(Graphics g) {
		// Render it only when going upwards
		if (vel.getY() < 0) {
			g.setColor(color);
			g.fillRect((int) pos.getX(), (int) pos.getY(), 12, 12);
		}
		
		if (parts != null) {
			for (int i = 0; i < NUM_PARTICLES; i++) {
				if (!parts[i].isDestroyed()) 
					parts[i].render(g);
			}
		}
	}
}
