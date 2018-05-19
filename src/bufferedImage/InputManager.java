package bufferedImage;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;


public class InputManager implements KeyListener, MouseListener {
	
	Screen screen;
	
	public InputManager(Screen screen) {
		this.screen = screen;
	}
	
	// Key presses are sent to the main object, which will decides
	// what to do with them.
	public void keyPressed(KeyEvent e) {
		if (e.getKeyCode() == KeyEvent.VK_UP)
			screen.up(true);
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			screen.down(true);
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			screen.left(true);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			screen.right(true);
	}

	public void keyReleased(KeyEvent e) {	     
		if (e.getKeyCode() == KeyEvent.VK_UP)
			screen.up(false);
		if (e.getKeyCode() == KeyEvent.VK_DOWN)
			screen.down(false);
		if (e.getKeyCode() == KeyEvent.VK_LEFT)
			screen.left(false);
		if (e.getKeyCode() == KeyEvent.VK_RIGHT)
			screen.right(false);
	}

	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
