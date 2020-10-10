package snake.gameplay;
import java.util.ArrayList;
import java.util.List;

import snake.Game;

/**
 * Observer structure
 */
public class GameTickExecutor implements Runnable {

	private List<GameTickListener> tasks = new ArrayList<GameTickListener>();
	
	/**
	 * check if game paused, otherwise execute tasks for every game tick
	 */
	@Override
	public void run() {
		if(Game.isPaused()) {
			return;
		}
		for(GameTickListener task : tasks) {
			task.doTick();
		}
	}
	/**
	 * add 'task' to the list
	 * @param task a job to be executed every game tick
	 */
	public void addTask(GameTickListener task) {
		tasks.add(task);
	}
	/**
	 * remove 'task' from the list
	 * @param task a job to be executed every game tick
	 */
	public void remove(GameTickListener task) {
		tasks.remove(task);
	}
	/**
	 * the default gametick contianing the default front- and backend
	 */
	public final static GameTickExecutor DEFAULT_GAMETICK = new GameTickExecutor();
	static {
		DEFAULT_GAMETICK.addTask(Game.getRunningGame());
		DEFAULT_GAMETICK.addTask(Game.getRunningDrawBoard());
	}


}
