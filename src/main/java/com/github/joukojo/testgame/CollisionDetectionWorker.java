package com.github.joukojo.testgame;

import java.util.List;

import org.slf4j.LoggerFactory;

import org.slf4j.Logger;

import com.github.joukojo.testgame.world.core.Moveable;
import com.github.joukojo.testgame.world.core.WorldCore;
import com.github.joukojo.testgame.world.core.WorldCoreFactory;

public class CollisionDetectionWorker implements Runnable {

	private final static Logger LOG = LoggerFactory
			.getLogger(CollisionDetectionWorker.class);

	@Override
	public void run() {
		WorldCore worldCore = WorldCoreFactory.getWorld();
		while (true) {
			List<Moveable> bullets = worldCore.getMoveableObjects("bullets");

			if (bullets != null && !bullets.isEmpty()) {
				for (Moveable moveableBullet : bullets) {
					Bullet bullet = (Bullet) moveableBullet;
					List<Moveable> monsters = worldCore
							.getMoveableObjects("monsters");
					if (monsters != null && !monsters.isEmpty()) {
						for (Moveable moveableMonster : monsters) {
							Monster monster = (Monster) moveableMonster;
							// FIXME do the location calculation better
							if (monster != null && bullet != null) {
								if (!monster.isDestroyed() && !bullet.isDestroyed()) {
									int deltaX = Math.abs(bullet.locationX
											- monster.locationX);
									int deltaY = Math.abs(bullet.locationY
											- monster.locationY);
									LOG.trace("deltaX {}", deltaX);
									LOG.trace("deltaY {}", deltaY);
									if (deltaX < 20 && deltaY < 20) {
										LOG.debug("we have a hit");
										monster.setDestroyed(true);
										bullet.setDestroyed(true);
									}
								}
							}
						}
					}
				}
			}

			// FIXME do the player collision detection here

			Thread.yield();
		}

	}

}
