package snake.gameplay;
/**
 * In here the game get started with the start() method.
 * A safe spawn location for the snake gets randomly selected and food is randomly generated in the field.
 * The game will stop when the snake eats it's own body or hits the wall/obstacle. 
 */
import snake.Game;
import snake.Game.Gamestatus;
import snake.Util;

public class Gameplay implements GameTickListener{
	
	private Level level;
	private SnakeStructure snake;
	private Coordinate nextFood;
	private int score;
	
	//Using Singleton because there were some fatal pseudo-pointer issues regarding multiple gameplays at once
	private static Gameplay instance;
	public static Gameplay getInstance() {
		return (instance == null ? new Gameplay() : instance); //short version of if: If instance == null then new Gameplay, else instance
	}
	
	private Gameplay() {
		instance = this;
		this.level = Level.getDefaultLevel();
	}
	
	/**
	 * Starts the round, sets the score to 0, creates a new SnakeStructure with a safeSpawnLocation, spawns the head and generates the first piece of food.
	 */
	public void start() {
		score = 0;
		snake = new SnakeStructure(getSafeSpawnLocation());
		snake.increaseLength(level.getStartingLength()-1);
		generateNewFood();
	}
	
	/**
	 * @return the current selected level
	 */
	public Level getLevel() {
		return level;
	}
	
	public SnakeStructure getSnake() {
		return snake;
	}
	
	/**
	 * Moves the snake for every tick and checks if the position of the head is valid. Also checks for food and changes the length of the snake accordingly to that.
	 */
	@Override
	public void doTick() {
		snake.moveForward();
		Coordinate c = snake.getHead().coordinate;
		if(!isOnValidPosition(c)) {
			Game.stopCurrentGamePlay();
		}
		if(c.equals(nextFood)) {
			snake.increaseLength(1);
			incScore();
			generateNewFood();
		}
	}
	/**
	 * Setter for the passed level
	 * Only works when the Gamestatus is stopped
	 * @param gameLevel 
	 */
	public void setLevel(Level gameLevel) {
		if(Game.getGameStatus().equals(Gamestatus.STOPPED)) {
			this.level = gameLevel;
		}
	}
	
	/**
	 * checks if the coordinate collides with any obstacle/wall/snake-bodypart
	 * @param the coordinate that will be checked
	 * @return true if there is no obstacle on that coordinate
	 */
	public boolean isOnValidPosition(Coordinate c) {
		//out of bounds
		if(!Util.isInRange(0, level.getSizeX()-1, c.x) && level.getWallCollision()) {
			return false;
		}
		if(!Util.isInRange(0, level.getSizeY()-1, c.y) && level.getWallCollision()) {
			return false;
		}
		//check blocked fields
		if(level.getBlockedCoords().contains(c)) {
			return false;
		}
		//check own body
		if(snake.hasCollidedWithBody()) {
			return false;
		}
		return true;
		
	}
	
	/**
	 * @param dir direction of the snake head for the next gametick
	 */
	public void changeSnakeDirection(Direction dir) {
		if(dir != null) {
			snake.getHead().direction = dir;
		}
	}
	
	
	private void generateNewFood() {
		nextFood = generateFoodLocation();
	}
	
	public Coordinate getFoodLocation() {
		return nextFood;
	}
	/**
	 * generate location for snake to spawn safely (not in blocked coordinate)
	 * @return a new safe place to spawn
	 */
	private Coordinate getSafeSpawnLocation() {
		Coordinate c = Coordinate.randomCoordinate(level.getSizeX(), level.getSizeY());
		if(level.getBlockedCoords().contains(c)) {
			c = getSafeSpawnLocation();
		}
		return c;
	}
	/**
	 * generate location for food to spawn safely (not in snake or blocked coordinate)
	 * @return a new location where food could possibly be placed
	 */
	private Coordinate generateFoodLocation() {
		Coordinate c = Coordinate.randomCoordinate(level.getSizeX(), level.getSizeY());
		if(level.getBlockedCoords().contains(c) || snake.intersectsWith(c)) {
			c = generateFoodLocation();
		}
		return c;
	}
	
	/**
	 * increase the score and thus the games relative speed
	 */
	private static long SCORE_MULTIPLIER = 100;
	public void incScore() {
		score++;
		double relativeTickSpeed = 1 + (0.05*score);
		Game.getGameClock().adjustTickTime(relativeTickSpeed);
	}
	public long getScore() {
		return Long.valueOf(SCORE_MULTIPLIER * score);
	}
	
}
