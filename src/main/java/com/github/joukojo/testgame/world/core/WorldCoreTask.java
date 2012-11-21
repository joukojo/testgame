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

	private volatile boolean isRunning;
	private final static Logger LOG = LoggerFactory.getLogger(WorldCoreTask.class);

	public WorldCoreTask() {
		setRunning(true);
	}

	@Override
	public void run() {
		final WorldCore worldCore = WorldCoreFactory.getWorld();
		final int existingPriority = Thread.currentThread().getPriority();
		final Thread currentThread = Thread.currentThread();
		currentThread.setPriority(Thread.MIN_PRIORITY);

		while (isRunning()) {
			LOG.debug("and world starts to move");
			final List<String> objectNames = worldCore.getMoveableObjectNames();

			for (final String objectName : objectNames) {

				final List<Moveable> moveableObjects = worldCore.getMoveableObjects(objectName);
				if (LOG.isDebugEnabled()) {
					final Object params[] = { objectName, moveableObjects.size() };
					LOG.debug("the size of the moveable objects:{}:{}", params);
				}
				moveObjects(moveableObjects);

			}

			// clean moveables at end
			worldCore.cleanMoveables();

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
		return isRunning;
	}

	public void setRunning(final boolean isRunning) {
		this.isRunning = isRunning;
	}
}
