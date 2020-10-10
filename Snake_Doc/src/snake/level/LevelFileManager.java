package snake.level;

import java.io.File;
import java.io.FilenameFilter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import snake.gameplay.Coordinate;
import snake.gameplay.Level;

/**
 * controls all stored level files and loads them on startup 
 * 
 */
public class LevelFileManager {
	
	public final static String EXTENSION = ".lvl", DIRECTORY = "Levels/";
	
	public LevelFileManager() {
		
	}
	/**
	 * Creates a LevelParser and streams the data from {@link #getMatchingFiles()} into an list
	 */
	public List<Level> getAllLevelsFromStorage(){
		LevelParser parser = new LevelParser();
		return Arrays.stream(getMatchingFiles())
				.map(x -> parser.readFromFile(x))
				.collect(Collectors.toList());
	}
	/**
	 * @return an array containing all files from the /Levels/ directory matching the ".lvl" file extension
	 */
	private File[] getMatchingFiles(){
		File dir = new File(DIRECTORY);
		return dir.listFiles(new FilenameFilter() {
			
			@Override
			public boolean accept(File dir, String name) {
				return name.endsWith(EXTENSION);
			}
		});
	}
	/**
	 * A Method to write the level into a string. Into a document
	 * @param sizeX a parameter that determines the length of the playing field
	 * @param sizeY a parameter that determines the width of the playing field
	 * @param displayName a parameter that determines the name
	 * @param blockedCoords a list that contains the blocked blocks
	 * @param speedModifier a parameter that determines the speed of the snake
	 * @param startLength a parameter that determines the length of the snake 
	 */
	public void writeLevelToStorage(int sizeX, int sizeY, String displayName, List<Coordinate> blockedCoords, double speedModifier, int startLength) {
		writeLevelToStorage(new Level(sizeX, sizeY, blockedCoords, displayName, speedModifier, startLength));
	}
	
	/**
	 * Writes a given level to the storage directory and saves it
	 * @param level the level that will be saved
	 */
	public void writeLevelToStorage(Level level) {
		LevelParser parser = new LevelParser();
		parser.writeToFile(level, new File(DIRECTORY + getSafeFileName(level.getDisplayName()) + EXTENSION));
	}
	
	/**
	 * A Method using regex to make strings safe for filenames
	 * @param rawName The raw unsafe name
	 * @return A safe version for the filename, which doesnt contain chars like slash or space
	 */
	private String getSafeFileName(String rawName) {
		return rawName.replaceAll("\\W+", "_");
	}
	
}
