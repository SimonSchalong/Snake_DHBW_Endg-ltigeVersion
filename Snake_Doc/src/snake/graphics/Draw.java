package snake.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.util.Map;
import java.util.TreeMap;

import javax.swing.JPanel;

import snake.Game;
import snake.gameplay.Coordinate;
import snake.gameplay.GameTickListener;
import snake.gameplay.Gameplay;
import snake.gameplay.SnakeStructure;

public class Draw extends JPanel implements GameTickListener{

	private static final long serialVersionUID = -8178807887370698794L;
	
	private GameplayMenu menu;
	
	public Draw(GameplayMenu menu) {
		this.menu = menu;
	}

	@Override
	/**
	 * Draws the playing ground using the X and Y values from the gamegridCalculator
	 */
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		Gameplay gp = Game.getRunningGame();
		GamegridCalculator gc = getGamegridCalculator();

		for(int i = 0; i < gc.getSquaresXCount(); i++) {
			for(int j = 0; j < gc.getSquaresYCount(); j++) {
				g.setColor(Colors.GRID_BACKGROUND.getColor());
				g.fillRect(gc.getSquareSize()*i, gc.getSquareSize()*j, gc.getSquareSize(), gc.getSquareSize());
				g.setColor(Colors.GRID.getColor());
				g.drawRect(gc.getSquareSize()*i, gc.getSquareSize()*j, gc.getSquareSize(), gc.getSquareSize());
			}
		}
		
		/**
		 * draw the Snake
		 */
		boolean colorIndicator = true;
		
		
		SnakeStructure snake = gp.getSnake();
		for(Coordinate c : snake) {
			g.setColor(stripes.get(colorIndicator).getColor());
			g.fillRect(gc.getSquareSize()*c.x, gc.getSquareSize()*c.y, gc.getSquareSize(), gc.getSquareSize());
			colorIndicator = !colorIndicator;
		}
		
		/**
		 * draw the food
		 */
		Coordinate food = gp.getFoodLocation();
		g.setColor(Colors.FOOD.getColor());
		g.fillRect(gc.getSquareSize()*food.x, gc.getSquareSize()*food.y, gc.getSquareSize(), gc.getSquareSize());
		super.repaint();
		
		/**
		 * draw the obstacles
		 */
		for(Coordinate c : gp.getLevel().getBlockedCoords()) {
			g.setColor(Colors.OBSTACLE.getColor());
			g.fillRect(gc.getSquareSize()*c.x, gc.getSquareSize()*c.y, gc.getSquareSize(), gc.getSquareSize());
			g.setColor(Colors.GRID.getColor());
			g.drawRect(gc.getSquareSize()*c.x, gc.getSquareSize()*c.y, gc.getSquareSize(), gc.getSquareSize());
		}
		
	}
	/**
	 * used to create a new gamegridCalculator using the X and Y size provided by the config
	 * @return a new gamegridCalculator
	 */
	private GamegridCalculator getGamegridCalculator() {
		Gameplay gp = Game.getRunningGame();
		GamegridCalculator gc = new GamegridCalculator();
		gc.setSquaresX(gp.getLevel().getSizeX());
		gc.setSquaresY(gp.getLevel().getSizeY());
		return gc;
	}
	
	/**
	 * create a new map and load new elements added to the snake to the map
	 */
	private static Map<Boolean, Colors> stripes = new TreeMap<Boolean, Colors>();
	static {
		stripes.put(true, Colors.SNAKE_BODY1);
		stripes.put(false, Colors.SNAKE_BODY2);
	}
	
	@Override
	/**
	 * @return Dimension containing the width and the height of the gameGrid
	 */
	public Dimension getPreferredSize() {
		GamegridCalculator gc = getGamegridCalculator();
		return new Dimension(gc.getTotalGridWidth(), gc.getTotalGridHeight());
	}
	
	@Override
	/**
	 * at each tick the score is updated and all elements are repainted
	 */
	public void doTick() {
		
		super.revalidate();
		super.repaint();
		
		menu.updateScores();
		menu.revalidate();
		menu.repaint();
	}
	
	
}
