package com.github.joukojo.testgame.world.graphics;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class GraphicEngineWorkerTest {
	@Mock
	private GraphicEngine graphicEngine;

	@Mock
	private boolean running;
	private GraphicEngineWorker graphicEngineWorker;

	@Before
	public void createGraphicEngineWorker() throws Exception {
		graphicEngineWorker = new GraphicEngineWorker(graphicEngine);
		graphicEngineWorker.setRunning(running);
	}

	@Test
	public void testGraphicEngineWorker() {
		fail("Not yet implemented");
	}

	@Test
	public void testRun() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsRunning() {
		fail("Not yet implemented");
	}

}
