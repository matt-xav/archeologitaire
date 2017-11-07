package src;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JPanel implements MouseListener{
	
	public JFrame myFrame;
	public int WIDTH;
	public int HEIGHT;
	
	private Card myCard;
	
	public Test(JFrame frame){
		super();
		WIDTH = frame.getWidth();
		HEIGHT = frame.getHeight();
		myFrame = frame;
		
		myCard = new Card(1,10,10,219,"d");
		
	}

    public static void main(String[] args){
        JFrame thisFrame = new JFrame();
		thisFrame.setTitle("Test");
		thisFrame.setSize(1400, 700); //width, height
		thisFrame.setVisible(true);
		thisFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		Test myTest = new Test(thisFrame);
		thisFrame.add(myTest);
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
}