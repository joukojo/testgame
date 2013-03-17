package com.github.joukojo.testgame.world.graphics;

import java.io.Serializable;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphicEngineWorker implements Runnable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private transient final GraphicEngine graphicEngine;
	private transient volatile boolean isRunning;
	private final static Logger LOG = LoggerFactory.getLogger(GraphicEngineWorker.class);

	public GraphicEngineWorker(final GraphicEngine graphicEngine) {
		this.graphicEngine = graphicEngine;
		isRunning = true;
	}

	@Override
	public void run() {

		while (isRunning()) {
			LOG.debug("drawing objects");
			final long start = System.currentTimeMillis();
			graphicEngine.drawObjects();
			// Let the OS have a little time...
			final long end = System.currentTimeMillis();

			final long delta = end - start;

			if (delta > 100L) {
				LOG.warn("the graphic engine draw objects in {}ms", delta);
			} else {
				LOG.debug("the graphic engine draw objects in {}ms", delta);
			}
			LOG.debug("thread yield");
			Thread.yield();

		}

	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(final boolean isRunning) {
		this.isRunning = isRunning;
	}

}
