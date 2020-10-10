package snake.gameplay;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
/**
 * Defines and modifies the tickrate
 */
public class GameClock {
	
	private final static long TICK_TIME = 500;
	
	private ScheduledExecutorService executor;
	private Runnable tick;
	private long tickTime;
	/*
	 * creates a new gameclock and initiates the scheduler 
	 */
	public GameClock() {
		executor = Executors.newSingleThreadScheduledExecutor();
	}
	/**
	 * starts an scheduled task with the given tick-task
	 * @param gameTick the task that has to run on every scheduler execute
	 */
	public void start(Runnable gameTick) {
		tick = gameTick;
		tickTime = getTickTime();
		executor.scheduleAtFixedRate(tick, 0, tickTime, TimeUnit.MILLISECONDS);
	}
	/**
	 * Controls the speed of the snake
	 * @param val the speed modifier in percent. e.g. 200 means twice as fast
	 */
	public void adjustTickTime(double val) {
		long dynTime = (long)(tickTime*(1/val));
		executor.shutdown();
		executor = Executors.newSingleThreadScheduledExecutor();
		executor.scheduleAtFixedRate(tick, dynTime, dynTime, TimeUnit.MILLISECONDS);
	}
	
	
	public void stop() {
		executor.shutdown();
	}
	/*
	 * Divide the TICK_TIME with the speed modifier to speed up or slow down the game accordingly to the level's value
	 * @return returns the modified tick time
	 */
	private long getTickTime() {
		long tick_time = (long) (TICK_TIME / Gameplay.getInstance().getLevel().getSpeedModifier());
		return tick_time;
	}
	
}
