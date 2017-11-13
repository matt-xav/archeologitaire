package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class BombPanel extends JPanel implements MouseListener, MouseMotionListener{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame myFrame;
	private int WIDTH;
	private int HEIGHT;
	private int xOffset = 0;
	private int yOffset = 0;
	
	private Bomb[] bombArray;
	private int bombsNumber = 3;
	private int bombWidth = 30; //width (and length) of bomb, in pixels 
	
	public BombPanel(JFrame frame) {
		super();
		WIDTH = frame.getWidth();
		HEIGHT = frame.getHeight();
		myFrame = frame;
		
		//methodology borrowed from Steve Harper's dolphin code
		int borderWidth = (WIDTH - frame.getContentPane().getWidth())/2;
		xOffset = borderWidth;
        yOffset = HEIGHT - frame.getContentPane().getHeight()-borderWidth; // assume side border = bottom border; ignore title bar
        
        
		bombArray = new Bomb[bombsNumber];
//		bombArray[0] = new Bomb(30, 40, bombWidth);
//		bombArray[1] = new Bomb(50, 70, bombWidth);
//		bombArray[2] = new Bomb(270, 110, bombWidth);
		for(int i = 0; i < 3; i++) {
			//bombArray[i] = new Bomb(i*bombWidth, HEIGHT - bombWidth, bombWidth);
			bombArray[i] = new Bomb(i*bombWidth, 0, bombWidth);
			//System.out.println(i*bombWidth + "\n" +  HEIGHT  + "\n" +  bombWidth);
		}
		
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		//g.setColor(Color.BLACK);
		for(int i = 0; i < bombsNumber; i++) {
			bombArray[i].display(g);
			//System.out.println("test");
		}
		
	}
	
	@Override
	public void mouseClicked(MouseEvent arg0) {
		//System.out.println("You clicked");
		Point p = arg0.getLocationOnScreen();
		p.setLocation(p.getX() - myFrame.getX()- xOffset, p.getY() - myFrame.getY() - yOffset);
		for(int i = 0; i < bombsNumber; i++) {
			//System.out.println(p);
			//System.out.println(bombArray[i]);
			if(bombArray[i].contains(p)) {
				System.out.println("You clicked in Bomb " + i);
				if(!bombArray[i].isSelected()) {
					bombArray[i].select();
					System.out.println();
				}else {
					bombArray[i].deselect();
				}
			}else{
				System.out.println("You didn't click in Bomb " + i + " at " + bombArray[i].xpoints[0] + ", " + bombArray[i].ypoints[0]);
				//System.out.println(p);
				bombArray[i].deselect();
			}
		}
		repaint();
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

	@Override
	public void mouseDragged(MouseEvent arg0) {
		
		
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		Point p = arg0.getLocationOnScreen();
		p.setLocation(p.getX() - myFrame.getX()- xOffset, p.getY() - myFrame.getY() - yOffset);
		for(int i = 0; i < bombsNumber; i++) {
			if(bombArray[i].isSelected()) {
				bombArray[i].absolutemove(p);
			}
		}
		repaint();
		
	}
	
	//main method for testing purposes
	public static void main(String[] args) {
		JFrame thisFrame = new JFrame();
		thisFrame.setTitle("Bombs");
		thisFrame.setSize(500, 500); //width, height
		thisFrame.setVisible(true);
		thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		BombPanel myPanel = new BombPanel(thisFrame);
		thisFrame.add(myPanel);
	}
}
