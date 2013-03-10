package com.github.joukojo.testgame;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.joukojo.testgame.world.core.Moveable;
import com.github.joukojo.testgame.world.core.WorldCore;
import com.github.joukojo.testgame.world.core.WorldCoreFactory;

public class MonsterCreatorTask implements Runnable {

	private final static Logger LOG = LoggerFactory.getLogger(MonsterCreatorTask.class);

	private volatile boolean isrunning;

	private final Random random = new Random();

	public void incrementLevel() {
		final WorldCore worldCore = WorldCoreFactory.getWorld();
		final Player player = (Player) worldCore.getMoveable(Constants.PLAYER);
		if (player != null) {
			player.setLevel(player.getLevel() + 1);
		}

	}

	public void createMonsters() {
		final WorldCore worldCore = WorldCoreFactory.getWorld();

		final Player player = (Player) worldCore.getMoveable(Constants.PLAYER);
		final int level = player.getLevel();
		LOG.debug("current level: {}", level);
		for (int i = 0; i < level * 2; i++) {
			final Monster monster = new Monster();
			monster.setLocationX(getRandom().nextInt(DisplayConfiguration.getInstance().getWidth()- 100));
			monster.setLocationY(0);

			worldCore.addMoveable(Constants.MONSTERS, monster);
			try {

				final int delta = level * 150;
				if (delta < 2000) {
					Thread.sleep(2000 - delta);
				}
			} catch (final InterruptedException e) {
				LOG.warn("Thread is interrupted: {}", e.getMessage());
				Thread.interrupted();
			}
		}
	}

	@Override
	public void run() {
		setIsrunning(true);
		createMonsters();

		while (isIsrunning()) {

			clearMonsterOutsideOfViewPoint();
		}
	}

	public void clearMonsterOutsideOfViewPoint() {
		final WorldCore worldCore = WorldCoreFactory.getWorld();

		final List<Moveable> moveableMonsters = worldCore.getMoveableObjects(Constants.MONSTERS);
		if (moveableMonsters != null && !moveableMonsters.isEmpty()) {
			for (final Moveable moveable : moveableMonsters) {
				if (moveable.isOutside(DisplayConfiguration.getInstance().getWidth(), DisplayConfiguration.getInstance().getWidth())) {

					if (!moveable.isDestroyed()) {
						// The monster has reached the down -> decrease health
						final Player player = (Player) worldCore.getMoveable(Constants.PLAYER);
						player.setHealth(player.getHealth() - 10);
					}

					moveable.setDestroyed(true);
				}
			}

		} else {
			incrementLevel();
			createMonsters();
		}

		final List<Moveable> bullets = worldCore.getMoveableObjects(Constants.BULLETS);

		if (bullets != null && !bullets.isEmpty()) {
			for (final Moveable moveable : bullets) {
				if (moveable != null) {
					if (moveable.isOutside(DisplayConfiguration.getInstance().getWidth(), DisplayConfiguration.getInstance().getHeight())) {
						moveable.setDestroyed(true);
					}
				}
			}
		}

	}

	public boolean isIsrunning() {
		return isrunning;
	}

	public void setIsrunning(final boolean isrunning) {
		this.isrunning = isrunning;
	}

	public Random getRandom() {
		return random;
	}
}
