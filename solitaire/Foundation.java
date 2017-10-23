package solitaire;

/**
 * A StackOfCards in which all cards must be of the same suit and each card's
 * value is 1 more than that of the card below it. 
 * The bottom card must be an ace.
 */
public class Foundation extends StackOfCards
{
	/**Instantiates an empty Foundation  */
	public Foundation()
	{
	}
	
	/** Instantiates an empty foundation with given values. */
	public Foundation(int x, int y, int cardWidth)
	{
		super(x, y, cardWidth, 0, 0);
	}
	
	/** Adds a card to the top of the stack. */
	@Override
	public void push(Card card)
	{
		if (isEmpty())
		{
			if (card.getValue() == 1)
			{ 
				super.push(card);
			}
			else
			{
				throw new IllegalArgumentException();// TODO ""
			}
		}
		else
		{
			if (card.getValue() == peek().getValue() + 1 && card.getSuit() == peek().getSuit())
			{
				super.push(card);
			}
			else
			{
				throw new IllegalArgumentException();
			}
		}
	}
}