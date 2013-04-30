package com.github.joukojo.testgame;

import java.awt.GraphicsConfiguration;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;
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
	private static GameEngine INSTANCE;

	public void init() {
		
		final GraphicsConfiguration graphicsEngine = createGraphicConfiguration();
	
		final GraphicEngine engine = new GraphicEngine(graphicsEngine);

		final WorldCore worldCore = WorldCoreFactory.getWorld();
		final Player player = new Player();
		player.setPositionX(500);
		player.setPositionY(100);

		worldCore.addMoveable(Constants.PLAYER, player);
		setMonsterCreatorTask(new MonsterCreatorTask());

		setCollisionDetector(new CollisionDetectionWorker());

		setGEngineWorker(new GraphicEngineWorker(engine));

		setWorldCoreTask(new WorldCoreTask());

		setExecutorService(Executors.newCachedThreadPool());

	}

	protected GraphicsConfiguration createGraphicConfiguration() {
		final GraphicsEnvironment env = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		final GraphicsDevice[] devices = env.getScreenDevices();
		
	
		return devices[0].getDefaultConfiguration();
		
	}

	public void startGame() {
		LOG.debug("starting up the game engine");
		getExecutorService().execute(getMonsterCreatorTask());
		getExecutorService().execute(getCollisionDetector());
		getExecutorService().execute(getGEngineWorker());
		getExecutorService().execute(getWorldCoreTask());

	}

	public void stopGame() {
		getMonsterCreatorTask().setIsrunning(false);
		getCollisionDetector().setRunning(false);
		getGEngineWorker().setRunning(false);
		getWorldCoreTask().setRunning(false);

		getExecutorService().shutdownNow();

	}

	public static  GameEngine getInstance() {

		synchronized (GameEngine.class) {
			if (INSTANCE == null) {
				INSTANCE = new GameEngine();
			}
		}

		return INSTANCE;
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

	public GraphicEngineWorker getGEngineWorker() {
		return gEngineWorker;
	}

	public void setGEngineWorker(final GraphicEngineWorker gEngineWorker) {
		this.gEngineWorker = gEngineWorker;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

	public GraphicEngineWorker getgEngineWorker() {
		return gEngineWorker;
	}

	public void setgEngineWorker(GraphicEngineWorker gEngineWorker) {
		this.gEngineWorker = gEngineWorker;
	}

}
