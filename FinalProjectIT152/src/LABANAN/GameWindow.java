package LABANAN;

import javax.swing.JFrame;

public class GameWindow{
	
	private JFrame frame;
	
	public GameWindow(GamePanel GP) {
		
		frame = new JFrame("Game Window");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(GP);
		frame.setResizable(false);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setUndecorated(true); 
		frame.pack();
		frame.setVisible(true);
		
	}

}