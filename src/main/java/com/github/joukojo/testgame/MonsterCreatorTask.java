package com.github.joukojo.testgame;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.joukojo.testgame.world.core.Moveable;
import com.github.joukojo.testgame.world.core.WorldCore;
import com.github.joukojo.testgame.world.core.WorldCoreFactory;

public class MonsterCreatorTask implements Runnable {

	private final Logger LOG = LoggerFactory.getLogger(MonsterCreatorTask.class);

	private volatile boolean isrunning;

	public void incrementLevel() {
		WorldCore worldCore = WorldCoreFactory.getWorld();
		Player player = (Player) worldCore.getMoveable("player");
		if (player != null) {
			player.level += 1;
		}

	}

	public void createMonsters() {
		WorldCore worldCore = WorldCoreFactory.getWorld();
		Random random = new Random();
		Player player = (Player) worldCore.getMoveable("player");
		LOG.debug("current level: {}", player.level);
		for (int i = 0; i < player.level * 2; i++) {
			final Monster monster = new Monster();
			monster.locationX = random.nextInt(Constants.SCREEN_WIDTH - 100);
			monster.locationY = 0;

			worldCore.addMoveable("monsters", monster);
			try {

				int delta = player.level * 150;
				if (delta < 2000) {
					Thread.sleep(2000 - delta);
				}
			} catch (InterruptedException e) {
				LOG.warn("Thread is interrupted: {}", e.getMessage());
				Thread.interrupted();
			}
		}
	}

	public void run() {
		setIsrunning(true);
		createMonsters();
		
		while (isIsrunning()) {

			clearMonsterOutsideOfViewPoint();
		}
	}

	public void clearMonsterOutsideOfViewPoint() {
		WorldCore worldCore = WorldCoreFactory.getWorld();

		List<Moveable> moveableMonsters = worldCore.getMoveableObjects("monsters");
		if (moveableMonsters != null && !moveableMonsters.isEmpty()) {
			for (final Moveable moveable : moveableMonsters) {
				if (moveable.isOutside(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT)) {

					if (!moveable.isDestroyed()) {
						// The monster has reached the down -> decrease health
						final Player player = (Player) worldCore.getMoveable("player");
						player.health -= 10;
					}

					moveable.setDestroyed(true);

				}
			}

		} else {
			incrementLevel();
			createMonsters();
		}

		final List<Moveable> bullets = worldCore.getMoveableObjects("bullets");

		if (bullets != null && !bullets.isEmpty()) {
			for (Moveable moveable : bullets) {
				if (moveable != null) {
					if (moveable.isOutside(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT)) {
						moveable.setDestroyed(true);
					}
				}
			}
		}

	}

	public boolean isIsrunning() {
		return isrunning;
	}

	public void setIsrunning(boolean isrunning) {
		this.isrunning = isrunning;
	}
}
