package src;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.util.Random;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * Creates and initializing all game objects.
 *
 * @author Jason Miner
 *
 */
public class Solitaire extends JLabel
{
	private static final long serialVersionUID = 1189908838201482535L;

	private Deck deck;
	private Pile deckPile;
	private Card card;

	public CardListener listener = new CardListener(this);

	public Pile selectedPile; // The selected Pile is always drawn last (so it is on top of everything else)

	private Pile[] tableauPiles;
	private Pile[] foundationPiles;

	public static final int HORI_SPAC = 35;
	public static final int TABLEAU_PILE_Y_LOC = 200;
	public static final int FOUNDATION_PILE_Y_LOC = 20;

	public static final int[] TABLEAU_PILE_X_LOCS = { (HORI_SPAC * 1) + (Card.WIDTH * 0),
													  (HORI_SPAC * 2) + (Card.WIDTH * 1),
													  (HORI_SPAC * 3) + (Card.WIDTH * 2),
													  (HORI_SPAC * 4) + (Card.WIDTH * 3),
													  (HORI_SPAC * 5) + (Card.WIDTH * 4),
													  (HORI_SPAC * 6) + (Card.WIDTH * 5),
													  (HORI_SPAC * 7) + (Card.WIDTH * 6) };

	public static final int[] FOUNDATION_PILE_X_LOCS = { TABLEAU_PILE_X_LOCS[3],
													     TABLEAU_PILE_X_LOCS[4],
													     TABLEAU_PILE_X_LOCS[5],
													     TABLEAU_PILE_X_LOCS[6] };

	private int scale = 2; // changes the scale at which the dirt is drawn.
	
	private final Color BROWN = new Color(188, 175, 141);
	private int[][] dirtLocations;
	private final int digRadius = 50;
	private Random myRand;
	private boolean blind;
	private int blindRadius = 100;
	public Point mouseLocation;

	public Solitaire()
	{
		deck = new Deck();
		tableauPiles = new Pile[7];
		foundationPiles = new Pile[4];
		setInitialLayout(deck);
		deckPile = new Pile(deck.getX() + Card.WIDTH + Solitaire.HORI_SPAC, deck.getY(), Pile.DECK_PILE);
		selectedPile = null;
		CardListener listener = new CardListener(this);

		myRand = new Random();
		dirtLocations = new int[1064 / scale][639 / scale];// initialize dirt locations
		for (int i = 0; i < 1064 / scale; i++)
		{
			// System.out.printf("%3d ", i);
			for (int j = 0; j < 639 / scale; j++)
			{
				// System.out.printf("%3d ", j);
				dirtLocations[i][j] = myRand.nextInt(2);// randomly pick locations for dirt to be drawn
			}
			// System.out.print("\n");
		}
		blind = false;
		mouseLocation = new Point(0, 0);

		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		this.setFocusable(true);
	}

	/** Paints the screen on a graphics context */
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		// System.out.println("blind = " + blind);

		boolean foundcurse = false;
		// draw all piles and the remaining cards left in the deck
		for (int i = 0; i < tableauPiles.length; i++)
		{
			tableauPiles[i].display(g);
			if (tableauPiles[i].getCurseStatus())
			{
				System.out.println("Solitaire.paintcomponent tableau pile " + i + " is cursed.");
				foundcurse = true;
			}
		}
		if (foundcurse)
		{
			blind = true;
		}
		else
		{
			blind = false;
		}
		for (int i = 0; i < foundationPiles.length; i++)
		{
			foundationPiles[i].display(g);
		}
		deckPile.display(g);
		deck.display(g);
		if (selectedPile != null)
		{
			selectedPile.display(g);
		}

		// draw dirt
		for (int i = 0; i < 1064 / scale; i++)
		{
			// System.out.printf("%3d ", i);
			for (int j = 0; j < 639 / scale; j++)
			{
				if (dirtLocations[i][j] == 1)
				{
					// System.out.printf("%3d ", j);
					g.setColor(BROWN);
					g.fillRect(i * scale, j * scale, scale, scale);// draws a dirt particle
				}
			}
		}

		int px = (int) (mouseLocation.getX()) / scale;
		int py = (int) (mouseLocation.getY()) / scale;
		int xlim = px + digRadius;
		int ylim = py + digRadius;
		for (int i = px - digRadius; i < xlim; i++)
		{
			for (int j = py - digRadius; j < ylim; j++)
			{
				if (i >= 0 && i < 1064 / scale && j >= 0 && j < 639 / scale)
				{// ensure we don't go out of bounds
					if (Math.sqrt((px - i) * (px - i) + (py - j) * (py - j)) <= digRadius)
					{
						// System.out.println("CardListener.mouseMoved");
						dirtLocations[i][j] = 0;
					}
				}
			}
		}

		if (blind)
		{
			System.out.println("Drawing blindness");
			g.setColor(Color.BLACK);
			g.setColor(Color.BLACK); // draw rectangles
			g.fillRect(0, 0, 1064, (int) mouseLocation.getY() - blindRadius); // top
			g.fillRect(0, (int) mouseLocation.getY() + blindRadius, 1064, 639 - (int) mouseLocation.getY());// bottom
			g.fillRect(0, 0, (int) mouseLocation.getX() - blindRadius, 639);// left
			g.fillRect((int) mouseLocation.getX() + blindRadius, 0, 1064 - (int) mouseLocation.getX(), 639);// right

			// g.setColor(Color.RED);//rounding
			double angle;
			double rangle;
			for (int i = 0; i < 90; i++)
			{
				angle = (double) i;
				rangle = Math.toRadians(angle);
				// System.out.println(rangle);
				g.fillRect(// upper left
						(int) mouseLocation.getX() - blindRadius, (int) mouseLocation.getY() - blindRadius,
						(int) (blindRadius - (blindRadius * Math.sin(rangle))),
						(int) (blindRadius - (blindRadius * Math.cos(rangle))));
				g.fillRect(// upper right
						(int) (mouseLocation.getX() + (blindRadius * Math.sin(rangle)) + 1), // 1 is added to
																								// compensate for
						// the (int) conversion in
						// Math.sin
						(int) mouseLocation.getY() - blindRadius,
						(int) (blindRadius - (blindRadius * Math.sin(rangle))),
						(int) (blindRadius - (blindRadius * Math.cos(rangle))));
				g.fillRect(// lower left
						(int) mouseLocation.getX() - blindRadius,
						(int) (mouseLocation.getY() + (blindRadius * Math.cos(rangle)) + 1),
						(int) (blindRadius - (blindRadius * Math.sin(rangle))),
						(int) (blindRadius - (blindRadius * Math.cos(rangle))));
				g.fillRect(// lower left
						(int) (mouseLocation.getX() + (blindRadius * Math.sin(rangle)) + 1),
						(int) (mouseLocation.getY() + (blindRadius * Math.cos(rangle)) + 1),
						(int) (blindRadius - (blindRadius * Math.sin(rangle))),
						(int) (blindRadius - (blindRadius * Math.cos(rangle))));
			}
		}
		if (deckPile.isGameWon())
		{
			JTextArea ta = new JTextArea( "You have Won!!");
			ta.setFont(new Font("Papyrus", Font.PLAIN, 14));
			ta.setEditable(false);
			JOptionPane.showMessageDialog(this, new JScrollPane(ta),"Archeologitaire", JOptionPane.WARNING_MESSAGE);
		}
		
	}

	/** Sets the location of all cards to their starting points */
	public void setInitialLayout(Deck d)
	{
		int cardNum = 0;
		for (int i = 0; i < tableauPiles.length; i++)
		{
			tableauPiles[i] = new Pile(TABLEAU_PILE_X_LOCS[i], TABLEAU_PILE_Y_LOC, Pile.TABLEAU_PILE);
			for (int j = 0; j <= i; j++)
			{
				tableauPiles[i].addToPile(d.getCardAt(cardNum));
				if (j == i)
				{
					d.getCardAt(cardNum).faceDown = false;
				}
				d.removeCardAt(cardNum);
			}
		}
		for (int i = 0; i < foundationPiles.length; i++)
		{
			foundationPiles[i] = new Pile(FOUNDATION_PILE_X_LOCS[i], FOUNDATION_PILE_Y_LOC, Pile.FOUNDATION_PILE);
		}
		deck.setLocation(HORI_SPAC, FOUNDATION_PILE_Y_LOC);
	}
	
	public Pile[] getTableauPiles()
	{
		return tableauPiles;
	}

	public Pile[] getFoundationPiles()
	{
		return foundationPiles;
	}

	public Pile getDeckPile()
	{
		return deckPile;
	}

	public Deck getDeck()
	{
		return deck;
	}

	public boolean isBlind()
	{
		return blind;
	}

	public void setBlind(boolean blind)
	{
		this.blind = blind;
	}

	public CardListener getListener()
	{
		System.out.println("getListener");
		return listener;
	}

}
