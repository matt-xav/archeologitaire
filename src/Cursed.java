package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Cursed extends JPanel implements MouseMotionListener
{
	private static final long serialVersionUID = 1L;
	private JFrame myFrame;
	private JPanel myPanel;
	private int WIDTH;
	private int HEIGHT;
	private int xOffset = 0;
	private int yOffset = 0;
	private int x;
	private int y;

	private int radius = 25;// radius of mouse "lantern"

	private Point mouseLocation;

	public Cursed(JFrame frame)
	{
		super();
		myFrame = frame;
		WIDTH = frame.getWidth();
		HEIGHT = frame.getHeight();
		x = getX();
		y = getY();

		// methodology borrowed from Steve Harper's dolpin code
		int borderWidth = (WIDTH - frame.getContentPane().getWidth()) / 2;
		xOffset = borderWidth;
		yOffset = HEIGHT - frame.getContentPane().getHeight() - borderWidth; // assume side border = bottom border;
																				// ignore title bar

		mouseLocation = new Point(0, 0);

		addMouseMotionListener(this);
	}
<<<<<<< HEAD
	
	public Cursed(JPanel panel) {
		super();
		myPanel = panel;
		WIDTH = panel.getWidth();
		HEIGHT = panel.getHeight();
		x = getX();
		y = getY();
		
		xOffset = 0;
        yOffset = 0;
		
        mouseLocation = new Point(0, 0);
        
		addMouseMotionListener(this);
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.setColor(Color.BLACK);
		System.out.println("Cursed.paintComponent");
		if(mouseLocation.getX() > x && mouseLocation.getX() < WIDTH && mouseLocation.getY() > y && mouseLocation.getY() < HEIGHT) {
			g.setColor(Color.BLACK); //draw rectangles
			g.fillRect(x, y, WIDTH, (int) mouseLocation.getY() - radius); //top
			g.fillRect(x, (int) mouseLocation.getY() + radius, (int) WIDTH, HEIGHT - (int) mouseLocation.getY());//bottom
			g.fillRect(x, y, (int) mouseLocation.getX() - radius, HEIGHT);//left
			g.fillRect((int) mouseLocation.getX() + radius, y, WIDTH - (int) mouseLocation.getX(), HEIGHT);//right
			
			//g.setColor(Color.RED);//rounding
=======

	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		// g.drawImage(img, 0, 0, observer);
		g.setColor(Color.BLACK);
		if (mouseLocation.getX() > x && mouseLocation.getX() < WIDTH && mouseLocation.getY() > y
				&& mouseLocation.getY() < HEIGHT)
		{
			g.setColor(Color.BLACK); // draw rectangles
			g.fillRect(x, y, WIDTH, (int) mouseLocation.getY() - radius); // top
			g.fillRect(x, (int) mouseLocation.getY() + radius, (int) WIDTH, HEIGHT - (int) mouseLocation.getY());// bottom
			g.fillRect(x, y, (int) mouseLocation.getX() - radius, HEIGHT);// left
			g.fillRect((int) mouseLocation.getX() + radius, y, WIDTH - (int) mouseLocation.getX(), HEIGHT);// right

			// g.setColor(Color.RED);//rounding
>>>>>>> 3386ec7063e7ccfa08b15a64b945b4bf59468e0e
			double angle;
			double rangle;
			for (int i = 0; i < 90; i++)
			{
				angle = (double) i;
				rangle = Math.toRadians(angle);
				// System.out.println(rangle);
				g.fillRect(// upper left
						(int) mouseLocation.getX() - radius, (int) mouseLocation.getY() - radius,
						(int) (radius - (radius * Math.sin(rangle))), (int) (radius - (radius * Math.cos(rangle))));
				g.fillRect(// upper right
						(int) (mouseLocation.getX() + (radius * Math.sin(rangle)) + 1), // 1 is added to compensate for
																						// the (int) conversion in
																						// Math.sin
						(int) mouseLocation.getY() - radius, (int) (radius - (radius * Math.sin(rangle))),
						(int) (radius - (radius * Math.cos(rangle))));
				g.fillRect(// lower left
						(int) mouseLocation.getX() - radius,
						(int) (mouseLocation.getY() + (radius * Math.cos(rangle)) + 1),
						(int) (radius - (radius * Math.sin(rangle))), (int) (radius - (radius * Math.cos(rangle))));
				g.fillRect(// lower left
						(int) (mouseLocation.getX() + (radius * Math.sin(rangle)) + 1),
						(int) (mouseLocation.getY() + (radius * Math.cos(rangle)) + 1),
						(int) (radius - (radius * Math.sin(rangle))), (int) (radius - (radius * Math.cos(rangle))));
			}
		} else
		{
			g.fillRect(x, y, WIDTH, HEIGHT);
			// System.out.println("Something happened");
		}
	}

	@Override
	public void mouseDragged(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseMoved(MouseEvent arg0)
	{
		Point p = arg0.getLocationOnScreen();
		p.setLocation(p.getX() - myFrame.getX() - xOffset, p.getY() - myFrame.getY() - yOffset);
		// int px = (int) (p.getX()); int py = (int) (p.getY());
		mouseLocation.setLocation(p);
		repaint();
	}

	// main method for testing purposes
	public static void main(String[] args)
	{
		JFrame thisFrame = new JFrame();
		thisFrame.setTitle("Cursed");
		thisFrame.setSize(1000, 600); // width, height
		thisFrame.setVisible(true);
		thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Cursed myPanel = new Cursed(thisFrame);
		thisFrame.add(myPanel);
	}
}
