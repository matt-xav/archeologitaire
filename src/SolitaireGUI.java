package src;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;
import javax.swing.JMenu;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.io.IOException;
import java.awt.Toolkit;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class SolitaireGUI
{
	private Clip clip1 = null;
	private Clip clip2 = null;
	private static Solitaire solitaire;

	private UndoManager undoManager = new UndoManager();
	private UndoableEdit undoableEdit = new AbstractUndoableEdit();

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
		music1(true);
		undoManager.end();
		solitaire.repaint();
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

		solitaire = new Solitaire();
		solitaire.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				undoManager.addEdit(undoableEdit);
				
			}
		});
		solitaire.setBounds(0, 0, 1064, 639);
		frame.getContentPane().add(solitaire);
		solitaire.setLayout(null);

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
				music1(false);
				music2(false);
				undoManager.end();
				initialize();
				solitaire.repaint();
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
		mntmUndo.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (undoManager.canUndo())
				{
					undoManager.undo();
				}
			}
		});
		mntmUndo.setFont(new Font("Papyrus", Font.PLAIN, 14));
		mnFile.add(mntmUndo);

		mntmRedo = new JMenuItem("Redo");
		mntmRedo.addMouseListener(new MouseAdapter()
		{
			@Override
			public void mouseClicked(MouseEvent e)
			{
				if (undoManager.canRedo())
				{
					undoManager.redo();
				}
			}
		});
		mntmRedo.setFont(new Font("Papyrus", Font.PLAIN, 14));
		mnFile.add(mntmRedo);

		mnMusic = new JMenu("Music");
		mnMusic.setFont(new Font("Papyrus", Font.PLAIN, 14));
		menuBar.add(mnMusic);

		mntmOption1 = new JMenuItem("Option 1");
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
		mntmOff.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent arg0)
			{
				music1(false);
				music2(false);
			}
		});

		mntmOption2 = new JMenuItem("Option 2");
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
			audio1 = AudioSystem.getAudioInputStream(new File("intro.wav"));
			clip1.open(audio1);
			AudioInputStream audio2;
			clip2 = AudioSystem.getClip();
			audio2 = AudioSystem.getAudioInputStream(new File("overworld.wav"));
			clip2.open(audio2);
		} catch (IOException error)
		{
			System.out.println("File Not Found");
		} catch (UnsupportedAudioFileException e)
		{
		} catch (LineUnavailableException e2)
		{
		}

		solitaire.repaint();
	}

	public void music1(boolean value)
	{
		if (value)
		{
			clip1.loop(Clip.LOOP_CONTINUOUSLY);
		} else
		{
			clip1.stop();
		}
	}

	public void music2(boolean value)
	{
		if (value)
		{
			clip2.loop(Clip.LOOP_CONTINUOUSLY);
		} else
		{
			clip2.stop();
		}
	}
}
