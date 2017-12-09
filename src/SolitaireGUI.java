package src;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JMenu;
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.imageio.ImageIO;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.KeyStroke;
import java.awt.event.KeyEvent;
import java.awt.event.InputEvent;
import javax.swing.JLabel;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;

/**
 * Creates the GUI interface for the card Game 
 * 
 * @author Jason Miner
 *
 */
public class SolitaireGUI
{
	private Clip clip1 = null;
	private Clip clip2 = null;
	private Solitaire solitaire;
	
	private JFrame frame;
	private JMenuBar menuBar;

	private JMenu mnFile;
	private JMenu mnMusic;

	private JMenuItem mntmNewGame;
	private JMenuItem mntmQuitGame;
	private JMenuItem mntmOff;
	private JMenuItem mntmUndo;
	private JMenuItem mntmRedo;
	private JMenuItem mntmOption1;
	private JMenuItem mntmOption2;

	private JLabel backgroundLabel;

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
		music1(true);
		solitaire.repaint();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	@SuppressWarnings("deprecation")
	private void initialize()
	{
		frame = new JFrame();
		frame.setResizable(false);
		frame.getContentPane().setFont(new Font("Papyrus", Font.PLAIN, 14));
		frame.setFont(new Font("Papyrus", Font.PLAIN, 14));
		try {
			frame.setIconImage(ImageIO.read(new File("../solitaredig/assets/g974.png")));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.setTitle("Archeologitaire");
		frame.setBounds(100, 100, 1079, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		// Dirt dirtPanel = new Dirt(frame);
		// dirtPanel.setBounds(0, 0, 1064, 639);
		// frame.getContentPane().add(dirtPanel);

		// Cursed cursedPanel = new Cursed(frame);
		// cursedPanel.setBounds(0, 0, 1064, 639);
		// frame.getContentPane().add(cursedPanel);

		solitaire = new Solitaire();
		solitaire.setBounds(0, 0, 1064, 639);
		frame.getContentPane().add(solitaire);
		solitaire.setLayout(null);

		backgroundLabel = new JLabel("");
		try {
			backgroundLabel.setIcon(new ImageIcon(ImageIO.read(new File("../solitaredig/assets/background.jpg"))));
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		backgroundLabel.setVerticalAlignment(SwingConstants.TOP);
		backgroundLabel.setHorizontalAlignment(SwingConstants.LEFT);
		backgroundLabel.setBounds(0, 0, 1064, 639);
		frame.getContentPane().add(backgroundLabel);

		menuBar = new JMenuBar();
		menuBar.setFont(new Font("Papyrus", Font.PLAIN, 14));
		frame.setJMenuBar(menuBar);

		mnFile = new JMenu("File");
		mnFile.setFont(new Font("Papyrus", Font.PLAIN, 14));
		menuBar.add(mnFile);

		mntmNewGame = new JMenuItem("New Game");
		mntmNewGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				frame.dispose();
				SolitaireGUI window = new SolitaireGUI();
				window.frame.setVisible(true);
				music1(false);
				music2(false);
				initialize();
				solitaire.repaint();
//				JOptionPane.showMessageDialog(frame, "New Game has Begun");
			}
		});
		mntmNewGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, InputEvent.CTRL_MASK));
		mntmNewGame.setFont(new Font("Papyrus", Font.PLAIN, 14));
		mnFile.add(mntmNewGame);

		mntmQuitGame = new JMenuItem("Quit Game");
		mntmQuitGame.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				System.exit(0);
			}
		});
		mntmQuitGame.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, InputEvent.CTRL_MASK));
		mntmQuitGame.setFont(new Font("Papyrus", Font.PLAIN, 14));
		mnFile.add(mntmQuitGame);

		mntmUndo = new JMenuItem("Undo");
		mntmUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z, InputEvent.CTRL_MASK));
		mntmUndo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				solitaire.getListener().runMyUndo();
				System.out.println("Undo");
			}
		});

		mntmUndo.setFont(new Font("Papyrus", Font.PLAIN, 14));
		mnFile.add(mntmUndo);

		mntmRedo = new JMenuItem("Redo");
		mntmRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X, InputEvent.CTRL_MASK));
		mntmRedo.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				solitaire.getListener().runMyRedo();
				System.out.println("Redo");
			}
		});

		mntmRedo.setFont(new Font("Papyrus", Font.PLAIN, 14));
		mnFile.add(mntmRedo);

		mnMusic = new JMenu("Music");
		mnMusic.setFont(new Font("Papyrus", Font.PLAIN, 14));
		menuBar.add(mnMusic);

		mntmOption1 = new JMenuItem("Option 1");
		mntmOption1.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK));
		mntmOption1.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				music2(false);
				music1(true);
			}
		});
		mntmOption1.setFont(new Font("Papyrus", Font.PLAIN, 14));
		mnMusic.add(mntmOption1);

		mntmOff = new JMenuItem("Off");
		mntmOff.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_3, InputEvent.CTRL_MASK));
		mntmOff.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				music1(false);
				music2(false);
			}
		});

		mntmOption2 = new JMenuItem("Option 2");
		mntmOption2.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_MASK));
		mntmOption2.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				music1(false);
				music2(true);
			}
		});
		mntmOption2.setFont(new Font("Papyrus", Font.PLAIN, 14));
		mnMusic.add(mntmOption2);
		mntmOff.setFont(new Font("Papyrus", Font.PLAIN, 14));
		mnMusic.add(mntmOff);

		try
		{
			AudioInputStream audio1;
			clip1 = AudioSystem.getClip();
			audio1 = AudioSystem.getAudioInputStream(new File("../solitaredig/assets/overworld.wav"));
			clip1.open(audio1);
			AudioInputStream audio2;
			clip2 = AudioSystem.getClip();
			audio2 = AudioSystem.getAudioInputStream(new File("../solitaredig/assets/intro.wav"));
			clip2.open(audio2);
		}
		catch (IOException error)
		{
			System.out.println("File Not Found");
		}
		catch (UnsupportedAudioFileException e)
		{
			System.out.println("Unsupported Audio File");
		}
		catch (LineUnavailableException e2)
		{
			System.out.println("Line Unavailable");
		}
		
		if (solitaire.isGameWon() == true)
		{
			JOptionPane.showMessageDialog(solitaire, "You have Won!!");
		}	
		solitaire.repaint();
	}

	public void music1(boolean value)
	{
		if (value)
		{
			clip1.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else
		{
			clip1.stop();
		}
	}

	public void music2(boolean value)
	{
		if (value)
		{
			clip2.loop(Clip.LOOP_CONTINUOUSLY);
		}
		else
		{
			clip2.stop();
		}
	}
}
