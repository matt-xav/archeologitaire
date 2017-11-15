package src;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JButton;

public class SolitaireGUI
{
	private JFrame frame;
	private static Solitaire panel;
	private static Cursed cursedPanel;

	private JMenuBar menuBar;
	private JMenu mnFile;
	
	private JMenuItem mntmNewGame;
	private JMenuItem mntmQuitGame;
	private JMenuItem mntmUndo;
	private JMenuItem mntmRedo;
	
	private JButton btnUndo;
	private JButton btnRedo;

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
		panel.repaint();
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
		
		cursedPanel = new Cursed(frame);
		cursedPanel.setBounds(0, 0, 1064, 639);
		frame.getContentPane().add(cursedPanel);
				
		panel.repaint();

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
				frame.dispose();
				SolitaireGUI window = new SolitaireGUI();
				window.frame.setVisible(true);
				initialize();
				panel.repaint();
				JOptionPane.showMessageDialog(frame, "New Game has Begun");
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

		mntmUndo = new JMenuItem("Undo");
		mntmUndo.setFont(new Font("Papyrus", Font.PLAIN, 14));
		mnFile.add(mntmUndo);

		mntmRedo = new JMenuItem("Redo");
		mntmRedo.setFont(new Font("Papyrus", Font.PLAIN, 14));
		mnFile.add(mntmRedo);

		Component horizontalStrut = Box.createHorizontalStrut(240);
		menuBar.add(horizontalStrut);
		
		btnUndo = new JButton("Undo");
		btnUndo.setFont(new Font("Papyrus", Font.PLAIN, 10));
		menuBar.add(btnUndo);
		Component horizontalStrut_1 = Box.createHorizontalStrut(20);
		menuBar.add(horizontalStrut_1);
		
				btnRedo = new JButton("Redo");
				btnRedo.setFont(new Font("Papyrus", Font.PLAIN, 10));
				menuBar.add(btnRedo);
	}
}
