package src;

import java.util.Stack;

public class CardStack
{
    private Stack<Card> Cards;
    private int StackSize;

    public CardStack(){
        Cards = new Stack();
    }

    public Card drawTop(){
        return Cards.pop();
    }

    boolean addTop(Card c){

    }

    displayStack(){//draw cards all on same xz

    }

	public Card peek()
	{
		// TODO Auto-generated method stub
		return null;
	}

	public int size()
	{
		// TODO Auto-generated method stub
		return 0;
	}
}
