package src;

import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Creates a deck of 52 cards.
 * 
 * @author Jason Miner and Matthew Smillie
 *
 */
public class Deck
{
	private ArrayList<Card> deck;

	private int xLoc;
	private int yLoc;

	public Deck()
	{
		deck = new ArrayList<Card>();
		for (int i = 1; i <= 4; i++)
		{
			for (int k = 1; k <= 13; k++)
			{
				String suit = "";
				if (i == 1)
					suit = "h";
				if (i == 2)
					suit = "d";
				if (i == 3)
					suit = "c";
				if (i == 4)
					suit = "s";
				Card temp = new Card(k, suit);
				deck.add(temp);
			}
		}
		shuffleDeck();
	}

	/** Draws each card in the deck */
	public void display(Graphics g)
	{
		for (int i = 0; i < deck.size(); i++)
		{
			try
			{
				this.getCardAt(i).display(g);
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
	}

	/** Shuffles the deck of cards */
	private void shuffleDeck()
	{
		for (int i = 0; i < deck.size(); i++)
		{
			int index = (int) (Math.random() * deck.size());
			swap(i, index);
		}
	}

	/** Swaps two cards in a deck */
	private void swap(int i1, int i2)
	{
		Card temp = this.getCardAt(i1);
		this.setCardAt(i1, this.getCardAt(i2));
		this.setCardAt(i2, temp);
	}

	/** Returns the card at the specified index */
	public Card getCardAt(int index)
	{
		if (withinBounds(index))
		{
			return deck.get(index);
		}
		return null;
	}

	/** Removes the card at the specified index if the index is not out of bounds */
	public void removeCardAt(int index)
	{
		if (withinBounds(index))
		{
			deck.remove(index);
		}
	}

	/** Sets the card at index to c */
	private void setCardAt(int index, Card c)
	{
		if (withinBounds(index))
		{
			deck.set(index, c);
		}
	}

	/** Returns whether i is a valid index in the deck */
	private boolean withinBounds(int i)
	{
		return (i >= 0 && i < deck.size());
	}

	/** Prints the deck for debugging purposes */
	@SuppressWarnings("unused")
	private void printDeck()
	{
		for (int i = 0; i < deck.size(); i++)
		{
			System.out.println(i + ": " + this.getCardAt(i));
		}
	}

	/** Returns the card that has been clicked, if any */
	public boolean hasBeenClicked(MouseEvent e)
	{
		if ((e.getX() >= xLoc && e.getX() <= xLoc + Card.WIDTH) && (e.getY() >= yLoc && e.getY() <= yLoc + Card.HEIGHT))
		{
			return true;
		}
		return false;
	}

	/** Wrapper class for ArrayList.size() */
	public int size()
	{
		return deck.size();
	}

	/** Returns the card at the top of the deck (index size() - 1) */
	public Card getCardOnTop()
	{
		if (size() > 0)
		{
			return this.getCardAt(size() - 1);
		}
		return null;
	}

	/** Removes the card at the top of the deck */
	public void removeCardOnTop()
	{
		if (size() > 0)
		{
			this.removeCardAt(size() - 1);
		}
	}

	/**
	 * Sets the location of the deck to (x, y). Doing so also sets every card in the
	 * deck to this location.
	 */
	public void setLocation(int x, int y)
	{
		xLoc = x;
		yLoc = y;
		for (int i = 0; i < deck.size(); i++)
		{
			deck.get(i).setLocation(x, y);
		}
	}

	/** Returns the x location of the deck */
	public int getX()
	{
		return xLoc;
	}

	/** Returns the y location of the deck */
	public int getY()
	{
		return yLoc;
	}

	/** Turns all cards in the deck face down */
	public void turnAllCardsDown()
	{
		for (int i = 0; i < this.size(); i++)
		{
			deck.get(i).faceDown = true;
		}
	}

	/** Adds a card to the deck */
	private void addToDeck(Card c)
	{
		this.deck.add(c);
		c.setLocation(xLoc, yLoc);
	}

	/**
	 * Adds the pile p to the deck and removes the cards from p only if the deck has
	 * no cards in it
	 */
	public void addToDeck(Pile p)
	{
		if (this.size() == 0)
		{
			while (!p.isEmpty())
			{
				this.addToDeck(p.getCardAt(p.size() - 1));
				p.removeCardAt(p.size() - 1);
			}
			turnAllCardsDown();
		}
	}
}
