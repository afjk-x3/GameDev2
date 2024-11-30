package LABANAN;

import java.awt.Graphics;

import entities.Player;
import entities.Platform;

public class Game implements Runnable{

	private GameWindow GW;
	private GamePanel GP;
	private Thread gameThread;
	private final int FPS_SET = 120;
	private final int UPS_SET = 200;
	private Platform platform;
	private Player player;
	
	public Game() {
		initClasses();
		
		GP = new GamePanel(this);
		GW = new GameWindow(GP);
		GP.requestFocus();
		startGameLoop();
	}
	
	private void initClasses() {
	    platform = new Platform(700, 650, 500, 10); // Example platform
	    player = new Player(750, 200, platform);
	}

	private void startGameLoop() {
		gameThread = new Thread(this);
		gameThread.start();
	}
	
	public void update() {
	player.update();
	}

	public void render(Graphics g) {
	    player.render(g);
	    platform.render(g); // Render the platform
	}

	@Override
	public void run() {
		
		double timePerFrame = 1000000000.0 / FPS_SET;
		double timePerUpdate = 1000000000.0 / UPS_SET;
		
		long previousTime = System.nanoTime();
		
		int frames = 0;
		int updates = 0;
		long lastCheck = System.currentTimeMillis();
		
		double deltaU = 0;
		double deltaF = 0;
		
		while(true) {
			
			long currentTime = System.nanoTime();
			
			deltaU += (currentTime - previousTime) / timePerUpdate;
			deltaF += (currentTime - previousTime) / timePerFrame;
			previousTime = currentTime;
			
			 while (deltaU >= 1) {
		            update();
		            updates++;
		            deltaU--;
		        }

		        // Render the game (FPS)
		        if (deltaF >= 1) {
		            GP.repaint();
		            frames++;
		            deltaF--;
		        }

		        // Output debug info every second
		        if (System.currentTimeMillis() - lastCheck >= 1000) {
		            lastCheck = System.currentTimeMillis();
		            System.out.println("FPS: " + frames + " | UPS: " + updates);
		            frames = 0;
		            updates = 0;
		        }
		}
		
	}
	public void windowFocusLost() {
		player.resetDirBooleans();
	}
	public Player getPlayer() {
		return player;
	}
}