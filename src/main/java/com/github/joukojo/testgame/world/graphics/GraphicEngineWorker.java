package com.github.joukojo.testgame.world.graphics;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GraphicEngineWorker implements Runnable {

	private GraphicEngine graphicEngine;
	private volatile boolean isRunning;
	private final Logger LOG = LoggerFactory.getLogger(GraphicEngineWorker.class);

	public GraphicEngineWorker(GraphicEngine graphicEngine) {
		this.graphicEngine = graphicEngine;
		setRunning(true);		
	}
	
	@Override
	public void run() {

		while (isRunning()) {
			LOG.debug("drawing objects");
			long start = System.currentTimeMillis();
			graphicEngine.drawObjects();
			// Let the OS have a little time...
			long end = System.currentTimeMillis();
			
			long delta = end-start; 
			
			if( delta > 100L ) {
				LOG.warn("the graphic engine draw objects in {}ms", delta);
			}
			LOG.debug("thread yield");
			Thread.yield();

		}
		
	}

	public boolean isRunning() {
		return isRunning;
	}

	public void setRunning(boolean isRunning) {
		this.isRunning = isRunning;
	}

}
