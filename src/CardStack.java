<<<<<<< HEAD
<<<<<<< HEAD
=======
package src;

>>>>>>> 16993dc77b1bc1771cc1cbf63f93e1e16072f085
import java.util.Stack;
=======
>>>>>>> parent of de8015e... Introduced Java 9

public class CardStack
{
    private Stack<Card> Cards;
    private int StackSize

<<<<<<< HEAD
    public CardStack(){
<<<<<<< HEAD
        Cards = new Stack<Card>();
=======
        Cards = new Stack();
>>>>>>> 16993dc77b1bc1771cc1cbf63f93e1e16072f085
=======
    public Stack(){
        Cards = new Stack();
>>>>>>> parent of de8015e... Introduced Java 9
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
