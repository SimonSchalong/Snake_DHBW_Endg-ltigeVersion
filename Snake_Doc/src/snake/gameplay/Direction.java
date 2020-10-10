package snake.gameplay;

/**
 * Overrides the current position in the coord-system with the new position in any non-diagonal-direction using the enums
 */
public enum Direction {
	
	UP(0, -1), 	//inverse direction since the coord-system is shown upside down
	DOWN(0, 1),	//inverse direction since the coord-system is shown upside down
	LEFT(-1, 0),
	RIGHT(1, 0);
	
	private int dx, dy;
	/**
	 * Used to change the current position
	 * @param dx X-Coordinate in the coord-system
	 * @param dy Y-Coordinate in the coord-system
	 */
	private Direction(int dx, int dy) {
		this.dx = dx;
		this.dy = dy;
	}
	
	public int getDirectionX() {
		return dx;
	}
	public int getDirectionY() {
		return dy;
	}
	
	
}
