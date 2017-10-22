package solitaire;


/**
 * A StackOfCards that holds one card. 
 */
public class HoldingCell extends StackOfCards
{
	/** Instantiates an empty HoldingCell */
	public HoldingCell()
	{
	}
	
	/** Instantiates an empty stackOfCards with given values. */
	public HoldingCell(int x, int y, int cardWidth)
	{
		super(x, y, cardWidth, 0, 0);
	}
	
	/** Adds the given card to this stack. The card will then be the only card in the
	 * stack afterwards.
	 */
	public void push(Card card)
	{
		clear();
		super.push(card);
	}
	
	/** Adds a given stack if it contains only 1 element. */
	public void appendStack(Stack<Card> stack)
	{
		if (stack.size() < 2)
		{
			super.appendStack(stack);
		}
		else
		{
			throw new IllegalArgumentException();// TODO
		}
	}
}