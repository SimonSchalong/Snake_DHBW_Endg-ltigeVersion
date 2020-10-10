package snake.gameplay;

import java.io.Serializable;
import java.util.Random;

public class Coordinate implements Cloneable, Serializable{
	
	private static final long serialVersionUID = -7264300993678207229L;
	
	public int x, y;
	
	public Coordinate(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Coordinate other = (Coordinate) obj;
		if (x != other.x)
			return false;
		if (y != other.y)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Coordinate [x=" + x + ", y=" + y + "]";
	}
	
	@Override
	/**
	 * returns a new coordinate(same values, different pointer) 
	 */
	protected Coordinate clone() {
		return new Coordinate(x, y);
	}
	/**
	 * create random coordinates and return the x- and y-Coordinate
	 */
	private static Random ran = new Random();
	public static Coordinate randomCoordinate(int boundsX, int boundsY) {
		return new Coordinate(ran.nextInt(boundsX), ran.nextInt(boundsY));
	}
	
}
