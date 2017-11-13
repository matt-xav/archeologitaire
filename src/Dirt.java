/**
 * 
 */

package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dirt extends JPanel implements MouseListener, MouseMotionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFrame myFrame;
	private int WIDTH;
	private int HEIGHT;
	private int xOffset = 0;
	private int yOffset = 0;
	
	//NOT WORKED OUT YET, KEEP AT 1
	private int scale = 2;//the larger this number, the bigger the dirt will be
	
	private final Color BROWN = new Color(185, 122, 87);
	
	private int[][] dirtLocations;
	private final int digRadius = 25;
	
	private Random myRand;
	
	public Dirt(JFrame frame){
		super();
		WIDTH = frame.getWidth();
		HEIGHT = frame.getHeight();
		myFrame = frame;
		
		//methodology borrowed from Steve Harper's dolpin code
		int borderWidth = (WIDTH - frame.getContentPane().getWidth())/2;
		xOffset = borderWidth;
        yOffset = HEIGHT - frame.getContentPane().getHeight(); // assume side border = bottom border; ignore title bar
        System.out.printf("frame.getContentPane.getWidth: %d%nframe.getContentPane().getHeight(); %d%nxOffest: %d%nyOffest: %d%n", 
        		frame.getContentPane().getWidth(), frame.getContentPane().getHeight(), xOffset, yOffset);
		
		myRand = new Random();
		dirtLocations = new int[WIDTH/scale][HEIGHT/scale];// initialize dirt locations
		for(int i=0; i<WIDTH/scale; i++) {
			//System.out.printf("%3d ", i);
			for(int j=0; j<HEIGHT/scale; j++) {
				//System.out.printf("%3d ", j);
				dirtLocations[i][j] = myRand.nextInt(2);//randomly pick locations for dirt to be drawn
			}
			//System.out.print("\n");
		}
		addMouseListener(this);
		addMouseMotionListener(this);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i = 0; i<WIDTH/scale; i++) {
			//System.out.printf("%3d ", i);
			for(int j=0; j<HEIGHT/scale; j++) {
				if(dirtLocations[i][j]==1) {
					//System.out.printf("%3d ", j);
					g.setColor(BROWN);
					g.fillRect(i*scale, j*scale, scale, scale);// draws a dirt particle
				}
			}
			//System.out.print("\n");
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getLocationOnScreen();
		p.setLocation((p.getX() - myFrame.getX()- xOffset)/scale, (p.getY() - myFrame.getY() - yOffset)/scale);
		System.out.println(p.getX() +"\n"+ myFrame.getX() +"\n"+ xOffset +"\n"+ p.getY() +"\n"+ myFrame.getY() +"\n"+ yOffset +"\n\n");
		int px = (int) (p.getX()); int py = (int) (p.getY());
		int xlim = px + digRadius;
		int ylim = py + digRadius;
		for(int i = px - digRadius; i < xlim; i++) {
			for(int j = py - digRadius; j<ylim; j++) {
				if(i >= 0 && i < WIDTH/scale && j >= 0 && j < HEIGHT/scale) {//ensure we don't go out of bounds
					if(Math.sqrt((px-i)*(px-i) + (py-j)*(py-j)) <= digRadius) {
						dirtLocations[i][j] = 0;
						/*if(Math.sqrt((px-i)*(px-i) + (py-j)*(py-j)) == 0) {
							dirtLocations[i][j] = 1;
						}*/
					}
				}
			}
		}
		repaint();
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseClicked(e);
		
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

	@Override
	public void mouseDragged(MouseEvent arg0) {
		mouseClicked(arg0);
	}

	@Override
	public void mouseMoved(MouseEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	
	//main method for testing purposes
	public static void main(String[] args) {
		JFrame thisFrame = new JFrame();
		thisFrame.setTitle("Dirt");
		thisFrame.setSize(500, 500); //height, width
		thisFrame.setVisible(true);
		thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Dirt myPanel = new Dirt(thisFrame);
		thisFrame.add(myPanel);
	}

}
