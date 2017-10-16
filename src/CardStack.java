import java.util.Stack;

public class CardStack
{
    private Stack<Card> Cards;
    private int StackSize;

    public CardStack() {
        Cards = new Stack<Card>();
    }


    public Card drawTop(){
        return Cards.pop();
    }

    public boolean addTop(Card c){
        return false;
    }

    public void displayStack(){//draw cards all on same xz

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
