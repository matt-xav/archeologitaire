package assets;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.Polygon;

public class Bomb extends Polygon
{
	private int width;
	private boolean selected;

	public Bomb(int[] X, int[] Y, int Width)
	{
		super(X, Y, X.length);
		setWidth(Width);
		deselect();
	}

	// points are defined in a counterclockwise fashion
	public Bomb(int upperx, int uppery, int width)
	{
		super();// {upperx, upperx, upperx + width, upperx+width}, {uppery, uppery + width,
				// uppery+width, uppery}, 4);
		xpoints[0] = upperx;
		xpoints[1] = upperx;
		xpoints[2] = upperx + width;
		xpoints[3] = upperx + width;
		;
		ypoints[0] = uppery;
		ypoints[1] = uppery + width;
		ypoints[2] = uppery + width;
		ypoints[3] = uppery;
		npoints = 4;
		setWidth(width);
		deselect();
	}

	public void display(Graphics g)
	{
		if (selected)
		{
			g.setColor(Color.GREEN);
			g.fillOval(xpoints[0] - 2, ypoints[0] - 2, width + 4, width + 4);
		}
		g.setColor(Color.BLACK);
		g.fillOval(xpoints[0], ypoints[0], width, width);
		// System.out.println("x: " + xpoints[0] + "\ny: " + ypoints[0] + "\nwidth: " +
		// width + "\n");
	}

	public void absolutemove(Point p)
	{
		// System.out.println("Before move: " + this);
		xpoints[0] = (int) p.getX() - width / 2;
		xpoints[1] = (int) p.getX() - width / 2;
		xpoints[2] = (int) p.getX() + width / 2;
		xpoints[3] = (int) p.getX() + width / 2;

		ypoints[0] = (int) p.getY() - width / 2;
		ypoints[1] = (int) p.getY() + width / 2;
		ypoints[2] = (int) p.getY() + width / 2;
		ypoints[3] = (int) p.getY() - width / 2;
		npoints = 4;
		// System.out.println(" After move: " + this + "\n");
	}

	public void relativemove(int movex, int movey)
	{
		xpoints[0] = xpoints[0] + movex;
		xpoints[1] = xpoints[1] + movex;
		xpoints[2] = xpoints[2] + movex;
		xpoints[3] = xpoints[3] + movex;

		ypoints[0] = ypoints[0] + movey;
		ypoints[1] = ypoints[1] + movey;
		ypoints[2] = ypoints[2] + movey;
		ypoints[3] = ypoints[3] + movey;
		System.out.println("moved x " + movex + " moved y " + movey);
	}

	@Override
	public boolean contains(Point p)
	{
		if (xpoints[0] <= (int) p.getX() && xpoints[2] >= (int) p.getX() && ypoints[0] <= (int) p.getY()
				&& ypoints[2] >= (int) p.getY())
		{
			return true;
		}
		return false;
	}

	public int getWidth()
	{
		return width;
	}

	public void setWidth(int width)
	{
		this.width = width;
	}

	public boolean isSelected()
	{
		return selected;
	}

	public void select()
	{
		this.selected = true;
	}

	public void deselect()
	{
		this.selected = false;
	}

	public String toString()
	{
		return String.format("Bomb:(%3d,%3d), (%3d,%3d), (%3d,%3d) , (%3d,%3d)", xpoints[0], ypoints[0], xpoints[1],
				ypoints[1], xpoints[2], ypoints[2], xpoints[3], ypoints[3]);
	}
}
