package solitaire;


/**
 * A StackOfCards with operations for the Tableau Stacks
 */
public class Tableau extends StackOfCards
{
	/**
	 * Instantiates an empty stack where all cards will have no size and positioned
	 * at the origin.
	 */
	public Tableau()
	{
	}
	
	/**
	 * Note: the height of the cards will be based on the width of cards and the
	 * dimensions of a standard card.
	 * 
	 * @param x - The x coordinate for the center of the card on the bottom of the
	 *            stack.
	 * @param y - The y coordinate for the center of the card on the bottom of the
	 *            stack.
	 * @param cardWidth - The width of each card in the stack.
	 * @param offsetY - The difference in y coordinates of a card in the stack with that
	 *            of the card below it.
	 */
	public Tableau(int x, int y, int cardWidth, int offsetY)
	{
		super(x, y, cardWidth, 0, offsetY);
	}
	
	/** Pops all cards below and containing the given y coordinate if the tableau
	 *  contains the y coordinate. */
	public Stack<Card> popCardsBelow(int y)
	{
		if (!contains(this.x, y)) // Then y is not in the boundaries of this stack,
		{
			return null; 
		}
			
		// Holds the cards to be returned.
		Stack<Card> temp = new Stack<Card>();
		
		int numOfCards = 1; // One card is added to temp to start with.
		while (!peek().contains(x, y + (numOfCards - 1) * offsetY) && numOfCards <= size)
		{ 
			numOfCards++; // of cards below the given y coordinate
			// System.out.println(peek().contains(x, y));
			// System.out.println(y + " " + peek().getY() + " " +
			// this.shapeOfNextCard().getCenterY());
			// System.out.println(numOfCards + " will be popped");
		}
		
		for (int i = 0; i < numOfCards; i++)
		{// Move numOfCards cards from this
			temp.push(pop()); // stack to temp.
		}
		
		temp.reverse(); // Because cards were added out of order.
		return temp;
	}
	
	/** 
	 * Pops all cards in order in this tableau below or containing the given
	 * y-coordinate if:
	 *  	The tableau contains the y coordinate.
	 * 		The value of all cards below the y coordinate descend
	 * 		No two adjacent cards below the y coordinate have the same color.
	 * 		No card below the y coordinate is hidden.
	 * 	
	 * 		If one of the conditions isn't met, 'null' will be returned and
	 * 		the cards will not be removed.
*/
	public Stack<Card> popSuitableCardsBelow(int y)
	{
		Stack<Card> temp = popCardsBelow(y);
		if (temp == null || !isSuitable(temp))
		{
			// If temp is not suitable, then its cards should not be removed,
			appendStack(temp); // so we append them back to this stack and
			return null;
		}
		else
		{
			return temp; 
		}
	}
	
	/**
	 * Unlike appendStack in Stack.java, this will only append the
	 * stack if all cards in 'stack' are sequentially increasing in value
	 * from the top and alternate in color.
	 */
	public void appendSuitableCards(Stack<Card> stack)
	{
		Card bottom = stack.reverseCopy().pop();
		
		/*
		 * Checks if: 
		 * 			-the given stack alternates in color. 
		 * 			-the given stack is in sequence with regards to value. 
		 * 			-the bottom card of the given stack differs in color with this tableau's top card 
		 * 			-the bottom card's value is one less than the that of the top card. 
		 * 		If any of these conditions isn't met, an exception is thrown.
		 */
		if (!isEmpty() && (this.peek().compareTo(bottom) != 1 || this.peek().colorEquals(bottom)))
		{
			
			String message = "The given stack is not suitable.";
			throw new IllegalArgumentException(message);
		}
		
		appendStack(stack); // Then the given stack can be appended.
	}
	
	/**
	 * Determines whether the given stack is completely visible, alternates in color,
	 * and is in sequence from low to high values.
	 */
	public static boolean isSuitable(Stack<Card> stack)
	{
		return alternatesInColor(stack) && inSequence(stack) && isVisible(stack);
	}
	
	/**
	 * Determines if all cards in the stack are visible and alternate in color. 
	 * (the colors are ignored if any card is hidden).
	 */
	public static boolean alternatesInColor(Stack<Card> stack)
	{
		if (stack.size() < 2)
		{
			return true;
		}
		
		// Temporary stack in which elements will be removed to be checked. Another
		Stack<Card> copy = stack.copy();
		Card toCompare = copy.pop(); // To compare against other elements.
		
		while (!copy.isEmpty())
		{
			Card current = copy.pop(); // To compare against toCompare.
			// If they have the same color or current is hidden
			if (current.colorEquals(toCompare))
			{
				return false; 
			}
			toCompare = current; // update toCompare.
		}
		return true; 
	}
	
	/**
	 * Determines if all cards in the stack are visible and alternate in color. 
	 * (the colors are ignored if any card is hidden).
	 * */
	public static boolean isVisible(Stack<Card> stack)
	{
		Stack<Card> copy = stack.copy();
		while (!copy.isEmpty())
		{
			if (copy.pop().isHidden())
			{
				return false;
			}
		}
		return true; 
	}
	
	/**
	 * Checks if the values of the cards in the given stack is in sequence from low
	 * to high.
	 */
	public static boolean inSequence(Stack<Card> stack)
	{
		if (stack.size() < 2)
		{
			return true;
		}
		
		// Temporary stack in which elements will be removed to be checked. Another
		Stack<Card> copy = stack.copy();
		Card toCompare = copy.pop(); // To compare against other elements.
		
		while (!copy.isEmpty())
		{
			Card current = copy.pop(); // To compare against toCompare.
			// If they aren't sequentially ordered:
			if (current.compareTo(toCompare) != 1)
			{
				return false; 
			}
			toCompare = current; // update toCompare.
		}
		return true; 
	}
}