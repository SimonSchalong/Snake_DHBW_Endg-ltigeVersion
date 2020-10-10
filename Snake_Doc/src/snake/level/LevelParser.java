package snake.level;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import snake.gameplay.Level;
/**
 * used to read and write the levels to and from their respective files
 */
public class LevelParser {
	
	/**
	 * Creates an InputOutputStream and reads a level from this file
	 * @param f the file where the level will be read from
	 * @return the level that has been read
	 * @throws non-existing files with a specific error description and all other errors without an errorcode, printing the StackTrace
	 */
	public Level readFromFile(File f) {
		Level l = Level.getDefaultLevel();
		try {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			l = (Level) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException e) {
			System.err.println("The specified file was not found.");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return l;
	}
	/**
	 * Creates an InputOutputStream and writes a level to this file
	 * @param level this will be written to the file f
	 * @param f the file where the level is written to
	 * @throws all errors without an errorcode, printing the StackTrace
	 */
	public void writeToFile(Level level, File f) {
		File parent = f.getParentFile();
		if(!parent.exists()) parent.mkdir();
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(level);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
