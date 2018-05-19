package randomwalker;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class RandomWalker {
	int x, y;
	Color color;
	
	public RandomWalker() {
		Random rnd = new Random();
		x = rnd.nextInt(RWMain.WIDTH);
		y = rnd.nextInt(RWMain.HEIGHT);
		color = new Color(127, 127, 0, 255);
	}
	
	public void tick() {		
		Random rnd = new Random();
		int val = rnd.nextInt(4);
		if (val == 0) {
			if ((x + 1) < RWMain.WIDTH)
				x += 1;	
		}
		else if (val == 1) {
			if ((x - 1) >= 0)
				x -= 1;
		}
		else if (val == 2) {
			if ((y + 1) < RWMain.HEIGHT)
				y += 1;
		}
		else if (val == 3) {
			if ((y - 1) >= 0)
				y -= 1;
		}
	}
	
	public void render(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, 2, 2);
	}

}
