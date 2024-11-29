package Inputs;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import LABANAN.GamePanel;
import static utilz.Constant.Directions.*;


public class KeyboardInputs implements KeyListener{
	private GamePanel GP;
	public KeyboardInputs(GamePanel GP) {
		this.GP = GP;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			GP.setMoving(true);
			GP.setDirections(UP);
			break;
		case KeyEvent.VK_A:
			GP.setMoving(true);
			GP.setDirections(LEFT);
			break;
		case KeyEvent.VK_S:
			GP.setMoving(true);		
			GP.setDirections(DOWN);
			break;
		case KeyEvent.VK_D:
			GP.setMoving(true);
			GP.setDirections(RIGHT);
			break;
		}
		
	}
		

	@Override
	public void keyReleased(KeyEvent e) {
		
		switch(e.getKeyCode()) {
		case KeyEvent.VK_W:
			GP.setMoving(false);
			break;
		case KeyEvent.VK_A:
			GP.setMoving(false);
			break;
		case KeyEvent.VK_S:
			GP.setMoving(false);
			break;
		case KeyEvent.VK_D:
			GP.setMoving(false);
			break;
		}
	
	}

}