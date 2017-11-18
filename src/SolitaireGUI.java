package src;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.undo.UndoManager;

import java.awt.Component;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.Box;

public class SolitaireGUI
{
	private Clip clip = null;
	private static Solitaire panel;
	
	private JFrame frame;
	private JMenuBar menuBar;
	
	private JMenu mnFile;
	private JMenu mnMusic;
	
	private JMenuItem mntmNewGame;
	private JMenuItem mntmQuitGame;
	private JMenuItem mntmOn;
	private JMenuItem mntmOff;
	
	private JButton btnUndo;
	
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
				}
				catch (Exception e)
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
		music(true);
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
		
		// Cursed cursedPanel = new Cursed(frame);
		// frame.getContentPane().add(cursedPanel);
		
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
		
		btnUndo = new JButton("Undo");
		btnUndo.setHorizontalAlignment(SwingConstants.RIGHT);
		btnUndo.setFont(new Font("Papyrus", Font.PLAIN, 10));
		btnUndo.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent arg0)
			{
			}
		});
		
		mnMusic = new JMenu("Music");
		mnMusic.setFont(new Font("Papyrus", Font.PLAIN, 14));
		menuBar.add(mnMusic);
		
		mntmOn = new JMenuItem("On");
		mntmOn.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				music(true);
			}
		});
		mntmOn.setFont(new Font("Papyrus", Font.PLAIN, 14));
		mnMusic.add(mntmOn);
		
		mntmOff = new JMenuItem("Off");
		mntmOff.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				music(false);
			}
		});
		mntmOff.setFont(new Font("Papyrus", Font.PLAIN, 14));
		mnMusic.add(mntmOff);
		
		Component horizontalStrut = Box.createHorizontalStrut(26);
		menuBar.add(horizontalStrut);
		menuBar.add(btnUndo);
		

		try
		{
			AudioInputStream audio;
			clip = AudioSystem.getClip();
			audio = AudioSystem.getAudioInputStream(new File("overworld.wav"));
			clip.open(audio);
		}
		catch (IOException error)
		{
			System.out.println("File Not Found");
		}
		catch (UnsupportedAudioFileException e)
		{
		}
		catch (LineUnavailableException e2)
		{
		}
		
		panel.repaint();	
	}
	
	public void music(boolean value)
	{
		if (value)
			clip.loop(Clip.LOOP_CONTINUOUSLY);
		else
		{
			clip.stop();
		}
	}
}
