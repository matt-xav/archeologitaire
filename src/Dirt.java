package src;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Random;

import javax.swing.JFrame;

public class Dirt extends JFrame implements MouseListener{
	private final int WIDTH = 500;
	private final int LENGTH = 500;
	
	private final Color BROWN = new Color(185, 122, 87);
	
	private int[][] dirtLocations;
	
	private Random myRand;

	public void main(String[] args) {
		Dirt myDirt = new Dirt();
		myDirt.setVisible(true);
		
	}
	
	public Dirt(){
		setSize(WIDTH, LENGTH);
		
		Container myPane = getContentPane();
		
		 Graphics g = myPane.getGraphics();
		 
		 dirtLocations = new int[WIDTH][LENGTH];// initialize dirt locations
		 for(int i=0; i<WIDTH; i++) {
			 for(int j=0; j<LENGTH; i++) {
				 dirtLocations[i][j] = myRand.nextInt(1);//randomly pick locations for dirt to be drawn
			 }
		 }
		 displayDirt(g);
	}
	
	public void displayDirt(Graphics g){
		for(int i = 0; i<WIDTH; i++) {
			for(int j=0; j<WIDTH; i++) {
				if(dirtLocations[i][j]==1) {
					g.setColor(BROWN);
					g.drawRect(i, j, 1, 1);// draws a dirt particle
				}
			}
		}
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
