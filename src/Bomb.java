package src;

public class Bomb{
	private int x;
	private int y;
	
	public Bomb(int X, int Y) {
		x = X;
		y = Y;
	}
	
	public Bomb() {
		x = 0;
		y = 0;
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}
}

