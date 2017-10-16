<<<<<<< HEAD
import java.awt.event.MouseListener;
=======
package src;

import java.util.Stack;
>>>>>>> 16993dc77b1bc1771cc1cbf63f93e1e16072f085

public class Solitaire
{
	private CardStack myDrawStack;
	private CardStack myDiscardStack;
	
	private FoundationStack[] myFoundations;
	private TableauStack[] myTableaus;
	
	
	public Solitaire() {
		myDrawStack = new CardStack();
		myDiscardStack = new CardStack();

	}

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
