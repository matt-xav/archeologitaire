package src;

import java.awt.Point;
import java.awt.event.MouseEvent;
import javax.swing.event.MouseInputAdapter;
import javax.swing.undo.AbstractUndoableEdit;
import javax.swing.undo.UndoManager;
import javax.swing.undo.UndoableEdit;

/**
 * Creates and handles all mouse listeners 
 * 
 * @author Jason Miner
 *
 */
public class CardListener extends MouseInputAdapter
{
	private Solitaire panel;
	private Deck deck;

	private Pile deckPile;
	private Pile origPile;

	private Pile[] tableaPiles;
	private Pile[] foundationPiles;

	private int lastX;
	private int lastY;

	private UndoManager undoManager = new UndoManager();
	private UndoableEdit anEdit = new AbstractUndoableEdit();

	public CardListener(Solitaire panel)
	{
		this.panel = panel;
		deck = panel.getDeck();
		tableaPiles = panel.getTableauPiles();
		foundationPiles = panel.getFoundationPiles();
		deckPile = panel.getDeckPile();
		lastX = 0;
		lastY = 0;
		origPile = null;
	}

	@Override
	public void mouseMoved(MouseEvent e)
	{
		// System.out.println("CardListener.mouseMoved");
		Point p = new Point(e.getX(), e.getY());
		//System.out.println(p);
		p.setLocation(p.getX(), p.getY());
		panel.mouseLocation = p;
		panel.repaint();
	}

	/** Selects a card when it is clicked */
	public void mousePressed(MouseEvent e)
	{
		panel.selectedPile = getPileClicked(e);
		if (panel.selectedPile != null)
		{
			lastX = e.getX();
			lastY = e.getY();
		}
		else
		{ // if no pile was clicked, check if the deck was
			if (deck.hasBeenClicked(e))
			{
				if (deck.size() == 0)
				{
					deck.addToDeck(deckPile);
				}
				else
				{
					for (int i = 0; i < 3; i++)
					{
						Card c = deck.getCardOnTop();
						if (c != null)
						{
							deckPile.addToPile(c);
							deck.removeCardOnTop();
						}
					}
					deckPile.turnAllCardsUp();
				}
			}
		}
		//System.out.println("mouse pressed");

		panel.setVisible(false);
		panel.setVisible(true);
		panel.repaint();
	}

	/** Moves the card as it is dragged by the mouse */
	public void mouseDragged(MouseEvent e)
	{
		if (panel.selectedPile != null)
		{
			int newX = panel.selectedPile.getX() + (e.getX() - lastX);
			int newY = panel.selectedPile.getY() + (e.getY() - lastY);
			panel.selectedPile.setLocation(newX, newY);
			lastX = e.getX();
			lastY = e.getY();
			
			if (panel.selectedPile.getCardOnTop().isCursed())
			{
				panel.selectedPile.getCardOnTop().setCursed(false);
			}
		}
		System.out.println("mouse dragged");

		panel.repaint();
		panel.setVisible(false);
		panel.setVisible(true);
		mouseMoved(e);
	}

	/**
	 * Drops a card on a pile only if it has the right face and color
	 * 
	 * got help from a friend named Stanley Munson
	 */
	public void mouseReleased(MouseEvent e)
	{
		Pile p = panel.selectedPile;
		if (p != null)
		{
			boolean validDrop = false;
			// check to see if the selectedPile has been dropped on a tableau pile
			for (int i = 0; i < tableaPiles.length; i++)
			{
				if (tableaPiles[i].droppedOnPile(p))
				{
					System.out.println("pile moved: " + i + "pile dropped on");
					if (tableaPiles[i].isEmpty())
					{
						if (p.getCardOnBottom().getRank() == 13)
						{
							tableaPiles[i].addToPile(p);
							origPile.turnTopCardUp();
							validDrop = true;
						}
					}
					else
					{
						// if not empty only add if the colors are NOT the same
						if (!p.getCardOnBottom().isRed() == tableaPiles[i].getCardOnTop().isRed())
						{
							// now ensure the faces are descending
							if (p.getCardOnBottom().getRank() + 1 == tableaPiles[i].getCardOnTop().getRank())
							{
								tableaPiles[i].addToPile(p);
								origPile.turnTopCardUp();
								validDrop = true;
								break;
							}
						}
					} // end isEmpty() condition
				}
			}
			// if the drop is still invalid, check if it's been 
			// Dropped on a foundation pile instead
			if (!validDrop)
			{
				for (int i = 0; i < foundationPiles.length; i++)
				{
					if (foundationPiles[i].droppedOnPile(p))
					{
						if (foundationPiles[i].isEmpty())
						{
							if (p.size() == 1)
							{
								if (p.getCardOnBottom().getRank() == 1)
								{
									foundationPiles[i].addToPile(p.getCardOnBottom());
									origPile.turnTopCardUp();
									validDrop = true;
								}
							}
						}
						else
						{
							if (p.size() == 1)
							{
								// only single cards can be added to foundation piles the suits must be the same
								// for cards being added
								if (p.getCardOnBottom().getSuit() == foundationPiles[i].getCardOnTop().getSuit())
								{
									// the faces must be in ascending order
									if (p.getCardOnBottom().getRank() == foundationPiles[i].getCardOnTop().getRank() + 1)
									{
										foundationPiles[i].addToPile(p.getCardOnBottom());
										origPile.turnTopCardUp();
										validDrop = true;
									}
								}
							}
						} // end isEmpty() condition
					}
				}
			}
			if (!validDrop)
			{
				if (p.size() == 1)
				{
					origPile.addToPile(p.getCardOnBottom());
				}
				else
				{
					origPile.addToPile(p);
				}
			}
		}
		panel.selectedPile = null;
		origPile = null;

		System.out.println("mouse released");

		panel.repaint();
		panel.setVisible(false);
		panel.setVisible(true);
	}

	public void mouseClicked(MouseEvent e)
	{
		// System.out.println("mouse clicked");
	}

	/** Returns the card that was clicked or null if no card was clicked */
	private Pile getPileClicked(MouseEvent e)
	{
		Pile clicked = null;
		origPile = null;
		// check the main piles and then the suit piles
		for (int i = 0; i < tableaPiles.length; i++)
		{
			if ((clicked = tableaPiles[i].pileHasBeenClicked(e)) != null)
			{
				origPile = tableaPiles[i];
				return clicked;
			}
		}
		for (int i = 0; i < foundationPiles.length; i++)
		{
			if ((clicked = foundationPiles[i].pileHasBeenClicked(e)) != null)
			{
				origPile = foundationPiles[i];
				return clicked;
			}
		}
		if ((clicked = deckPile.pileHasBeenClicked(e)) != null)
		{
			origPile = deckPile;
		}
		return clicked;
	}

	public boolean runMyUndo()
	{
		if (undoManager.canUndo())
		{
			System.out.println("runMyUndo true");
			undoManager.undoOrRedo();
			return true;
		}
		System.out.println("runMyUndo false,  " + undoManager.toString());
		return false;
	}

	public boolean runMyRedo()
	{
		if (undoManager.canRedo())
		{
			System.out.println("runMyRedo true");
			undoManager.redo();
			return true;
		}
		System.out.println("runMyRedo false");
		return false;
	}

}
