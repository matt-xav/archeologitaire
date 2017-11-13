package src;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JPanel implements MouseListener
{
	public JFrame myFrame;
	public int WIDTH;
	public int HEIGHT;

	private Card card;

	public Test(JFrame frame)
	{
		super();
		setLayout(null);
		WIDTH = frame.getWidth();
		HEIGHT = frame.getHeight();
		myFrame = frame;

		card = new Card(3, "c", 10, 10);
	}

	public static void main(String[] args)
	{
		JFrame thisFrame = new JFrame();
		thisFrame.setTitle("Test");
		thisFrame.setSize(1400, 700); // width, height
		thisFrame.setVisible(true);
		thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Test myTest = new Test(thisFrame);
		thisFrame.getContentPane().add(myTest);
		thisFrame.repaint();
	}
	
	public void paintComponent(Graphics g)
	{
		try
		{
			card.draw(g);
		} catch (IOException e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

	@Override
	public void mouseClicked(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent arg0)
	{
		// TODO Auto-generated method stub

	}
}