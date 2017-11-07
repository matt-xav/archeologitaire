package src;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Test extends JPanel{
	
	public JFrame myFrame;
	public int WIDTH;
	public int HEIGHT;
	
	public Test(JFrame frame){
		super();
		WIDTH = frame.getWidth();
		HEIGHT = frame.getHeight();
		myFrame = frame;
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
}