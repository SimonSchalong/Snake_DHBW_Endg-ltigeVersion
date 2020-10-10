package snake;

import java.util.List;

import snake.configuration.Config;
import snake.gameplay.Coordinate;
import snake.gameplay.GameClock;
import snake.gameplay.GameTickExecutor;
import snake.gameplay.Gameplay;
import snake.gameplay.Level;
import snake.graphics.ChallengeMenu;
import snake.graphics.Draw;
import snake.graphics.GameplayMenu;
import snake.graphics.Gui;
import snake.graphics.LoseMenu;
import snake.graphics.MainMenu;
import snake.graphics.OptionMenu;
import snake.graphics.PauseMenu;
import snake.level.LevelFileManager;

/**
 * The central controlling main-class of the game
 */
public class Game {
	
	private static Config cfg;
	private static Gui gui;
	
	private static GameClock clock;
	
	private static Gamestatus STATUS;
	
	private Game() {
	}
	
	
	
	public static void main(String[] args) {
		cfg = Config.FILE_MANAGER.loadFromFile();
		gui = new Gui();
		gui.create();
		STATUS = Gamestatus.STOPPED;
		
//		createNewLevel(16, 16, "Dont cross the middle", coords, 1, 1);
//		createNewLevel(16, 16, "SPEEED", Collections.emptyList(), 3.0, 1);
//		createNewLevel(16, 16, "Large Python", Arrays.asList(new Coordinate(1, 1), new Coordinate(1, 14), new Coordinate(14, 1), new Coordinate(14, 14)), 0.75, 20);
	}
	/**
	 * global getter for the GUI  
	 */
	public static Gui getGameGui() {
		return gui;
	}
	/**
	 * global getter for the config
	 */
	public static Config getConfig() {
		return cfg;
	}
	/**
	 * global getter for the clock
	 */
	public static GameClock getGameClock() {
		return clock;
	}
	/**
	 * restored the config, but keeps the stored highscore
	 */
	public static void restoreConfig() {
		long hs = cfg.getHighScore();
		cfg = Config.DEFAULT_CONFIG;
		cfg.setHighScore(hs);
		cfg.save();
	}
	/**
	 * global getter for the gameplay
	 */
	public static Gameplay getRunningGame() {
		return Gameplay.getInstance();
	}
	/**
	 * global getter for the drawboard
	 */
	public static Draw getRunningDrawBoard() {
		if (STATUS.equals(Gamestatus.RUNNING)) {
			GameplayMenu menu = (GameplayMenu) gui.getCurrentMenu();
			return menu.getDrawBoard();
		}
		return null;
	}

	// -------------------------------Game switching methods-------------------------------
	/**
	 * sets the level for the next round
	 * @param level - the next level that will be played
	 */
	public static void setLevel(Level level) {
		Gameplay.getInstance().setLevel(level);
	}
	/**
	 * switches the display over to play-mode and prepares the start of the game
	 */
	public static void setupNewGamePlay() {
		STATUS = Gamestatus.PREPARED;
		Gameplay.getInstance().start();
		gui.setMenu(new GameplayMenu(), true);
	}
	/**
	 * starts the game, if the game is prepared
	 */
	public static void startGamePlay() {
		if(!STATUS.equals(Gamestatus.PREPARED)) {
			System.err.println("Invalid Gamestatus! Cannot start unprepared gameplay");
			System.exit(1);
		}
		STATUS = Gamestatus.RUNNING;
		clock = new GameClock();
		clock.start(GameTickExecutor.DEFAULT_GAMETICK);
	}
	/**
	 * stops the game and switches the display over to lose-screen
	 */
	public static void stopCurrentGamePlay() {
		if(!STATUS.equals(Gamestatus.RUNNING) && !STATUS.equals(Gamestatus.PREPARED)) return;
		if(STATUS.equals(Gamestatus.RUNNING)) clock.stop();
		STATUS = Gamestatus.STOPPED;
		gui.setMenu(new LoseMenu(), false);
	}
	/**
	 * switches the display back to the main menu if the game is not running
	 */
	public static void openMainMenu() {
		if(!STATUS.equals(Gamestatus.STOPPED)) return;
		gui.setMenu(new MainMenu(), true);
	}
	/**
	 * switches the display back to the challenge menu(level selector) if the game is not running
	 */
	public static void openChallengeMenu() {
		if(!STATUS.equals(Gamestatus.STOPPED)) return;
		gui.setMenu(new ChallengeMenu(), true);
	}
	/**
	 * switches the display back to the options menu if the game is not running
	 */
	public static void openOptionMenu() {
		if(!STATUS.equals(Gamestatus.STOPPED)) return;
		gui.setMenu(new OptionMenu(), true);
	}
	
	//paused flag
	private static boolean paused = false;
	
	/**
	 * @return true if the game is paused, false otherwise
	 */
	public static boolean isPaused() {
		return paused;
	}
	/**
	 * pauses the game, given the game is running
	 * if the game was already paused, calling this method will resume the game
	 */
	public static void pauseGame() {
		if(paused) {
			gui.restoreMenu();
		}else {
			gui.setMenu(new PauseMenu(), false);
		}
		paused = !paused;//flip state
	}
	//------------------------------------------------------------------------------------
	/**
	 * global getter of the gamestatus
	 * @return the status the game is currently in
	 */
	public static Gamestatus getGameStatus() {
		return STATUS;
	}
	
	public enum Gamestatus{
		STOPPED, PREPARED, RUNNING
	}
	
	/**	
	 * a method to create a new challenge 
	 * @param sizeX for the length of the field
	 * @param sizeY for the height of the field
	 * @param displayname for the name of the challenge
	 * @param blockedCoordinates for the blocked Coordinates
	 * @param speedModifier for the speed of the snake
	 * @param startLength for the length of the sneak
	 */
	public static void createNewLevel(int sizeX, int sizeY, String displayname, List<Coordinate> blockedCoordinates, double speedModifier, int startLength) {
		LevelFileManager lfm = new LevelFileManager();
		lfm.writeLevelToStorage(sizeX, sizeY, displayname, blockedCoordinates, speedModifier, startLength);
	}
	
	
	
	
}
