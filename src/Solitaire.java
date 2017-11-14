package src;

import java.awt.Color;
import java.awt.Graphics;
import javax.swing.JPanel;

/**
 * The panel that Solitaire is painted on. It is responsible for
 * drawing and initializing all game objects.
 */
public class Solitaire extends JPanel
{
	private static final long serialVersionUID = 1189908838201482535L;
	
	private Deck deck;
	private Pile deckPile;
	
	private Pile[] tableauPiles; 
	private Pile[] foundationPiles;

	public static final int HORI_SPAC = 35;

	public static final int TABLEAU_PILE_Y_LOC = 200;
	
	public static final int[] TABLEAU_PILE_X_LOCS =
		{ 		(HORI_SPAC * 1) + (Card.WIDTH * 0), (HORI_SPAC * 2) + (Card.WIDTH * 1), (HORI_SPAC * 3) + (Card.WIDTH * 2), 
				(HORI_SPAC * 4) + (Card.WIDTH * 3), (HORI_SPAC * 5) + (Card.WIDTH * 4), (HORI_SPAC * 6) + (Card.WIDTH * 5),
				(HORI_SPAC * 7) + (Card.WIDTH * 6) 
		};
	
	public static final int FOUNDATION_PILE_Y_LOC = 20;
	public static final int[] FOUNDATION_PILE_X_LOCS = { TABLEAU_PILE_X_LOCS[3], TABLEAU_PILE_X_LOCS[4], TABLEAU_PILE_X_LOCS[5], TABLEAU_PILE_X_LOCS[6] };

	/** The selected Pile is always drawn last (so it is on top of everything else) */
	public Pile selectedPile;
	
	/** Constructor for a game panel. Adds mouse listeners and initializes deck and piles */
	public Solitaire()
	{
		setBackground(Color.lightGray);
		deck = new Deck();
		tableauPiles = new Pile[7];
		foundationPiles = new Pile[4];
		setInitialLayout(deck);
		deckPile = new Pile(deck.getX() + Card.WIDTH + Solitaire.HORI_SPAC, deck.getY(), Pile.DECK_PILE);
		selectedPile = null;
		CardListener listener = new CardListener(this);
		this.addMouseListener(listener);
		this.addMouseMotionListener(listener);
		this.setFocusable(true);
	}
	
	/** Paints the screen on a graphics context */
	protected void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		// draw all piles and the remaining cards left in the deck
		for (int i = 0; i < tableauPiles.length; i++)
		{
			tableauPiles[i].display(g);
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
	}
	
	/** Return the deck of cards */
	public Deck getDeck()
	{
		return deck;
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
	
	/** @return the deck pile */
	public Pile getDeckPile()
	{
		return deckPile;
	}	
}
