package snake.configuration;

import java.io.Serializable;
/**
 * The Datatype representation of the "Gameconfig.cfg" file.
 * This Class contains all values that need to be stored if the process is closed
 */
public class Config implements Serializable{
	
	private static final long serialVersionUID = -1258837568539627809L;
	
	/**
	 * The minimal and maximal dimensions of the playable field
	 */
	public transient final static int MIN_SIZE = 8, MAX_SIZE = 64;
	/*
	 * The minimal length of the snake obviously
	 */
	public transient final static int MIN_LENGTH = 1;
	/**
	 * The minimal speed modifier of the game, relative acceleration over time not included
	 */
	public transient final static double MIN_SPEED = 0.25;
	/**
	 * The maximal speed modifier of the game, relative acceleration over time not included
	 */
	public transient final static double MAX_SPEED = 10.0;
	
	/**
	 * The maximal dimension of the playable field in pixels
	 */
	public transient final static int MAX_GRID_SIZE = 512;

	private long highScore;
	private int squaresX, squaresY;
	private int startLength;
	private double speed;
	
	
	public Config() {
	}

	
	public int getSquareSizeX() {
		return squaresX;
	}

	public int getSquareSizeY() {
		return squaresY;
	}

	public int getStartLength() {
		return startLength;
	}

	public double getSpeedModifier() {
		return speed;
	}

	public long getHighScore() {
		return highScore;
	}

	public void setHighScore(long highScore) {
		this.highScore = highScore;
	}

	public void setSquareSizeX(int squaresX) {
		this.squaresX = squaresX;
	}

	public void setSquareSizeY(int squaresY) {
		this.squaresY = squaresY;
	}

	public void setStartLength(int startLength) {
		this.startLength = startLength;
	}

	public void setSpeedModifier(double speed) {
		this.speed = speed;
	}
	
	public void save() {
		FILE_MANAGER.writeToFile(this);
	}
	
	public static final ConfigFileManager FILE_MANAGER = new ConfigFileManager();
	
	/**
	 * The default config of the game. This value is used when the config gets deleted of resetted(highScore is excluded from beeing resetted).
	 * {@value #highScore} 0
	 * {@value #squaresX} 16
	 * {@value #squaresY} 16
	 * {@value #speed} 1.0
	 * {@value #startLength} 1
	 */
	public final static Config DEFAULT_CONFIG = new Config();
	static {
		DEFAULT_CONFIG.highScore = 0;
		DEFAULT_CONFIG.squaresX = 16;
		DEFAULT_CONFIG.squaresY = 16;
		DEFAULT_CONFIG.speed = 1.0;
		DEFAULT_CONFIG.startLength = 1;
	}
	
	@Override
	public String toString() {
		return "Config [squaresX=" + squaresX + ", squaresY=" + squaresY + ", startLength=" + startLength + ", speed="
				+ speed + "]";
	}
	
}
