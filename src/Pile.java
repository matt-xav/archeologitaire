package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

/**
 * Creates a pile of cards in solitaire. It can hold anywhere from 0 to 52
 * cards.
 *
 * @author Jason Miner
 *
 */
public class Pile
{
	private ArrayList<Card> pile;

	public static final int FOUNDATION_PILE = 0;
	public static final int TABLEAU_PILE = 1;
	public static final int TEMP_PILE = 2;
	public static final int DECK_PILE = 3;

	private int pileType;
	private int xLoc;
	private int yLoc;
	private int width;
	private int height;

	public static final int VERT_DISPL = 22;
	public static final int HORI_DISPL = 26;

	private Card[] drawPile;

	private Random rand;
	private int cursedProb = 2;
	private int cursedNum = 0;

	public Pile()
	{
		this(0, 0, TEMP_PILE);
	}

	public Pile(int x, int y, int t)
	{
		rand = new Random();
		pile = new ArrayList<Card>();
		xLoc = x;
		yLoc = y;
		width = Card.WIDTH;
		height = Card.HEIGHT;
		if (t != FOUNDATION_PILE && t != TABLEAU_PILE && t != DECK_PILE)
		{
			pileType = TEMP_PILE;
		}
		else
		{
			pileType = t;
		}
		// the top3 are only used by DECK_PILE's
		drawPile = (pileType == DECK_PILE) ? new Card[3] : null;
	}

	/** Draws the pile of cards */
	public void display(Graphics g)
	{
		if (pile.size() == 0 && pileType != DECK_PILE)
		{
			g.setColor(Color.black);
			g.drawRoundRect(xLoc, yLoc, Card.WIDTH, Card.HEIGHT, 10, 10);
			return;
		}

		/**
		 * removed so cards could paint correctly
		 */
		// if (pileType == DECK_PILE)
		// {
		// for (int i = 0; i < drawPile.length; i++)
		// {
		// if (drawPile[i] != null)
		// {
		// try
		// {
		// System.out.println("draw" + this.getCardAt(i));
		// drawPile[i].display(g);
		// } catch (IOException e)
		// {
		// e.printStackTrace();
		// }
		// }
		// }
		// }
		else
		{
			for (int i = 0; i < pile.size(); i++)
			{
				try
				{
					// System.out.println("Pile: " + this.getCardAt(i));
					pile.get(i).display(g);
				}
				catch (IOException e)
				{
					e.printStackTrace();
				}
			}
		}
	}

	/** Adds a card to the pile and sets its location appropriately */
	public void addToPile(Card c)
	{
		if (c != null)
		{
			pile.add(c);
		}
		if (pileType != FOUNDATION_PILE && pileType != DECK_PILE)
		{
			c.setLocation(xLoc, yLoc + pile.indexOf(c) * VERT_DISPL);
			if (pile.size() > 1)
			{
				height += VERT_DISPL;
			}
		}
		else
			if (pileType == FOUNDATION_PILE)
			{
				c.setLocation(xLoc, yLoc);
			}
			else
				if (pileType == DECK_PILE)
				{
					c.setLocation(xLoc, yLoc);
					updateTop3();
				}
	}

	public void addToPile(Pile p)
	{
		if (this.pileType == TABLEAU_PILE)
		{
			for (int i = 0; i < p.size(); i++)
			{
				this.addToPile(p.getCardAt(i));
			}
		}
	}

	public Card getCardAt(int i)
	{
		return withinBounds(i) ? pile.get(i) : null;
	}

	private boolean withinBounds(int i)
	{
		return (i >= 0 && i < pile.size());
	}

	public Pile pileHasBeenClicked(MouseEvent e)
	{
		if (pileType == DECK_PILE)
		{
			Card c = this.getCardOnTop();
			if (c != null)
			{
				if ((e.getX() >= c.getX() && e.getX() <= c.getX() + Card.WIDTH)
						&& (e.getY() >= c.getY() && e.getY() <= c.getY() + Card.HEIGHT))
				{
					return this.getPileAt(this.size() - 1);
				}
			}
			return null;
		}
		if (e.getX() < this.xLoc || e.getX() > this.xLoc + this.width)
		{
			return null;
		}
		if (this.size() == 0 && (e.getY() >= this.yLoc && e.getY() <= this.yLoc + this.height))
		{
			return this;
		}
		for (int i = 0; i < this.size() - 1; i++)
		{
			Card c = this.getCardAt(i);
			if (e.getY() >= c.getY() && e.getY() <= c.getY() + VERT_DISPL && !c.faceDown)
			{
				return this.getPileAt(i);
			}
		}
		if (this.size() > 0)
		{
			Card c = this.getCardOnTop();
			if (e.getY() >= c.getY() && e.getY() <= c.getBottomY() && !c.faceDown)
			{
				return this.getPileAt(this.size() - 1);
			}
		}
		return null;
	}

	public Pile getPileAt(int i)
	{
		Pile p = new Pile(this.getCardAt(i).getX(), this.getCardAt(i).getY(), TEMP_PILE);
		while (i < this.size())
		{
			p.addToPile(this.getCardAt(i));
			this.removeCardAt(i);
		}
		return p;
	}

	public void removeCardAt(int i)
	{
		if (withinBounds(i))
		{
			pile.remove(i);
		}
	}

	public void removeCard(Card c)
	{
		if (c != null)
		{
			pile.remove(c);
		}
	}

	public boolean isOnTop(Card c)
	{
		if (withinBounds(pile.indexOf(c)))
		{
			return (pile.indexOf(c) == pile.size() - 1);
		}
		return false;
	}

	public Card getCardOnTop()
	{
		if (pile.size() > 0)
		{
			return pile.get(pile.size() - 1);
		}
		return null;
	}

	public Card getCardOnBottom()
	{
		if (pile.size() > 0)
		{
			return pile.get(0);
		}
		return null;
	}

	public boolean droppedOnPile(Card c)
	{
		// this checks to see if any of the cards corners is on a pile
		return (((c.getX() >= xLoc && c.getX() <= xLoc + width) && (c.getY() >= yLoc && c.getY() <= yLoc + height))
				|| ((c.getRightX() >= xLoc && c.getRightX() <= xLoc + width)
						&& (c.getY() >= yLoc && c.getY() <= yLoc + height))
				|| ((c.getX() >= xLoc && c.getX() <= xLoc + width)
						&& (c.getBottomY() >= yLoc && c.getBottomY() <= yLoc + height))
				|| ((c.getRightX() >= xLoc && c.getRightX() <= xLoc + width)
						&& (c.getBottomY() >= yLoc && c.getBottomY() <= yLoc + height)));
	}

	public boolean droppedOnPile(Pile p)
	{
		if (p.size() > 0)
		{
			return droppedOnPile(p.getCardAt(0));
		}
		return false;
	}

	public int size()
	{
		return pile.size();
	}

	public int getType()
	{
		return isValidPile(pileType) ? pileType : TEMP_PILE;
	}

	public void setType(int t)
	{
		pileType = isValidPile(t) ? t : TEMP_PILE;
	}

	public boolean isValidPile(int t)
	{
		return (t == TABLEAU_PILE || t == FOUNDATION_PILE || t == TEMP_PILE || t == DECK_PILE);
	}

	public int getX()
	{
		return xLoc;
	}

	public int getY()
	{
		return yLoc;
	}

	public void setLocation(int x, int y)
	{
		if (pileType == TEMP_PILE)
		{
			xLoc = x;
			yLoc = y;
			// move every card in the pile
			for (int i = 0; i < this.size(); i++)
			{
				Card c = this.getCardAt(i);
				c.setLocation(x, y + (i * VERT_DISPL));
			}
		}

	}

	public boolean isEmpty()
	{
		return this.size() == 0;
	}

	public void turnTopCardUp()
	{
		if (this.size() > 0)
		{
			this.getCardOnTop().faceDown = false;
			cursedNum = rand.nextInt(cursedProb);
			System.out.println(cursedNum);
			if (cursedNum == cursedProb - 1)
			{
				this.getCardOnTop().setCursed(true);
			}
		}
	}

	public void turnAllCardsUp()
	{
		for (int i = 0; i < size(); i++)
		{
			pile.get(i).faceDown = false;
		}
	}

	public void updateTop3()
	{
		if (pileType == DECK_PILE)
		{
			// first clear the top 3
			for (int i = 0; i < drawPile.length; i++)
			{
				drawPile[i] = null;
			}

			if (this.size() >= 3)
			{
				for (int i = this.size() - 3, j = 0; i < this.size(); i++, j++)
				{
					drawPile[j] = this.getCardAt(i);
					drawPile[j].setLocation(xLoc + (j * HORI_DISPL), yLoc);
				}
			}
			else
			{
				for (int i = 0; i < this.size(); i++)
				{
					drawPile[i] = this.getCardAt(i);
					drawPile[i].setLocation(xLoc + (i * HORI_DISPL), yLoc);
				}
			}
		}
	}

	public String toString()
	{
		return String.format("Pile#: %s", TABLEAU_PILE);
	}

}
