
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
	        case KeyEvent.VK_A: 
	        	GP.getGame().getPlayer().setLeft(true); 
	        	break;
	        case KeyEvent.VK_S:
	        	GP.getGame().getPlayer().setCrouch(true);
	        	break;
	        case KeyEvent.VK_D: 
	        	GP.getGame().getPlayer().setRight(true); 
	        break;
	        case KeyEvent.VK_SPACE: 
	        	GP.getGame().getPlayer().setJumped(true); 
	        	break;
	    }
	}

	@Override
	public void keyReleased(KeyEvent e) {
	    switch(e.getKeyCode()) {
	        case KeyEvent.VK_A: 
	        	GP.getGame().getPlayer().setLeft(false); 
	        	break;
	        case KeyEvent.VK_S:  
	        	GP.getGame().getPlayer().setCrouch(false);
	        	break;
	        case KeyEvent.VK_D: 
	        	GP.getGame().getPlayer().setRight(false); 
	        	break;
	        case KeyEvent.VK_SPACE: 
	        	GP.getGame().getPlayer().setJumped(false); 
	        	break;
	    }
	}


}