package solitaire;


import java.awt.Desktop;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;

public class SolitaireGUI extends JPanel implements ActionListener
{
	private Solitaire game; 
	
	private JMenuItem solitaireItem;
	
	/** Instantiates this with Solitaire by default. */
	public SolitaireGUI()
	{
		game = new Solitaire(this);
	}
	
	/** Draws the game. */
	protected void paintComponent(Graphics pane)
	{
		super.paintComponent(pane);
		game.paint(pane);
	}
	
	/** Returns a menu bar to select the form of Solitaire. */
	private JMenuBar makeMenuBar()
	{
		JMenuBar bar = new JMenuBar(); // Holds all of the buttons.
		JMenu newGameMenu = new JMenu("New Game");
		
		solitaireItem = new JMenuItem("Solitaire");
		solitaireItem.addActionListener(this);
		newGameMenu.add(solitaireItem); // Adds the Solitaire item.
		
		bar.add(newGameMenu);
		
		return bar; // The bar has been created.
	}
	
	/** Responds to menu events to set the form of Solitaire. */
	@Override
	public void actionPerformed(ActionEvent e)
	{
		this.removeMouseListener(game);
		this.removeMouseMotionListener(game);
		
		if (e.getSource() == solitaireItem)
		{
			game = new Solitaire(this);
		}
		repaint();
	}
	
	/** Makes a window containing this and the menu bar. */
	public static void main(String[] args)
	{
		SolitaireGUI gamePanel = new SolitaireGUI(); 
		JFrame window = new JFrame(); 
		
		window.setTitle("Solitaire"); 
		window.setLocation(10, 10); // location,
		window.getContentPane().setBackground(gamePanel.getBackground()); 
		window.setJMenuBar(gamePanel.makeMenuBar()); 
		window.add(gamePanel); 
		window.setSize(gamePanel.getPreferredSize());
		window.setVisible(true); 
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}