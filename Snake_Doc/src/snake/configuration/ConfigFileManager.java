package snake.configuration;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
/**
 * read and write operations for the config file.
 */
public class ConfigFileManager {
	public final static String FILE_NAME = "Gameconfig.cfg";
	
	public ConfigFileManager() {
		
	}
	/**
	 * writes the config to the file
	 * @param cfg is used to write changes of the config to the config file
	 */
	public void writeToFile(Config cfg) {
		File f = new File(FILE_NAME);
		try {
			FileOutputStream fos = new FileOutputStream(f);
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(cfg);
			oos.close();
			fos.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * loads the config from the file
	 * @return The stored config file. If there is no config the default config will be returned
	 */
	public Config loadFromFile() {
		File f = new File(FILE_NAME);
		Config cfg = Config.DEFAULT_CONFIG;
		try {
			FileInputStream fis = new FileInputStream(f);
			ObjectInputStream ois = new ObjectInputStream(fis);
			cfg = (Config) ois.readObject();
			ois.close();
			fis.close();
		} catch (IOException | ClassNotFoundException e) {
			//Config not found
		}
		return cfg;
	}
	
	
}
