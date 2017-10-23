package solitaire;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;

import javax.imageio.ImageIO;

public enum Suit
{
	SPADES, HEARTS, DIAMONDS, CLUBS;
	
	/** Holds the symbol for this suit as an image. */
	private Image symbol;
	
	/**
	 * Four static {@link Image}s for each suit. They are static so that each
	 * <code>Suit</code> instance doesn't have its own image in memory. These
	 * variables are private because they may be <code>null</code> at some point.
	 */
	private static Image SPADES_ICON, HEARTS_ICON, DIAMONDS_ICON, CLUBS_ICON;
	
	/** Constructor which sets the symbol for the suit. */
	private Suit()
	{
		setImages();
	}
	
	private void setImages()
	{
		if (SPADES_ICON != null) // Then there is no need to read all images again
			return; // as they have already been read.
			
		try
		{
			SPADES_ICON = ImageIO.read(getClass().getResource("spade.gif"));
			HEARTS_ICON = ImageIO.read(getClass().getResource("heart.gif"));
			DIAMONDS_ICON = ImageIO.read(getClass().getResource("diamond.gif"));
			CLUBS_ICON = ImageIO.read(getClass().getResource("club.gif"));
		}
		catch (IOException e)
		{
		}
	}
	
	/** Sets the image for symbol depending on this suit. */
	private void setSymbol()
	{
		switch (this)
		{
			case SPADES:
				symbol = SPADES_ICON;
				break;
			case HEARTS:
				symbol = HEARTS_ICON;
				break;
			case CLUBS:
				symbol = CLUBS_ICON;
				break;
			case DIAMONDS:
				symbol = DIAMONDS_ICON;
				break;
		}
	}
	
	/** Returns the color of the suit based on standard French card suits. */
	public Color getColor()
	{
		switch (this)
		{
			case HEARTS:
			case DIAMONDS:
				return Color.RED;
			case SPADES:
			case CLUBS:
			default:
				return Color.BLACK;
		}
	}
	
	/** Draws the suit at a given location with given dimensions. */
	public void draw(Graphics pane, int x, int y, int width)
	{
		if (symbol == null)
		{ // Then the symbol needs to be set.
			setSymbol();
		}
		
		double scale = width * 1.0 / symbol.getWidth(null); // To scale the image
		int height = (int) (symbol.getHeight(null) * scale); // to the given size.
		
		// Draws the image
		pane.drawImage(symbol, x - width / 2, y - height / 2, width, height, null);
	}
}