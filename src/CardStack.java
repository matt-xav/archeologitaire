import java.util.Stack;

public class CardStack
{
    private Stack<Card> Cards;
    private int StackSize;

    public CardStack(){
        Cards = new Stack<Card>();
    }

    public Card drawTop(){
        return Cards.pop();
    }

    boolean addTop(Card c){
        return false;
    }

    public void displayStack(){//draw cards all on same xz

    }
}
