package com.github.joukojo.testgame.world.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class WorldCoreFactory {
	private final static Logger LOG = LoggerFactory
			.getLogger(WorldCoreFactory.class);

	
	private WorldCoreFactory() {
		// no instantiate 
	}
	private static WorldCore INSTANCE;

	public static WorldCore getWorld() {
		synchronized (WorldCoreFactory.class) {
			if (INSTANCE == null) {
				LOG.debug("creating world instance");
				INSTANCE = new WorldCoreImpl();
			}
		}
		return INSTANCE;
	}

}
