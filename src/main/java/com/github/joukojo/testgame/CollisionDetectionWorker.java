package com.github.joukojo.testgame;

import java.io.Serializable;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.joukojo.testgame.world.core.Moveable;
import com.github.joukojo.testgame.world.core.WorldCore;
import com.github.joukojo.testgame.world.core.WorldCoreFactory;

public class CollisionDetectionWorker implements Runnable, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private final static Logger LOG = LoggerFactory.getLogger(CollisionDetectionWorker.class);
	private volatile boolean running;

	@Override
	public void run() {
		
		setRunning(true);
		while (isRunning()) {
			handleBulletCollisions(WorldCoreFactory.getWorld());

			handlePlayerCollisions(WorldCoreFactory.getWorld());
			// as this is a never ending loop, 
			// temporarily pause and allow other threads to execute
			Thread.yield();
		}

	}

	protected void handleBulletCollisions(final WorldCore worldCore) {
		final List<Moveable> bullets = worldCore
				.getMoveableObjects(Constants.BULLETS);

		if (bullets != null && !bullets.isEmpty()) {
			for (final Moveable moveableBullet : bullets) {
				final Bullet bullet = (Bullet) moveableBullet;
				if (bullet != null) {
					isBulletInCollisionWithMonster(worldCore, bullet);
				}
			}
		}
	}

	protected void handlePlayerCollisions(final WorldCore worldCore) {
		final Player player = (Player) worldCore.getMoveable(Constants.PLAYER);
		if (player != null) {
			final List<Moveable> monsters = worldCore
					.getMoveableObjects(Constants.MONSTERS);

			if (monsters != null) {
				for (final Moveable moveable : monsters) {
					handlePlayerCollision(player, moveable);

				}
			}
		}
	}

	private void handlePlayerCollision(final Player player,
			final Moveable moveable) {
		final Monster monster = (Monster) moveable;
		if (monster != null && !monster.isDestroyed()) {
			isPlayerAndMonsterInCollision(player, monster);
		}
	}

	private void isBulletInCollisionWithMonster(final WorldCore worldCore,
			final Bullet bullet) {
		final List<Moveable> monsters = worldCore
				.getMoveableObjects(Constants.MONSTERS);
		if (monsters != null && !monsters.isEmpty()) {
			for (final Moveable moveableMonster : monsters) {
				final Monster monster = (Monster) moveableMonster;
				if (monster != null && bullet != null) {
					isBulletAndMonsterInCollision(worldCore, bullet, monster);
				}
			}
		}
	}

	protected void isBulletAndMonsterInCollision(final WorldCore worldCore,
			final Bullet bullet, final Monster monster) {
		if (!monster.isDestroyed() && !bullet.isDestroyed()) {

			// correct the monster location
			final int monsterRealX = monster.getLocationX() + 34;
			final int monsterRealY = monster.getLocationY() + 34;

			final int deltaX = Math.abs(bullet.getLocationX() - monsterRealX);
			final int deltaY = Math.abs(bullet.getLocationY() - monsterRealY);
			if (LOG.isTraceEnabled()) {
				LOG.trace("deltaX {}", deltaX);
				LOG.trace("deltaY {}", deltaY);
			}
			if (deltaX < 30 && deltaY < 30) {
				LOG.debug("we have a hit");
				monster.setDestroyed(true);
				bullet.setDestroyed(true);

				final Player player = (Player) worldCore
						.getMoveable(Constants.PLAYER);
				player.setScore(player.getScore() + 100);
			}
		}
	}

	protected void isPlayerAndMonsterInCollision(final Player player,
			final Monster monster) {
		if (!monster.isDestroyed()) {

			// correct the monster location
			final int monsterRealX = monster.getLocationX() + 34;
			final int monsterRealY = monster.getLocationY() + 13;

			final int deltaX = Math.abs(player.getPositionX() - monsterRealX);
			final int deltaY = Math.abs(player.getPositionY() - monsterRealY);

			if (LOG.isTraceEnabled()) {
				final Object params[] = { deltaX, deltaY };
				LOG.trace("delta values(x/y) : ({}/{})", params);
			}
			if (deltaX < 20 && deltaY < 20) {
				LOG.trace("we have a hit with monster");
				monster.setDestroyed(true);

				player.setHealth(player.getHealth() - 1);
			}
		}
	}

	public boolean isRunning() {
		return running;
	}

	public void setRunning(final boolean isRunning) {
		this.running = isRunning;
	}

}
