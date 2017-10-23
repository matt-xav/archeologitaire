package solitaire;

import java.awt.Component;

/**
 * Moves a StackOfCards from a source location to a the location of a
 * destination 
 * 
 * Borrowed from:
 */
public class StackOfCardsAnimator implements Runnable
{

	private StackOfCards cards;
	private StackOfCards destination;

	private double y;
	private double x;
	private double dy;
	private double dx;
	private double accelerationY;
	private double accelerationX;

	private Component component;
	
	/** Instantiates the animation. */
	public StackOfCardsAnimator(StackOfCards cards, StackOfCards destination, Component component)
	{
		this.cards = cards;
		this.destination = destination;
		
		// The new position of cards.
		int destinationX = (int) destination.shapeOfNextCard().getCenterX();
		int destinationY = (int) destination.shapeOfNextCard().getCenterY();
		
		this.component = component;
		
		// The difference in x and y between the two stacks and the hypotenuse
		// of the resulting triangle.
		int deltaX = destinationX - cards.getX();
		int deltaY = destinationY - cards.getY();
		double hyp = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
		
		dx = deltaX / hyp; // cosine of the angle.
		dy = deltaY / hyp; // sine of the angle.
		accelerationX = dx; // The acceleration and velocity vectors point in the
		accelerationY = dy; // same direction with equal initial values.
		
		x = cards.getX();
		y = cards.getY();
		
		Thread thread = new Thread(this);
		thread.start(); // Starts the thread to run the animation.
	}
	
	/** Moves cards from its initial location to the destination stack */
	public void run()
	{
		while (!hasArrived())
		{ // until cards arrives to the destination.
			x += dx; // update the position,
			y += dy;
			
			dx += accelerationX; // and the velocity.
			dy += accelerationY;
			
			cards.setLocation((int) x, (int) y); // Set the location.
			
			try
			{
				Thread.sleep(10); // Then wait a few milliseconds.
			}
			catch (InterruptedException e)
			{
			}
			
			if (component != null)
			{ // If its not null,
				component.repaint(); // repaint.
			}
		}
		
		try
		{
			destination.appendStack(cards); // appends the cards.
		}
		catch (IllegalArgumentException e)
		{
		} // some stacks may throw an exception.
		
		cards.clear();
		component.repaint();
	}
	
	/** Determines if the cards have arrived (have past the destination). */
	private boolean hasArrived()
	{
		boolean arrivedX, arrivedY;
		
		// The sign of the velocity indicates the which side of the destination
		// that cards where initially on. If cards is still on that side,
		// then it hasn't arrived.
		if (dx < 0)
		{
			arrivedX = cards.getX() <= destination.shapeOfNextCard().getCenterX();
		}
		else
		{
			arrivedX = cards.getX() >= destination.shapeOfNextCard().getCenterX();
		}
		if (dy < 0)
		{
			arrivedY = cards.getY() <= destination.shapeOfNextCard().getCenterY();
		}
		else
		{
			arrivedY = cards.getY() >= destination.shapeOfNextCard().getCenterY();
		}
		return arrivedX && arrivedY; // Cards must arrive in both dimensions.
	}
}