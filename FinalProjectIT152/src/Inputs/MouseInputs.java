package Inputs;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import LABANAN.GamePanel;

public class MouseInputs implements MouseListener, MouseMotionListener {
	
	private GamePanel GP;
	public MouseInputs(GamePanel GP) {
		this.GP =GP;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
//		GP.setRectPos(e.getX(), e.getY());
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		System.out.println("Attacked");
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}