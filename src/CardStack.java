<<<<<<< HEAD
=======
package src;

>>>>>>> 16993dc77b1bc1771cc1cbf63f93e1e16072f085
import java.util.Stack;

public class CardStack
{
    private Stack<Card> Cards;
    private int StackSize;

    public CardStack(){
<<<<<<< HEAD
        Cards = new Stack<Card>();
=======
        Cards = new Stack();
>>>>>>> 16993dc77b1bc1771cc1cbf63f93e1e16072f085
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
