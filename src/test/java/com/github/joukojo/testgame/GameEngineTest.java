package com.github.joukojo.testgame;

import static org.junit.Assert.*;


import java.util.concurrent.ExecutorService;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;

import org.mockito.runners.MockitoJUnitRunner;

import com.github.joukojo.testgame.world.core.WorldCoreTask;
import com.github.joukojo.testgame.world.graphics.GraphicEngineWorker;

@RunWith(MockitoJUnitRunner.class)
public class GameEngineTest {
	@Mock
	private CollisionDetectionWorker collisionDetector;

	@Mock
	private MonsterCreatorTask creatorTask;

	@Mock
	private ExecutorService executorService;

	@Mock
	private GraphicEngineWorker gEngineWorker;

	@Mock
	private MonsterCreatorTask monsterCreatorTask;

	@Mock
	private WorldCoreTask worldCoreTask;
	private GameEngine gameEngine;

	@Before
	public void createGameEngine() throws Exception {
		gameEngine = new GameEngine();
		gameEngine.setCollisionDetector(collisionDetector);
		gameEngine.setCreatorTask(creatorTask);
		gameEngine.setExecutorService(executorService);
		gameEngine.setGEngineWorker(gEngineWorker);
		gameEngine.setMonsterCreatorTask(monsterCreatorTask);
		gameEngine.setWorldCoreTask(worldCoreTask);
	}

	@Test
	public void testInit() {
		GameEngine gameEngine = new GameEngine() {
			
		};
		gameEngine.init();
		
		assertNotNull("collisionDetector is null", gameEngine.getCollisionDetector());
	}

	@Test
	public void testStartGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testStopGame() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetInstance() {
		fail("Not yet implemented");
	}

}
