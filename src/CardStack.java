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
}
