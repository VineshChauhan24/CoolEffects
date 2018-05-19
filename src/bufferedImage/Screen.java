package bufferedImage;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;


public class Screen {
	
	private BufferedImage floorTile;
	private BufferedImage screen;
	private BufferedImage lantern;
	private int w, h;
	private int xOffset, yOffset;
	private int pxlColor;
	private int x, y;
	private boolean up, down, left, right; 
	private int a, r, gr, b;
	private float globalLight;
	private final static int TILE_SIZE = 32; // In pixels. Width is same as height.
	
	public Screen() {
		w = BufferedImageMain.WIDTH;
		h = BufferedImageMain.HEIGHT;
		// Instantiate screen and tile:
		screen = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		try {
			floorTile = ImageIO.read(new File("res/floorTile.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		// Compute the light image. This only needs to be done once.
		lantern = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
		int pxlColor = 0;
		for (int xx = 0; xx < w; xx++) {
			for (int yy = 0; yy < h; yy++) {
				int distX = (int) Math.abs(xx-w/2);
				int distY = (int) Math.abs(yy-h/2);
				double distance = Math.sqrt(distX*distX + distY*distY);
				if (distance < 35.0)
					pxlColor = 0x5fffffff;
				else 
					pxlColor = 0x00000000;
				
				lantern.setRGB(xx, yy, pxlColor);
			}
		}
		xOffset = yOffset = pxlColor = 0;
		up = down = left = right = false;
		x = y = 0;
		globalLight = 0.3f;
	}
	
	public void up(boolean val) {
		if (val) 
			up = true;
		else 
			up = false;
	}
	
	public void down(boolean val) {
		if (val) 
			down = true;
		else 
			down = false;
	}
	
	public void right(boolean val) {
		if (val) 
			right = true;
		else 
			right = false;
	}
	
	public void left(boolean val) {
		if (val) 
			left = true;
		else 
			left = false;
	}
	
	private int min(float v1, int i) {
		if (v1 > 0) {
			int val;
			val = (int) Math.round(v1);
			return val;
		}
		else
			return 0;
	}
	
	public void tick() {
		if (up)    yOffset-=2;
		if (down)  yOffset+=2;
		if (right) xOffset+=2;
		if (left)  xOffset-=2;
	}
	
	public void render(Graphics g) {
		for (int yy = 0; yy < h; yy++){
			for (int xx = 0; xx < w; xx++) {		
				// Compute the floor pixels using the tile pixels
				x = (int) Math.abs((xx + xOffset)%TILE_SIZE);
				y = (int) Math.abs((yy + yOffset)%TILE_SIZE);
				pxlColor = floorTile.getRGB(x, y);
				a =  (pxlColor & 0xff000000) >> 24;
				r =  (pxlColor & 0x00ff0000) >> 16;
				gr = (pxlColor & 0x0000ff00) >> 8;
				b =  pxlColor & 0x000000ff;
				r  = min(r * globalLight, 0);
				gr = min(gr * globalLight, 0);
				b  = min(b * globalLight, 0);
				pxlColor = (a << 24) | (r << 16) | (gr << 8) | b;
				screen.setRGB(xx, yy, pxlColor);
			}
		}
		// Images are rendered one after another, exploiting transparency.
		// Order is important: floor, walls, player, lantern, ...
		g.drawImage(screen, 0, 0, null);
		g.drawImage(lantern,  0,  0,  null);
	}

}
