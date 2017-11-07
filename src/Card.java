package src;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.io.File;

import javax.swing.ImageIcon;

public class Card
{
	private int rank;
    private String suit;
    private boolean isFaceUp;
    
	// The card's center's coordinates.
	private int x, y;
	
	// The card's width and height. 
	private int width, height;
    
    /**
     * Constructs a Card object
     */
    public Card(int newRank, int x, int y, int width, String newSuit)
    {
        rank = newRank;
        suit = newSuit;
        setSize(width);
		this.x = x;
		this.y = y;
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
    
	/**
	 * draws the cards
	 */
	private void drawCard(Graphics g, Card card, int x, int y)
	{
		if (card == null)
		{
			g.setColor(Color.BLACK);
			g.drawRect(x, y, width, height);
		}
		else
		{
			String fileName = card.getFileName();
			if (!new File(fileName).exists())
			    throw new IllegalArgumentException("bad file name:  " + fileName);
			Image image = new ImageIcon(fileName).getImage();
			g.drawImage(image, x, y, width, height, null);
		}
	}
    
	/** Returns whether or not some given coordinates are within the outline of the card. */
	public boolean contains(int x, int y)
	{
		return getShape().contains(x, y);
	}
    
	/**
	 * Compares this card with some other card. Per the specifications, a negative
	 * integer, zero, or a positive integer will be returned if this card is less
	 * than, equal to, or greater than the given card respectively. Specifically,
	 * the number returned is this card's value minus the given card's value.
	 */
	public int compareTo(Card card)
	{
		return rank - card.getRank();
	}
    
	/** Sets the coordinates of the center of the card. */
	public void setLocation(int x, int y)
	{
		this.x = x;
		this.y = y;
	}

	/** Returns the Shape of the card. */
	public Shape getShape()
	{
		return new RoundRectangle2D.Double(x - width / 2, y - height / 2, width, height, width / 10, width / 10);
	}
    
	public void setSize(int width)
	{
		this.width = width;
		height = width * 3 / 2;
	}
    
	/** Returns the x coordinate of the middle of the card. */
	public int getX()
	{
		return x;
	}
	
	/** Returns the y coordinate of the middle of the card. */
	public int getY()
	{
		return y;
	}
    
    
    // added 
    public String toString()
    {
        return String.format("Card: %s%s", getSuit(), getRank());//need to add special things for j, k, q, a
    }
}
