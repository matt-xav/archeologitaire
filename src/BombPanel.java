package src;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BombPanel extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Bomb[] bombarray;
	private int bombsNumber = 3;
	
	public BombPanel(JFrame frame) {
		bombarray = new Bomb[bombsNumber];
		for(int i = 0; i < 3; i++) {
			bombarray[i] = new Bomb();
		}
	}
	
	public static void paintComponent() {
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mousePressed(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//main method for testing purposes
	public static void main(String[] args) {
		JFrame thisFrame = new JFrame();
		thisFrame.setTitle("Cursed");
		thisFrame.setSize(1400, 700); //width, height
		thisFrame.setVisible(true);
		thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Cursed myPanel = new Cursed(thisFrame);
		thisFrame.add(myPanel);
	}
}
