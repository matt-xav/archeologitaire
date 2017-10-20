package src;

public class Card
{
	private int rank;
    private String suit;
    private boolean isFaceUp;
    
    /**
     * Constructs a Card object
     * @param newRank rank of the card
     * @param newSuit suit of the card
     */
    public Card(int newRank, String newSuit)
    {
        rank = newRank;
        suit = newSuit;
        isFaceUp = false;
    }
    
    public int getRank()
    {
        return rank;
    }

    public String getSuit()
    {
        return suit;
    }

    public boolean isRed()
    {
        return (suit.equals("d") || suit.equals("h"));
    }

    public boolean isFaceUp()
    {
        return isFaceUp;
    }

    public void turnUp()
    {
        isFaceUp = true;
    }

    public void turnDown()
    {
        isFaceUp = false;
    }

    //added 
    public void display()
    {
    	/**Enter Code*/ 
    }

    public String getFileName()
    {
        if (!isFaceUp)  
        	return "back.gif"; 
        if (rank == 10) 
        	return "t" + suit + ".gif";
        if (rank == 11) 
        	return "j" + suit + ".gif";
        if (rank == 12) 
        	return "q" + suit + ".gif";
        if (rank == 13) 
        	return "k" + suit + ".gif";
        if (rank == 1)  
        	return "a" + suit + ".gif";
        
        return rank + suit + ".gif";
    }

    // added 
    public String toString()
    {
        return String.format("Card: %s%s", getSuit(), getRank());//need to add special things for j, k, q, a
    }
}
