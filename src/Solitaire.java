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

	public Card getDrawCard() { /**Enter Code*/ return null; }
	 
	public Card getDiscardCard() { /**Enter Code*/ return null; }

	public Card getFoundationCard() { /**Enter Code*/ return null; }
	 
	public Stack<Card> getStack() { /**Enter Code*/ return null; }
	 
	public Stack<Card> createDraw() { /**Enter Code*/ return null; }
	
	public Stack<Card> removeFaceUpCards() { /**Enter Code*/ return null; }
	
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
	
	public boolean canAddToStack() { /**Enter Code*/ return false; }
	 
	public boolean canAddToFoundation() { /**Enter Code*/ return false; }
	

}
