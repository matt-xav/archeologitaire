package solitaire;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.geom.RoundRectangle2D;

public class StackOfCards extends Stack<Card>
{
	// The coordinates of the center of the bottom card. 
	protected int x, y;
	
	// The width of all cards in the stack. 
	protected int cardWidth;
	
	// The difference in x and y coordinates respectively of a card in the stack
	// with the card below it. The bottom card will have coordinates (x,y).
	protected int offsetX, offsetY;
	
	/** Instantiates an empty StackOfCards */
	public StackOfCards()
	{
	}
	
	/** Instantiates an empty stackOfCards with given values. */
	public StackOfCards(int x, int y, int cardWidth, int offsetX, int offsetY)
	{
		this.x = x;
		this.y = y;
		this.cardWidth = cardWidth;
		this.offsetX = offsetX;
		this.offsetY = offsetY;
	}
	
	/** 
	 * Returns a new StackOfCards object with 52 cards where all cards
	 * are at the origin with no size and are not hidden.
	 */
	public static StackOfCards randomDeck()
	{
		StackOfCards deck = new StackOfCards();
		deck.fillBySuit();
		deck.shuffle(); // then shuffled.
		return deck;
	}
	
	/** Adds card to this stack. */
	public void push(Card card)
	{
		// The location of the card is changed to match the stack.
		card.setLocation(x + offsetX * size, y + offsetY * size);
		card.setSize(cardWidth); // So is the size.
		super.push(card);
	}
	
	/** Adds 52 cards by suit. */
	public void fillBySuit()
	{
		for (Suit suit : Suit.values())
		{
			for (int i = 1; i < 14; i++)
			{
				push(new Card(suit, i, cardWidth, x, y, false));
			}
		}
	}
	
	/** Reverses the stack and sets each of the card's location accordingly. */
	public void reverse()
	{
		super.reverse();
		setLocation(x, y); // The location of each card is updated.
	}
	
	/** Sets the location of all cards in the stack */
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
		// Then the location of all elements are updated.
		int i = 0;
		for (Node<Card> node = head; node != null; node = node.getNext())
		{
			node.getValue().setLocation(x + (size - i - 1) * offsetX, y + (size - i - 1) * offsetY);
			i++;
		}
	}
	
	/** Sets the space between a card in the stack and the card below */
	public void setOffset(int offsetX, int offsetY)
	{
		this.offsetX = offsetX;
		this.offsetY = offsetY;
		setLocation(x, y); // The location is update to match the new offset.
	}
	
	/** Determines whether a given point is within the stack. */
	public boolean contains(int x, int y)
	{
		// Each card is checked to see if it contains the given location.
		for (Node<Card> node = head; node != null; node = node.getNext())
		{
			if (node.getValue().contains(x, y))
				return true;
		}
		return false;
	}
	
	/**
	 * Draws all of the cards in order of the stack, if the stack is empty, 
	 * a card outline will be drawn as a place holder.
	 */
	public void draw(Graphics pane)
	{
		if (isEmpty())
		{
			drawOutlineOfNextCard(pane);
		}
		else
		{
			draw(pane, head);
		}
	}
	
	/** Draws all cards below a given node containing a card. */
	private void draw(Graphics pane, Node<Card> node)
	{
		if (node != null)
		{
			draw(pane, node.getNext());
			node.getValue().draw(pane);
		}
	}
	
	/** Returns the shape of a card  */
	public RoundRectangle2D.Double shapeOfNextCard()
	{
		return new RoundRectangle2D.Double(x - cardWidth / 2 - offsetX * size, y - cardWidth * 3 / 4 + offsetY * size,
				cardWidth, cardWidth * 3 / 2, cardWidth / 10, cardWidth / 10);
	}
	
	/** Draws the shape of next card in light gray. */
	public void drawOutlineOfNextCard(Graphics pane)
	{
		pane.setColor(Color.LIGHT_GRAY);
		((Graphics2D) pane).fill(shapeOfNextCard());
	}
	
	
	/** Returns the x coordinate of where the center bottom card would be. */
	public int getX()
	{
		return x;
	}
	
	/** Returns the y coordinate of where the center bottom card would be. */
	public int getY()
	{
		return y;
	}
	
	
	/***************************************************************************************
	 * 
	 * Borrowed the following code from:
	 * 
	 ****************************************************************************************/
	
	/**Reorders all of the cards randomly. The positions of the cards will be
	 * changed accordingly. */
	public void shuffle()
	{
		// First merge shuffle is performed and the Knuth/Fisher-Yates shuffle is.
		// done. After several tests, these shuffles in this order had the most
		// uniform odds of some permutation of the cards being selected.
		head = knuthShuffle(mergeShuffle(head));
		setLocation(x, y);
	}
	
	/**
	 * Shuffles the deck by randomly by performing the Knuth/Fisher-Yates shuffle.
	 * 
	 * @param head
	 *            The head of the list to be shuffled. For this method, it is
	 *            intended for node to be head.
	 * @return A node that heads a shuffled deck.
	 */
	private Node<Card> knuthShuffle(Node<Card> head)
	{
		if (size < 2) // Then no shuffling needs to be performed.
			return head;
		
		int tempSize = size;// The number of nodes that still need to be shuffled.
		
		// For each node in the list:
		for (Node<Card> node = head; node.getNext() != null; node = node.getNext())
		{
			
			Node<Card> randomNode = node; // A random node.
			
			// The position of the random node relative to node.
			int numOfIterations = (int) (Math.random() * tempSize--);
			
			// Advances the random node to its position.
			for (int i = 0; i < numOfIterations; i++)
			{
				randomNode = randomNode.getNext();
			}
			swap(node, randomNode); // And swap node with randomNode
		}
		return head;
	}
	
	/**
	 * Swaps the data in node1 and node2 between each other.
	 */
	private void swap(Node<Card> node1, Node<Card> node2)
	{
		Card tempCard = node1.getValue();
		node1.setValue(node2.getValue());
		node2.setValue(tempCard);
	}
	
	/**
	 * Shuffles the given list/node by recursively dividing it into two sublists of
	 * equal length
	 * 
	 * @param node
	 * @return
	 */
	private Node<Card> mergeShuffle(Node<Card> node)
	{
		// Base case.
		if (node == null || node.getNext() == null)
		{ // Then there does exists
			return node; // another permutation for the list.
		}
		
		Node<Card> headL = node; // Heads the left list.
		
		Node<Card> slowCounter = node; // Holds the element before the middle element
		Node<Card> fastCounter = node.getNext(); // To iterate through the list.
		
		// The following loop finds the middle node. Slow counter will point to
		// the middle node.
		// We will advance the fastCounter twice and slowCounter once in each
		// iteration. When fastCounter is null, slowCounter will be pointing to
		// the middle of node.
		while (fastCounter != null && fastCounter.getNext() != null)
		{
			slowCounter = slowCounter.getNext();
			fastCounter = fastCounter.getNext().getNext(); // advanced twice.
		}
		
		Node<Card> headR = slowCounter.getNext();// The right sublist.
		slowCounter.setNext(null); // Severs the origin list into two.
		headL = mergeShuffle(headL); // Shuffle the left.
		headR = mergeShuffle(headR); // Shuffle the right
		return randomizedMerge(headL, headR); // And merge them.
	}
	
	/**
	 * Merges the two given lists in random order. For best results (most random
	 * order), the two given lists should have similar sizes.
	 * 
	 * @param left
	 *            One of the lists to be merged.
	 * @param right
	 *            The other list to be merged.
	 * @return The merged list.
	 */
	private Node<Card> randomizedMerge(Node<Card> left, Node<Card> right)
	{
		if (left == null) // Then there is no merging to be done.
			return right;
		if (right == null)
			return left;
		
		Node<Card> randomHead = null; // Heads the merged list.
		
		if (Math.random() <= 0.5)
		{ // Randomly selects the left or right list.
			randomHead = left;
			randomHead.setNext(randomizedMerge(left.getNext(), right));
		}
		else
		{
			randomHead = right;
			randomHead.setNext(randomizedMerge(left, right.getNext()));
		}
		return randomHead;
	}

}