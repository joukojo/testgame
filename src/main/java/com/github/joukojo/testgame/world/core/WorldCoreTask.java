package com.github.joukojo.testgame.world.core;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * This task makes the world go around
 * 
 * @author joukojo
 * 
 */
public class WorldCoreTask implements Runnable {

	private transient volatile boolean isWorldRunning;
	private final static Logger LOG = LoggerFactory.getLogger(WorldCoreTask.class);

	public WorldCoreTask() {
		isWorldRunning = true;
	}

	@Override
	public void run() {
		
		final int existingPriority = Thread.currentThread().getPriority();
		final Thread currentThread = Thread.currentThread();
		currentThread.setPriority(Thread.MIN_PRIORITY);

		while (isRunning()) {
			LOG.debug("and world starts to move");
			final List<String> objectNames = WorldCoreFactory.getWorld().getMoveableObjectNames();

			for (final String objectName : objectNames) {

				final List<Moveable> moveableObjects = WorldCoreFactory.getWorld().getMoveableObjects(objectName);
				if (LOG.isDebugEnabled()) {
					LOG.debug("the size of the moveable objects:{}:{}", objectName, moveableObjects.size());
				}
				moveObjects(moveableObjects);

			}

			// clean moveables at end
			WorldCoreFactory.getWorld().cleanMoveables();

			LOG.debug("and world has moved");
			try {
				LOG.trace("Sleeping for 20 msecs");
				Thread.sleep(20);
				LOG.trace("Slept.");
			} catch (final InterruptedException e) {
				Thread.currentThread().interrupt();
			}

		}

		currentThread.setPriority(existingPriority);

	}

	private void moveObjects(final List<Moveable> moveableObjects) {
		for (final Moveable moveable : moveableObjects) {
			LOG.trace("moving object: {}", moveable);
			moveable.move();
		}
	}

	public boolean isRunning() {
		return isWorldRunning;
	}

	public void setRunning(final boolean isRunning) {
		this.isWorldRunning = isRunning;
	}
}
