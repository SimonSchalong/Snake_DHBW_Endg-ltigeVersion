package snake;
/**
 * These functions check if value is between min and max
 */
public class Util {
	
	private Util() {
	}
	
	/**
	 * @param min the lower bound of the range
	 * @param max the upper bound of the range
	 * @param value the value that will be checked
	 * @return true if value is element of [min, max]
	 */
	public static boolean isInRange(double min, double max, double value) {
		if(Math.max(min, value) == Math.min(max, value)) {
			return true;
		}
		return false;
	}
	/**
	 * @param min the lower bound of the range
	 * @param max the upper bound of the range
	 * @param value the value that will be checked
	 * @return true if value is element of [min, max]
	 */
	public static boolean isInRange(int min, int max, int value) {
		if(Math.max(min, value) == Math.min(max, value)) {
			return true;
		}
		return false;
	}
	
	
}
