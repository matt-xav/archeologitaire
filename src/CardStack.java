import java.util.Stack;

import

public class CardStack extends Stack
{
    private int StackSize;

    public CardStack()
    {
    }


    public Card drawTop()
    {
        return pop();
    }

    public boolean addTop(Card c){
        return false;
    }

    public void displayStack(){//draw cards all on same xz

    }

}
