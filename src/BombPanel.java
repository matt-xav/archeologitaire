package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BombPanel extends JPanel implements MouseListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame myFrame;
	private int WIDTH;
	private int HEIGHT;
	private int xOffset = 0;
	private int yOffset = 0;
	
	private Polygon[] bombArray;
	private int bombsNumber = 3;
	
	public BombPanel(JFrame frame) {
		super();
		WIDTH = frame.getWidth();
		HEIGHT = frame.getHeight();
		myFrame = frame;
		
		//methodology borrowed from Steve Harper's dolpin code
		int borderWidth = (WIDTH - frame.getContentPane().getWidth())/2;
		xOffset = borderWidth;
        yOffset = HEIGHT - frame.getContentPane().getHeight()-borderWidth; // assume side border = bottom border; ignore title bar
        
		bombArray = new Bomb[bombsNumber];
		for(int i = 0; i < 3; i++) {
			bombArray[i] = new Bomb();
		}
		
		addMouseListener(this);
	}
	
	public void paintComponent(Graphics g) {
		g.setColor(Color.BLACK);
		for(int i = 0; i < bombsNumber; i++) {
			g.fillOval(bombArray[i].getX(), bombArray[i].getY(), bombArray[i].getWidth(), bombArray[i].getWidth());
		}
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
