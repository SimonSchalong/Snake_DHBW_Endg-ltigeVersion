package snake.graphics;

import java.awt.Color;
/**
 * this enum contains the default colors of all items displayed by the gui
 */

public enum Colors {
	FOOD(new Color(196, 61, 15)),
	SNAKE_BODY1(new Color(88, 154, 48)),
	SNAKE_BODY2(new Color(69, 122, 37)),
	OBSTACLE(new Color(95, 64, 33)),
	GRID_BACKGROUND(new Color(70, 70, 70)),
	GRID(new Color(40, 40, 40)),
	POPUP_MENU(new Color(10, 10, 10, 100)),
	POPUP_MENU_BORDER(new Color(160, 160, 160));
	
	
	private Color c;
	
	private Colors(Color color) {
		this.c = color;
	}
	
	public Color getColor() {
		return c;
	}
}
