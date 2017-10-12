
public class Card 
{
	public int 		rank;
    public String 	suit;
    public boolean  isFaceUp;
    
    /**
     * Constructs a Card object
     * @param newRank rank of the card
     * @param newSuit suit of the card
     */
    public Card(int newRank, String newSuit)
    {
        rank	 = newRank;
        suit 	 = newSuit;
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

    public String getFileName()
    {
        if (!isFaceUp)  
        	return "back.jpg"; 
        if (rank == 10) 
        	return "t" + suit + ".jpg";
        if (rank == 11) 
        	return "j" + suit + ".jpg";
        if (rank == 12) 
        	return "q" + suit + ".jpg";
        if (rank == 13) 
        	return "k" + suit + ".jpg";
        if (rank == 1)  
        	return "a" + suit + ".jpg";
        
        return rank + suit + ".jpg";
    }
}
