
import java.awt.event.MouseListener;


import java.util.Stack;

public class Solitaire
{
	private CardStack myDrawStack;
	private CardStack myDiscardStack;

	private FoundationStack[] myFoundations;
	private TableauStack[] myTableaus;

	public Solitaire()
	{
		myDrawStack = new CardStack();
		myDiscardStack = new CardStack();

	}


	public Card getDrawCard()
	{
		if (myDrawStack.size() == 0)
			return null;
		return myDrawStack.peek();
	}

	public Card getDiscardCard()
	{
		if (myDiscardStack.size() == 0)
			return null;
		return myDiscardStack.peek();
	}

	public Card getFoundationCard(int index)
	{
		if (myFoundations[index].isEmpty())
			return null;
		return myFoundations[index].peek();
	}

	public Stack<Card> getStack()
	{ /** Enter Code */

	}

	public Stack<Card> createDraw()
	{
		/** Enter Code */
	}

	public Stack<Card> removeFaceUpCards()
	{
		/** Enter Code */
	}

	public void deal()
	{
		/** Enter Code */
	}

	public void dealOneCards()
	{
		/** Enter Code */
	}

	public void dealThreeCards()
	{
		/** Enter Code */
	}

	public void resetDrawStack()
	{
		/** Enter Code */
	}

	public void DrawStackClicked()
	{
		/** Enter Code */
	}

	public void DiscardStackClicked()
	{
		/** Enter Code */
	}

	public void foundationStackClicked()
	{
		/** Enter Code */
	}

	public void tableauStackClicked()
	{
		/** Enter Code */
	}

	public void initializeStacks()
	{
		/** Enter Code */
	}

	public void isGameOver()
	{
		/** Enter Code */
	}

	public void displayGameOverDialog()
	{
		/** Enter Code */
	}

	public void addToStack()
	{
		/** Enter Code */
	}

	public boolean canAddToStack()
	{
		/** Enter Code */
	}

	public boolean canAddToFoundation()
	{
		/** Enter Code */
	}
=======
	public Card getDrawCard() { /**Enter Code*/ }
	 
	public Card getDiscardCard() { /*Enter Code*/ }

	public Card getFoundationCard() { /*Enter Code*/ }
	 
	public Stack<Card> getStack() { /**Enter Code*/ }
	 
	public Stack<Card> createDraw() { /**Enter Code*/ }
	
	public Stack<Card> removeFaceUpCards() { /**Enter Code*/ }
	
	public void deal() { /**Enter Code*/ }
	 
	public void dealOneCards() { /**Enter Code*/ }
	 
	public void dealThreeCards() { /**Enter Code*/ }
	 
	public void resetDrawStack() { /**Enter Code*/ }
	 
	public void DrawStackClicked() { /**Enter Code*/ }
	 
	public void DiscardStackClicked() { /**Enter Code*/ }
	 
	public void foundationStackClicked() { /**Enter Code*/ }
	 
	public void tableauStackClicked() { /**Enter Code*/ }
	 
	public void initializeStacks() { /**Enter Code*/ }
	
	public void isGameOver() { /**Enter Code*/ }
	 
	public void displayGameOverDialog() { /**Enter Code*/ }
	 
	public void addToStack() { /**Enter Code*/ }
	
	public boolean canAddToStack() { /**Enter Code*/ }
	 
	public boolean canAddToFoundation() { /**Enter Code*/ }
	

}
