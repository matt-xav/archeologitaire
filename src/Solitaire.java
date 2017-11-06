package src;

import java.awt.event.MouseListener;


import java.util.Stack;

public class Solitaire
{
	private CardStack myDrawStack;
	private CardStack myDiscardStack;

	private FoundationStack[] myFoundations;
	private TableauStack[] myTableaus;
	
	private SolitaireGUI display;
    private Solitaire instance;
	
	public enum GAME_STATES {INITIALIZING, GAME_IN_PROGRESS, GAME_OVER, BAD_STATE};
	public GAME_STATES currentState = GAME_STATES.BAD_STATE;
	
	
    /**
     * Constructor that uses the method initializeStacks to start a game
     */
    public Solitaire()
	{
    	initializeStacks();
	}

    public Solitaire instance() 
    {
    	if (instance == null)
    	{
            instance = new Solitaire();
    	}
    	return instance;
    }
    
	public Card getDrawCard() 
	{ 
//		if (stock.size() == 0) 
        	return null;
//        return stock.peek();
	}
	 
	public Card getDiscardCard() 
	{ 
		/**Enter Code*/ 
		return null; 
	}

	public Card getFoundationCard() 
	{ 
		/**Enter Code*/ 
		return null; 
	}
	 
	public CardStack getStack() 
	{ 
		/**Enter Code*/ 
		return null; 
	}
	 
	public CardStack createDraw() 
	{ 
		/**Enter Code*/
		return null; 
	}
	
	public CardStack removeFaceUpCards() 
	{ 
		/**Enter Code*/
		return null;
	}
	
	public void deal() 
	{ 
		/**Enter Code*/ 
	}
	 
	public void dealOneCards()
	{ 
		/**Enter Code*/ 
	}
	 
	public void dealThreeCards() 
	{ 
		/**Enter Code*/ 
	}
	 
	public void resetDrawStack() 
	{ 
		/**Enter Code*/ 
	}
	 
	public void DrawStackClicked() 
	{ 
		/**Enter Code*/ 
	}
	 
	public void DiscardStackClicked() 
	{ 
		/**Enter Code*/ 
	}
	 
	public void foundationStackClicked()
	{ 
		/**Enter Code*/ 
	}
	 
	public void tableauStackClicked() 
	{ 
		/**Enter Code*/ 
	}
	 
	public void initializeStacks() 
	{ 
		currentState = GAME_STATES.INITIALIZING;
    	
    	myFoundations = new FoundationStack[4];
        for (int i = 0; i < myFoundations.length; i++)
        {
            myFoundations[i] = new FoundationStack();
        }
        myTableaus = new TableauStack[7];
        for (int i = 0; i < myTableaus.length; i++)
        {
        	myTableaus[i] = new TableauStack();
        }
        myDrawStack 	= new CardStack();
        myDiscardStack	= new CardStack();
        
///		display 		= new SolitaireGUI(this); /* starts the GUI*/
		
        myDrawStack 	= createDraw();
        deal();
        currentState = GAME_STATES.GAME_IN_PROGRESS;
	}
	
	public void isGameOver() 
	{ 
		/**Enter Code*/ 
	}
	 
	public void displayGameOverDialog() 
	{ 
		/**Enter Code*/ 
	}
	 
	public void addToStack() 
	{ 
		/**Enter Code*/ 
	}
	
	public boolean canAddToStack() 
	{ 
		/**Enter Code*/ 
		return false; 
	}
	 
	public boolean canAddToFoundation()
	{ 
		/**Enter Code*/ 
		return false; 
	}
	

}
