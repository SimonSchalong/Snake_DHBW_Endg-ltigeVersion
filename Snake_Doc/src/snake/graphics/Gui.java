package snake.graphics;

import java.awt.BorderLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Stack;

import javax.swing.JFrame;
import javax.swing.JLayeredPane;

import snake.Game;
import snake.Game.Gamestatus;
import snake.gameplay.Direction;
/**
 * this class manages the different menus and the user interaction
 */
public class Gui implements KeyListener{
	
	public final static int HEIGHT = 600, WIDTH = 900;
	
	private JFrame mainFrame;
	private JLayeredPane pane;
	
	private GridBagConstraints gbc;
	
	/**
	 * creates and configures the game window
	 */
	public void create() {
		mainFrame = new JFrame("Snake");
		mainFrame.setSize(WIDTH, HEIGHT);
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		mainFrame.setLocationRelativeTo(null);//center on screen
		mainFrame.setResizable(false);
		mainFrame.setVisible(true);
		
		//set border layout to wrap layered pane around
		mainFrame.setLayout(new BorderLayout());
		
		pane = new JLayeredPane();
		pane.setSize(WIDTH, HEIGHT);
		
		mainFrame.addKeyListener(this);
		
		mainFrame.add(pane, BorderLayout.CENTER);
		
		GridBagLayout gbl = new GridBagLayout();
		gbc = new GridBagConstraints();
		pane.setLayout(gbl);
		gbc.insets = new Insets(5, 5, 5, 5);
		gbc.gridx = 0;
		gbc.gridy = 0;
		gbc.gridwidth = 1;
		gbc.gridheight = 1;
		
		setMenu(new MainMenu(), false);
	}
	
	private Stack<Displayable> menus = new Stack<Displayable>();
	
	public Displayable getCurrentMenu() {
		return menus.peek();
	}
	/**
	 * sets the currently displayed menu to the given value and clears all old contents from the pane if wanted
	 * @param menu the new menu that will be displayed
	 * @param clearAllContents 
	 */
	public void setMenu(Displayable menu, boolean clearAllContents) {
		if(clearAllContents) {
			pane.removeAll();
			menus.clear();
		}
		if(menus.size() > 0) {
			pane.moveToBack(getCurrentMenu());
		}
		menus.push(menu);
		pane.add(menu, gbc, menu.getLayer());
		pane.moveToFront(menu);
		//update focus after button is pressed
		mainFrame.setFocusable(true);
		mainFrame.requestFocusInWindow();
		//redraw the new menu since contents may change
		mainFrame.revalidate();
		mainFrame.repaint();
	}
	/**
	 * removes the menu displayed on top(e.g. pause) and keeps all other menus below
	 */
	public void restoreMenu() {
		pane.remove(getCurrentMenu());
		menus.pop();
		pane.revalidate();
		pane.repaint();
	}
	
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	/**
	 * First check if the game is running or prepared, then react accordingly to the keystrokes
	 */
	@Override
	public void keyPressed(KeyEvent e) {
		boolean running, prepared;
		running = Game.getGameStatus().equals(Gamestatus.RUNNING);
		prepared = running || Game.getGameStatus().equals(Gamestatus.PREPARED);
		if(!prepared) {
			return;
		}
		int keyCode = e.getKeyCode();
		Direction newDir = null;
		switch (keyCode) {
		case KeyEvent.VK_UP:
			newDir = Direction.UP;
			break;
		case KeyEvent.VK_DOWN:
			newDir = Direction.DOWN;
			break;
		case KeyEvent.VK_LEFT:
			newDir = Direction.LEFT;
			break;
		case KeyEvent.VK_RIGHT:
			newDir = Direction.RIGHT;
			break;
		case KeyEvent.VK_ESCAPE:
			Game.pauseGame();
			return;
		}
		if(!running) {
			Game.startGamePlay();
		}
		if (newDir != null) {
			Game.getRunningGame().changeSnakeDirection(newDir);
		}

	}

	@Override
	public void keyReleased(KeyEvent e) {
	}

	
}
