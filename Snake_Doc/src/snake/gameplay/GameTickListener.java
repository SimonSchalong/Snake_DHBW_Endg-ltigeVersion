package snake.gameplay;

/**
 * This interface represents an action that is executed on every gametick
 * An implementing sub-class has to be registered in the GameTickExecutor
 */
public interface GameTickListener {
	
	/**
	 * The Gametick-Event. Executes on every game tick
	 */
	public void doTick();
	
}
