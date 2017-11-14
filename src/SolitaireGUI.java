package src;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Font;

public class SolitaireGUI
{
	private JFrame frame;
	private static Solitaire panel;
	
	private JMenuBar menuBar;
	private JMenu mnFile;
	private JMenuItem mntmNewGame;
	private JMenuItem mntmQuitGame;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args)
	{
		EventQueue.invokeLater(new Runnable()
		{
			public void run()
			{
				try
				{
					SolitaireGUI window = new SolitaireGUI();
					window.frame.setVisible(true);
				} catch (Exception e)
				{
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public SolitaireGUI()
	{
		initialize();
	}
	
	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize()
	{
		frame = new JFrame();
		frame.getContentPane().setFont(new Font("Papyrus", Font.PLAIN, 14));
		frame.setFont(new Font("Papyrus", Font.PLAIN, 14));
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage("Reaper.jpg"));
		frame.setResizable(false);
		frame.setTitle("Solitaire Dig");
		frame.setBounds(100, 100, 1079, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		panel = new Solitaire();
		panel.setBounds(0, 0, 1064, 639);
		frame.getContentPane().add(panel);
		panel.setLayout(null);
		
		Cursed cursedPanel = new Cursed(frame);
		frame.add(cursedPanel);
		
		menuBar = new JMenuBar();
		menuBar.setFont(new Font("Papyrus", Font.PLAIN, 14));
		frame.setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		mnFile.setFont(new Font("Papyrus", Font.PLAIN, 14));
		menuBar.add(mnFile);

		mntmNewGame = new JMenuItem("New Game");
		mntmNewGame.setFont(new Font("Papyrus", Font.PLAIN, 14));
		mntmNewGame.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseReleased(MouseEvent e)
			{
				initialize();
				frame.dispose();
			}
		});
		mnFile.add(mntmNewGame);

		mntmQuitGame = new JMenuItem("Quit Game");
		mntmQuitGame.setFont(new Font("Papyrus", Font.PLAIN, 14));
		mntmQuitGame.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseReleased(MouseEvent e)
			{
				System.exit(0);
			}
		});
		mnFile.add(mntmQuitGame);

		panel.repaint();
	}
	
	
}
