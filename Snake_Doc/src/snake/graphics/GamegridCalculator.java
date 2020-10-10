package snake.graphics;

import snake.Game;

/**
 * this class is used to determine the sizes of the display corresponding the values set in the config
 *
 */
public class GamegridCalculator {

	public final static int MAX_GRID_SIZE = 512;
	
	private int squaresX, squaresY;
	
	public GamegridCalculator() {
		squaresX = Game.getConfig().getSquareSizeX(); 
		squaresY = Game.getConfig().getSquareSizeY();
	}

	public int getSquaresXCount() {
		return squaresX;
	}

	public int getSquaresYCount() {
		return squaresY;
	}

	public void setSquaresX(int squaresX) {
		this.squaresX = squaresX;
	}

	public void setSquaresY(int squaresY) {
		this.squaresY = squaresY;
	}
	
	public int getSquareSize() {
		int size = 32;
		while(size*squaresX > MAX_GRID_SIZE || size*squaresY > MAX_GRID_SIZE) {
			size -= 4;
		}
		return size;
	}
	
	public int getTotalGridWidth() {
		return getSquareSize()*squaresX;
	}
	
	public int getTotalGridHeight() {
		return getSquareSize()*squaresY;
	}
	
	/**
	 * @deprecated
	 */
	public int getOffsetX() {
		return (Gui.WIDTH-getTotalGridWidth())/2 - 10;
	}
	/**
	 * @deprecated
	 */
	public int getOffsetY() {
		return (Gui.HEIGHT-getTotalGridHeight())/2 - 20;
	}
}
