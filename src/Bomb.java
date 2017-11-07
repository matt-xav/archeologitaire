package src;

import java.awt.Polygon;

public class Bomb extends Polygon {
	private int x;
	private int y;
	private int width;
	
	public Bomb(int X, int Y, int Width) {
		super();
		setX(X);
		setY(Y);
		setWidth(Width);
	}
	
	public Bomb() {
		super();
		setX(0);
		setY(0);
		setWidth(0);
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getWidth() {
		return width;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setWidth(int width) {
		this.width = width;
	}
}

