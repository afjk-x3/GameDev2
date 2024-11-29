package LABANAN;

import java.awt.Dimension;
import java.awt.Graphics;
import javax.swing.JPanel;
import Inputs.KeyboardInputs;
import Inputs.MouseInputs;


public class GamePanel extends JPanel {
	
	private MouseInputs MI;
	private Game game;
	public GamePanel(Game game) {
		
		MI = new MouseInputs(this);
		this.game = game;
		
		setPanelSize();
		addKeyListener(new KeyboardInputs(this));
		addMouseListener(MI);
		addMouseMotionListener(MI);
			
	}
	

	private void setPanelSize() {
		Dimension size = new Dimension(1920,1080);
		setMinimumSize(size);
		setPreferredSize(size);
		setMaximumSize(size);
	}
	
	public void updateGame() {
		
	}

	public void paintComponent(Graphics g) {
		super.paintComponent(g);
	  game.render(g);
	}
	
	public Game getGame() {
		return game;
	}
	
		

		
	}

	/*public void setRectPos(int x, int y) {
		this.xDelta = x;
		this.yDelta = y;
		
	}*/

