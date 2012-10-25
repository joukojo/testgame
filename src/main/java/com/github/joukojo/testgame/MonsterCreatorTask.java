package com.github.joukojo.testgame;

import java.util.List;
import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.joukojo.testgame.world.core.Moveable;
import com.github.joukojo.testgame.world.core.WorldCore;
import com.github.joukojo.testgame.world.core.WorldCoreFactory;

public class MonsterCreatorTask implements Runnable {

	private final Logger LOG = LoggerFactory
			.getLogger(MonsterCreatorTask.class);

	int level = 1;

	public void setLevel(int level) {
		this.level = level;
	}

	public void incrementLevel() {
		WorldCore worldCore = WorldCoreFactory.getWorld();
		Player player  = (Player) worldCore.getMoveable("player");
		player.level += 1; 
		

	}

	public void createMonsters() {
		WorldCore worldCore = WorldCoreFactory.getWorld();
		Random random = new Random();
		Player player  = (Player) worldCore.getMoveable("player");
		LOG.debug("current level: {}", level);
		for (int i = 0; i < player.level * 2; i++) {
			final Monster monster = new Monster();
			monster.locationX = random.nextInt(Constants.SCREEN_WIDTH);
			monster.locationY = 0;

			worldCore.addMoveable("monsters", monster);
			try {
				Thread.sleep(4000 - (player.level * 150));
			} catch (InterruptedException e) {
				LOG.warn("Thread is interrupted: {}", e.getMessage());
				Thread.interrupted();
			}
		}
	}

	public void run() {
		createMonsters();
		while (true) {

			clearMonsterOutsideOfViewPoint();
		}
	}

	public void clearMonsterOutsideOfViewPoint() {
		WorldCore worldCore = WorldCoreFactory.getWorld();

		List<Moveable> moveableMonsters = worldCore
				.getMoveableObjects("monsters");
		if (moveableMonsters != null && !moveableMonsters.isEmpty()) {
			for (Moveable moveable : moveableMonsters) {
				if (moveable.isOutside(Constants.SCREEN_WIDTH, Constants.SCREEN_HEIGHT)) {
					
					if( !moveable.isDestroyed()) {
						// The monster has reached the down -> decrease health
						Player player  = (Player) worldCore.getMoveable("player");
						player.health -= 10;
					}
					
					moveable.setDestroyed(true);
					
					
				}
			}

		} else {
			incrementLevel();
			createMonsters();
		}

		List<Moveable> bullets = worldCore.getMoveableObjects("bullets");

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
}
