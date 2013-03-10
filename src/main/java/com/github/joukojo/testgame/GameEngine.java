package com.github.joukojo.testgame;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.joukojo.testgame.world.core.WorldCore;
import com.github.joukojo.testgame.world.core.WorldCoreFactory;
import com.github.joukojo.testgame.world.core.WorldCoreTask;
import com.github.joukojo.testgame.world.graphics.GraphicEngine;
import com.github.joukojo.testgame.world.graphics.GraphicEngineWorker;

public class GameEngine {
	private final static Logger LOG = LoggerFactory.getLogger(GameEngine.class);
	

	private MonsterCreatorTask creatorTask;
	private CollisionDetectionWorker collisionDetector;
	private GraphicEngineWorker gEngineWorker;
	private WorldCoreTask worldCoreTask;

	private ExecutorService executorService;
	private static GameEngine instance;

	public void init() {
		
		final GraphicsEnvironment env = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		final GraphicsDevice[] devices = env.getScreenDevices();
		
	
		final GraphicsConfiguration graphicsEngine = devices[0].getDefaultConfiguration();
	
		final GraphicEngine engine = new GraphicEngine(graphicsEngine);

		final WorldCore worldCore = WorldCoreFactory.getWorld();
		final Player player = new Player();
		player.setPositionX(500);
		player.setPositionY(100);

		worldCore.addMoveable(Constants.PLAYER, player);
		setMonsterCreatorTask(new MonsterCreatorTask());

		setCollisionDetector(new CollisionDetectionWorker());

		setgEngineWorker(new GraphicEngineWorker(engine));

		setWorldCoreTask(new WorldCoreTask());

		setExecutorService(Executors.newCachedThreadPool());

	}

	public void startGame() {
		LOG.debug("starting up the game engine");
		getExecutorService().execute(getMonsterCreatorTask());
		getExecutorService().execute(getCollisionDetector());
		getExecutorService().execute(getgEngineWorker());
		getExecutorService().execute(getWorldCoreTask());

	}

	public void stopGame() {
		getMonsterCreatorTask().setIsrunning(false);
		getCollisionDetector().setRunning(false);
		getgEngineWorker().setRunning(false);
		getWorldCoreTask().setRunning(false);

		getExecutorService().shutdownNow();

	}

	public static  GameEngine getInstance() {

		synchronized (GameEngine.class) {
			if (instance == null) {
				instance = new GameEngine();
			}
		}

		return instance;
	}

	public MonsterCreatorTask getMonsterCreatorTask() {
		return getCreatorTask();
	}

	public void setMonsterCreatorTask(final MonsterCreatorTask creatorTask) {
		this.setCreatorTask(creatorTask);
	}

	public CollisionDetectionWorker getCollisionDetector() {
		return collisionDetector;
	}

	public void setCollisionDetector(final CollisionDetectionWorker collisionDetector) {
		this.collisionDetector = collisionDetector;
	}

	public WorldCoreTask getWorldCoreTask() {
		return worldCoreTask;
	}

	public void setWorldCoreTask(final WorldCoreTask worldCoreTask) {
		this.worldCoreTask = worldCoreTask;
	}

	public ExecutorService getExecutorService() {
		return executorService;
	}

	public void setExecutorService(final ExecutorService executorService) {
		this.executorService = executorService;
	}

	public MonsterCreatorTask getCreatorTask() {
		return creatorTask;
	}

	public void setCreatorTask(final MonsterCreatorTask creatorTask) {
		this.creatorTask = creatorTask;
	}

	public GraphicEngineWorker getgEngineWorker() {
		return gEngineWorker;
	}

	public void setgEngineWorker(final GraphicEngineWorker gEngineWorker) {
		this.gEngineWorker = gEngineWorker;
	}

}
