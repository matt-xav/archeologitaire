package src;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JMenu;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
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
import javax.swing.UIManager;

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
	private Pile pile;

	private JFrame frame;
	private JMenuBar menuBar;
	private JLabel backgroundLabel;
	private JTextArea ta1;
	private JTextArea ta2;

	private JMenu mnFile;
	private JMenu mnMusic;
	private JMenu mnHelp;

	private JMenuItem mntmNewGame;
	private JMenuItem mntmQuitGame;
	private JMenuItem mntmOff;
	private JMenuItem mntmOption1;
	private JMenuItem mntmOption2;
	private JMenuItem mntmRules;
	private JMenuItem mntmSpecRules;

	private ImageIcon icon = new ImageIcon("/solitaredig/assets/cards/Reaper.jpg");

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
		try
		{
			frame.setIconImage(ImageIO.read(new File("../solitaredig/assets/g974.png")));
		}
		catch (IOException e1)
		{
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		frame.setTitle("Archeologitaire");
		frame.setBounds(100, 100, 1079, 700);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		solitaire = new Solitaire();
		solitaire.setBounds(0, 0, 1064, 639);
		frame.getContentPane().add(solitaire);
		solitaire.setLayout(null);

		backgroundLabel = new JLabel("");
		try
		{
			backgroundLabel.setIcon(new ImageIcon(ImageIO.read(new File("../solitaredig/assets/background.jpg"))));
		}
		catch (IOException e1)
		{
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
				// JOptionPane.showMessageDialog(frame, "New Game has Begun");
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

		mnHelp = new JMenu("Help");
		mnHelp.setFont(new Font("Papyrus", Font.PLAIN, 14));
		menuBar.add(mnHelp);

		mntmRules = new JMenuItem("Basic Game Rules");
		mntmRules.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_1, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmRules.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					ta1 = new JTextArea(20, 100);
					ta1.setFont(new Font("Papyrus", Font.PLAIN, 14));
					ta1.read(new FileReader("../solitaredig/assets/BasicRules.txt"), null);
					ta1.setEditable(false);
					UIManager.put("OptionPane.buttonFont", new Font("Papyrus", Font.BOLD, 14));
					JOptionPane.showMessageDialog(solitaire, new JScrollPane(ta1), "Basic Game Rules for Archeologitaire", JOptionPane.PLAIN_MESSAGE);
				}
				catch (IOException ioe)
				{
					ioe.printStackTrace();
				}
			}
		});
		mntmRules.setFont(new Font("Papyrus", Font.PLAIN, 14));
		mnHelp.add(mntmRules);

		mntmSpecRules = new JMenuItem("Special Game Rules");
		mntmSpecRules.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_2, InputEvent.CTRL_MASK | InputEvent.SHIFT_MASK));
		mntmSpecRules.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				try
				{
					ta2 = new JTextArea(20, 50);
					ta2.setFont(new Font("Papyrus", Font.PLAIN, 14));
					ta2.read(new FileReader("../solitaredig/assets/SpecialRules.txt"), null);
					ta2.setEditable(false);
					UIManager.put("OptionPane.buttonFont", new Font("Papyrus", Font.BOLD, 14));
					JOptionPane.showMessageDialog(solitaire, new JScrollPane(ta2), "Special Rules Specific to Archeologitaire", JOptionPane.PLAIN_MESSAGE);
				}
				catch (IOException ioe)
				{
					ioe.printStackTrace();
				}
			}
		});
		mntmSpecRules.setFont(new Font("Papyrus", Font.PLAIN, 14));
		mnHelp.add(mntmSpecRules);

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

/*
 * private JMenuItem mntmUndo;
 * private JMenuItem mntmRedo;
 * 
 * mntmUndo = new JMenuItem("Undo");
 * mntmUndo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Z,
 * InputEvent.CTRL_MASK)); mntmUndo.addActionListener(new ActionListener() {
 * public void actionPerformed(ActionEvent e) {
 * solitaire.getListener().runMyUndo(); System.out.println("Undo"); } });
 * 
 * mntmUndo.setFont(new Font("Papyrus", Font.PLAIN, 14)); mnFile.add(mntmUndo);
 * 
 * mntmRedo = new JMenuItem("Redo");
 * mntmRedo.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_X,
 * InputEvent.CTRL_MASK)); mntmRedo.addActionListener(new ActionListener() {
 * public void actionPerformed(ActionEvent e) {
 * solitaire.getListener().runMyRedo(); System.out.println("Redo"); } });
 * 
 * mntmRedo.setFont(new Font("Papyrus", Font.PLAIN, 14)); mnFile.add(mntmRedo);
 */