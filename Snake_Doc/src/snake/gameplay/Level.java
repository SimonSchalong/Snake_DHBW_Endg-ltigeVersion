package snake.gameplay;
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import snake.Game;
import snake.configuration.Config;
/**
 * this class is used to generate a new Level with the given parameters
 */
public class Level implements Serializable{
	
	private static final long serialVersionUID = 3841876130241237097L;
	
	
	private int sizeX, sizeY;
	private List<Coordinate> blockedCoords;
	private String name;
	private double speedMod;
	private int startLength;
	private transient boolean wallCollision;
	
	/**
	 * Creates a new Level object with the given parameters
	 * @param sizeX The X-Gridsize. Has to be a non-zero integer between 8 and 64.
	 * @param sizeY The Y-Gridsize. Has to be a non-zero integer between 8 and 64.
	 * @param blockedCoordinates A list containing all coordinates where a obstacle should be placed
	 * @param displayName The name of the level
	 * @param speedModifier The speedmodifier for the gameplay. Has to be a non-zero value between 0.25 and 10
	 * @param startLength The length of the snake when the level stars. Can be every number greater than 0
	 */
	public Level(int sizeX, int sizeY, List<Coordinate> blockedCoordinates, String displayName, double speedModifier, int startLength) {
		this.blockedCoords = blockedCoordinates;
		this.name = displayName;
		setSizeX(sizeX);
		setSizeY(sizeY);
		setSpeedModifier(speedModifier);
		setStartingLength(startLength);
		wallCollision = true;
	}
	
	public int getSizeX() {
		return sizeX;
	}
	public int getSizeY() {
		return sizeY;
	}
	public List<Coordinate> getBlockedCoords() {
		return blockedCoords;
	}
	public String getDisplayName() {
		return name;
	}
	public double getSpeedModifier() {
		return speedMod;
	}
	public int getStartingLength() {
		return startLength;
	}
	
	public boolean getWallCollision() {
		return wallCollision;
	}
	
	@Override
	public String toString() {
		return name;
	}
	/**
	 * used to get all necessary data from the config
	 * @return new Level with all the necessary data from the config
	 */
	public final static Level getDefaultLevel() {
		return new Level(
				Game.getConfig().getSquareSizeX(),
				Game.getConfig().getSquareSizeY(),
				Collections.emptyList(),
				"Default Level",
				Game.getConfig().getSpeedModifier(),
				Game.getConfig().getStartLength()
			);
	}
	/**
	 * used to get the defaultLevel without wallCollision
	 * @return the defaultlevel, but wallCollision is turned off
	 */
	public final static Level getFreePlayLevel() {
		Level l = getDefaultLevel();
		l.wallCollision = false;
		return l;
	}
	/**
	 * used to change the SizeX via the optionMenu
	 * @param newSize the new x-coord for the level's dimension
	 */
	private void setSizeX(int newSize) {
		int val = Math.min(newSize, Config.MAX_SIZE);
		val = Math.max(val, Config.MIN_SIZE);
		this.sizeX = val;
	}
	/**
	 * used to change the SizeY via the optionMenu
	 * @param newSize the new y-coord for the level's dimension
	 */
	private void setSizeY(int newSize) {
		int val = Math.min(newSize, Config.MAX_SIZE);
		val = Math.max(val, Config.MIN_SIZE);
		this.sizeY = val;
	}
	/**
	 * used to change the speedModifier via the optionMenu
	 * @param speedMod value that the speedModifier is set to
	 */
	private void setSpeedModifier(double speedMod) {
		double val = Math.min(speedMod, Config.MAX_SPEED);
		val = Math.max(val, Config.MIN_SPEED);
		this.speedMod = val;
	}
	/**
	 * used to change the startLength via the optionMenu
	 * @param length that the startingLength is set to
	 */
	private void setStartingLength(int length) {
		this.startLength = Math.max(length, Config.MIN_LENGTH);
	}
	
	
	
}
