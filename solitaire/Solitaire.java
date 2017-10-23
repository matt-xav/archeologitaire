package solitaire;

import java.awt.Color;
import java.awt.Container;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;

public class Solitaire implements MouseListener, MouseMotionListener
{
	protected Tableau[] tableaux;
	protected Foundation[] foundations;
	
	protected StackOfCards stock;
	protected StackOfCards waste;
	protected StackOfCards inUse;
	protected StackOfCards lastStack;
	
	protected boolean initiallyHidden = true;
	protected boolean initialized;
	
	protected int cardWidth;
	protected int offset;
	protected int yCoord;
	protected int moves;
	protected int deltaX; 
	protected int deltaY;
	
	protected Container container;

	protected Queue<StackOfCards> animationQueue;
	
	/** Instantiates an empty Solitaire Constructor */
	public Solitaire()
	{
	}
	
	/** Instantiates the game with a Container. */
	public Solitaire(Container container)
	{
		this.container = container;
		container.addMouseListener(this); 
		container.addMouseMotionListener(this); // for dragging.
		container.setBackground(new Color(0, 180, 0)); // A green color.
		container.setSize(790, 720);
		container.setPreferredSize(container.getSize());
		
		yCoord = container.getHeight() / 12;
		cardWidth = 60;
		offset = cardWidth / 2;
		
		inUse = new StackOfCards(0, 0, cardWidth, 0, offset * 3 / 2);
		animationQueue = new Queue<StackOfCards>();
		
		init(); // Initializes all of the stacks.
	}
	
	/** Initializes all of the stacks of cards */
	protected void init()
	{
		StackOfCards deck = StackOfCards.randomDeck();
		
		initTableaux(deck, new int[]
		{ 1, 2, 3, 4, 5, 6, 7 });
		initStockAndWaste(deck); 
		initFoundations(4); 
		initialized = true; 
		container.repaint();
	}
	
	/** Initializes the  cards in each tableau stack. */
	protected void initTableaux(StackOfCards source, int[] initialTableauxSizes)
	{
		tableaux = new Tableau[initialTableauxSizes.length];
		
		for (int i = 0; i < tableaux.length; i++)
		{
			tableaux[i] = new Tableau((cardWidth + 10) * (i + 1), yCoord + cardWidth * 2, cardWidth, offset);
			
			for (int j = 0; j < initialTableauxSizes[i]; j++)
			{ 
				tableaux[i].push(source.pop());
				tableaux[i].peek().setHidden(initiallyHidden);
			}
		}
		for (StackOfCards stack : tableaux)
		{ 
			stack.peek().setHidden(false);
		}
	}
	
	/** Initializes the stock and waste. */
	protected void initStockAndWaste(StackOfCards deck)
	{
		stock = new StackOfCards(cardWidth + 10, yCoord, cardWidth, 0, 0);
		stock.appendStack(deck); 
		stock.peek().setHidden(true); 
		
		waste = new StackOfCards(2 * (stock.getX()), yCoord, cardWidth, 0, 0);
	}
	
	/** Initializes the size and location of foundation stacks */
	protected void initFoundations(int numOfFoundations)
	{
		foundations = new Foundation[numOfFoundations];
		for (int i = 0; i < foundations.length; i++)
		{
			foundations[i] = new Foundation(tableaux[tableaux.length - i - 1].getX(), yCoord, cardWidth);
		}
	}
	
	/** Performs the action associated with stock when clicked. */
	protected boolean stockPressedAction(int x, int y)
	{
		if (stock.contains(x, y))
		{
			// If the stock was clicked:
			waste.push(stock.pop()); // Move the top card from stock to waste.
			waste.peek().setHidden(false);
			
			if (!stock.isEmpty())
			{
				stock.peek().setHidden(true);// Hides the new top card of the stack.
			}
			moves++; 
			container.repaint();
			return true; 
		}
		else if (stock.shapeOfNextCard().contains(x, y))
		{
			// else if the mouse clicked the empty stock's area:
			// Turn over all cards from the waste to the stock,
			stock.appendStack(waste.reverseCopy());
			waste.clear(); 
			
			if (!stock.isEmpty())
			{
				stock.peek().setHidden(true); 
				moves++; 
			}
			container.repaint();
			return true;
		}
		return false;
	}
	
	/** Performs the action associated with the waste */
	protected boolean wastePressedAction(int x, int y)
	{
		// If the waste has cards and the mouse clicked the waste
		if (waste.contains(x, y))
		{
			inUse.push(waste.pop());
			lastStack = waste; 
			moves++;
			return true; 
		}
		return false; 
	}
	
	/** Performs the action associated with the tableau */
	protected boolean tableauxPressedAction(int x, int y)
	{
		for (Tableau tableau : tableaux)
		{ // Check each tableau
			if (tableau.contains(x, y))
			{ // and if the mouse clicked a tableau
				
				// The cards to be put inUse.
				Stack<Card> cards = tableau.popCardsBelow(y);
				
				if (!removableFromTableaux(cards))
				{
					// the cards are not removable so we put them back.
					tableau.appendStack(cards);
					return false; 
				}
				
				// The y coordinate of the bottom card that was popped.
				int cardsY = cards.reverseCopy().peek().getY();
				
				deltaX = x - tableau.getX(); // How off center the click was
				deltaY = y - cardsY; 		 // relative to the card.
				
				// Then put all cards below the click in use, if they are suitable.
				inUse.appendStack(cards);
				
				lastStack = tableau; // And the the tableau becomes the last stack.
			}
		}
		return false; 
	}
	
	/** Checks if a card can be removed from tableau */
	protected boolean removableFromTableaux(Stack<Card> cards)
	{
		return cards != null && Tableau.isVisible(cards) && Tableau.inSequence(cards)
				&& Tableau.alternatesInColor(cards);
	}
	
	/** Performs the pressed action methods. */
	public void mousePressed(MouseEvent e)
	{
		if (hasWon())
		{
			container.repaint(); 
			onWin(); // perform the on win action
			return;
		}
		int x = e.getX(), y = e.getY();

		if (inUse.isEmpty() && !stockPressedAction(x, y) && !wastePressedAction(x, y))
		{
			tableauxPressedAction(x, y);
		}
	}
	
	/** Checks if the card inUse can be added to the tableau */
	protected boolean tableauxReleasedAction(int x, int y)
	{
		for (Tableau tableau : tableaux)
		{ 
			if (tableau.contains(x, y) || tableau.shapeOfNextCard().contains(x, y))
			{
				try
				{
					tableau.appendSuitableCards(inUse);
					inUse.clear();
					flipLastStack();
					return true;
				}
				catch (IllegalArgumentException ex)
				{
				}
			}
		}
		return false;
	}
	
	/**Checks if the card inUse can be added to the foundation */
	protected boolean foundationsReleasedAction(int x, int y)
	{
		if (inUse.isEmpty() || inUse.size() != 1)
		{ 
			return false;
		}
		for (Foundation foundation : foundations)
		{
			if (foundation.contains(x, y) || (foundation.isEmpty() && foundation.shapeOfNextCard().contains(x, y)))
			{
				try
				{
					foundation.push(inUse.peek());
					inUse.pop(); 
					flipLastStack();
					return true; 
				}
				catch (IllegalArgumentException ex)
				{ 
					return false; 
				}
			}
		}
		return false;
	}
	
	/**  Calls all of the release action methods. */
	@Override
	public void mouseReleased(MouseEvent e)
	{
		if (inUse.isEmpty())
		{
			return; 
		}
		int x = e.getX(), y = e.getY(); // The mouse's location.
		if (!tableauxReleasedAction(x, y) && !foundationsReleasedAction(x, y))
		{
			// Then no action was performed, so we return the cards to the last stack.
			returnToLastStack(); 
		}
		else
		{
			moves++;
		}
	}
	
	/** Return the cards that are in use to the last stack that was clicked. */
	protected void returnToLastStack()
	{
		new StackOfCardsAnimator(inUse, lastStack, container);
	}
	
	/** Flips the top card of lastStack if it is not empty. */
	protected void flipLastStack()
	{
		if (!lastStack.isEmpty())
		{ 
			lastStack.peek().setHidden(false); 
		}
		container.repaint();
	}
	
	/** Sets the location of the stack inUse to the MouseEvent's location. */
	@Override
	public void mouseDragged(MouseEvent e)
	{
		if (inUse != null)
		{
			inUse.setLocation(e.getX() - deltaX, e.getY() - deltaY);
			container.repaint();
		}
	}
	
	/** Removes empty elements from the animation queue. */
	protected void updateAnimationQueue()
	{
		while (!animationQueue.isEmpty())
		{ 
			if (animationQueue.peek().isEmpty())
			{
				animationQueue.dequeue(); 
			}
			else
			{
				return; 
			}
		}
	}
	
	/** Paints all of the stacks. */
	public void paint(Graphics pane)
	{
		if (initialized)
		{
			for (StackOfCards tableau : tableaux)
			{
				tableau.draw(pane);
			}
			for (StackOfCards foundation : foundations)
			{
				foundation.draw(pane);
			}
			if (stock != null && !stock.isEmpty())
			{
				stock.peek().draw(pane);
			}
			if (waste != null && !waste.isEmpty())
			{
				waste.peek().draw(pane);
			}
			if (inUse != null && !inUse.isEmpty())
			{
				inUse.draw(pane);
			}
			updateAnimationQueue();
			for (StackOfCards stack : animationQueue)
			{
				if (!stack.isEmpty())
				{
					stack.draw(pane);
				}
			}
		}
	}
	
	/** Determines whether the following winning condition has been met:
	 * 			The stock and waste are both empty.
	 * 			All cards in the tableau are not hidden.
	 * 			Only four or fewer of the tableau are not empty.
	 * 			All foundations have at least one card.
	 * 
	 * When these conditions are met, the user has won because all that's left is 
	 * to move cards to the foundation without any transfers among the stock, waste,
	 * and tableau.
	 */
	protected boolean hasWon()
	{
		for (Foundation f : foundations)
		{
			if (f.isEmpty())
			{
				return false; 
			}
		}
		int numOfNonEmptyTableaux = 0; 
		for (Tableau tableau : tableaux)
		{ 
			if (!Tableau.isSuitable(tableau))
			{
				return false; 
			}
			else if (tableau.size() != 0)
			{
				numOfNonEmptyTableaux++;
			}
		}
		return numOfNonEmptyTableaux <= 4 && stock.isEmpty() && waste.isEmpty();
	}
	
	/** Alerts the user that s/he has won and plays the winning animation. */
	protected void onWin()
	{
		new Thread(new Runnable()
		{
			public void run()
			{
				winningAnimation();
			}
		}).start();

		new Thread(new Runnable()
		{
			public void run()
			{
				JOptionPane.showMessageDialog(container, "Congratulations, you won in " + moves + " moves!.");
			}
		}).start();
	}
	
	/** Plays the winning animation. */
	protected void winningAnimation()
	{
		int sizeOfFoundations = 0;
		for (Foundation f : foundations)
		{
			sizeOfFoundations += f.size();
		}
		
		while (sizeOfFoundations < 52)
		{ 
			if (animationQueue.size() > 6)
			{ 
				try
				{
					Thread.sleep(100);
				}
				catch (InterruptedException e)
				{
				}
				continue; 
			}
			for (Foundation foundation : foundations)
			{ 
				Card temp = foundation.peek(); 
				
				for (Tableau tableau : tableaux)
				{
					// If the tableau:
					// 		-is not empty
					// 		-its top card's value is one greater than temp
					// 		-and it has the same suit as temp, then:
					if (!tableau.isEmpty() && temp.compareTo(tableau.peek()) == -1
							&& temp.getSuit() == tableau.peek().getSuit())
					{
						
						// move the top card to the foundation and animate it.
						animateTopCardOf(tableau, foundation);
						sizeOfFoundations++;
						
						break; 
					}
				}
			}
		}
	}
	
	/** Moves the top card of a source stack to the destination and animates it. */
	protected void animateTopCardOf(StackOfCards source, StackOfCards destination)
	{
		StackOfCards temp = new StackOfCards(source.getX(), source.peek().getY(), cardWidth, 0, 0);
		temp.push(source.pop());
		animationQueue.enqueue(temp);
		new StackOfCardsAnimator(temp, destination, container);
	}
	
	public void mouseEntered(MouseEvent e) {}
	public void mouseExited(MouseEvent e)  {}
	public void mouseClicked(MouseEvent e) {}
	public void mouseMoved(MouseEvent e)   {}
}