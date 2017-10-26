package src;

import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dirt extends JPanel implements MouseListener{
	private static JFrame myFrame;
	
	private final static int WIDTH = 500;
	private final static int LENGTH = 500;
	
	private final Color BROWN = new Color(185, 122, 87);
	
	private int[][] dirtLocations;
	private final int digRadius = 50;
	
	private Random myRand;

	public static void main(String[] args) {
		Dirt myPanel = new Dirt();
		
		myFrame = new JFrame();
		myFrame.setTitle("Dirt");
		myFrame.setSize(WIDTH, LENGTH);
		myFrame.add(myPanel);
		myFrame.setVisible(true);
		myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public Dirt(){
		super();
		setSize(WIDTH, LENGTH);
		
		myRand = new Random();
		dirtLocations = new int[WIDTH][LENGTH];// initialize dirt locations
		for(int i=0; i<WIDTH; i++) {
			for(int j=0; j<LENGTH; j++) {
				dirtLocations[i][j] = myRand.nextInt(2);//randomly pick locations for dirt to be drawn
			}
		}
		this.addMouseListener(this);
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		for(int i = 0; i<WIDTH; i++) {
			for(int j=0; j<WIDTH; j++) {
				if(dirtLocations[i][j]==1) {
					g.setColor(BROWN);
					g.drawRect(i, j, 0, 0);// draws a dirt particle
				}
			}
		}
	}
	
	
	@Override
	public void mouseClicked(MouseEvent e) {
		Point p = e.getLocationOnScreen();
		p.setLocation(p.getX() - myFrame.getX(), p.getY() - myFrame.getY() - 30);
		int px = (int) (p.getX()); int py = (int) (p.getY());
		int xlim = px + digRadius;
		int ylim = py + digRadius;
		for(int i = px - digRadius; i < xlim; i++) {
			for(int j = py - digRadius; j<ylim; j++) {
				if(i >= 0 && i < WIDTH && j >= 0 && j < LENGTH) {//ensure we don't go out of bounds
					if(Math.sqrt((px-i)*(px-i) + (py-j)*(py-j)) <= digRadius) {
						dirtLocations[i][j] = 0;
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

}
