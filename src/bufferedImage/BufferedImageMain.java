package bufferedImage;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

public class BufferedImageMain extends Canvas implements Runnable {          

	private static final long serialVersionUID = 1L;
	public final static int WIDTH = 384;
	public final static int HEIGHT = 384;
	private static boolean running = false;
	private Thread main_thread;
	private Screen screen;
	private InputManager inputs;
	
	public BufferedImageMain() {
		screen = new Screen();
		inputs = new InputManager(screen);
		addKeyListener(inputs);
		addMouseListener(inputs);
	}

	public synchronized void start() {
		if (running) return;
		running = true;
		main_thread = new Thread(this);
		main_thread.start();
	}             

	public synchronized void stop() {
		if (!running) return;
		running = false;
		try {
			main_thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		long last_time = System.nanoTime();
		final double amount_of_ticks = 60; // Ticks per second
		double ns = 1000000000 / amount_of_ticks;
		double delta = 0;

		// Here comes the main loop: tick() and render()
		while(running)
		{
			long now = System.nanoTime();
			delta += (now - last_time) / ns;
			last_time = now;
			if (delta >= 1) {
				tick(); // Update game elements, 60 times per second,
				        // independently of the speed of the
				        // underlying hardware.                                                    
				delta--;
			}
			render(); // Render graphics stuff to the screen.
			          // It will run at the computer max speed
			          // available (different from 60 fps).
		}
		stop();
	}

	public void tick() {
		// This method runs 60 times a second.
		screen.tick();
	}

	public void render() {
		// We'll use more than 1 buffer to avoid flickering
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(2);
			return;
		}
		Graphics g = bs.getDrawGraphics();
		// Clear the screen with white
		g.setColor(new Color(255));
		g.fillRect(0,  0, WIDTH, HEIGHT);
		// Render a new 'Screen'
		screen.render(g);
		
		g.dispose();
		bs.show();
	}
	
	public static void main(String[] args) {
		BufferedImageMain my_app = new BufferedImageMain();
		my_app.setPreferredSize(new Dimension(WIDTH, HEIGHT));
		my_app.setMaximumSize(new Dimension(WIDTH, HEIGHT));
		my_app.setMinimumSize(new Dimension(WIDTH, HEIGHT));

		JFrame frame = new JFrame("BufferedImage");
		frame.setSize(WIDTH, HEIGHT);
		// Important to avoid memory leaks when closing the Application:
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setResizable(false);
		frame.setVisible(true);                
		frame.add(my_app);

		my_app.start();
	}
}
