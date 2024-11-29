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
	        case KeyEvent.VK_W: GP.getGame().getPlayer().setUp(true); break;
	        case KeyEvent.VK_A: GP.getGame().getPlayer().setLeft(true); break;
	        case KeyEvent.VK_S: GP.getGame().getPlayer().setDown(true); break;
	        case KeyEvent.VK_D: GP.getGame().getPlayer().setRight(true); break;
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
	    switch(e.getKeyCode()) {
	        case KeyEvent.VK_W: GP.getGame().getPlayer().setUp(false); break;
	        case KeyEvent.VK_A: GP.getGame().getPlayer().setLeft(false); break;
	        case KeyEvent.VK_S: GP.getGame().getPlayer().setDown(false); break;
	        case KeyEvent.VK_D: GP.getGame().getPlayer().setRight(false); break;
	    }
	}


}