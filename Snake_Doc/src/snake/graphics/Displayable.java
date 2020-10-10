package snake.graphics;

import javax.swing.JPanel;
/**
 * Abstract jpanel class with a height the panel should be displayed on
 */
public abstract class Displayable extends JPanel{

	private static final long serialVersionUID = 6406445114316270458L;
	
	/**
	 * this method controls the height this panel will be displayed
	 * @return the height value
	 */
	public Integer getLayer() {
		return Integer.valueOf(100);
	}
}
