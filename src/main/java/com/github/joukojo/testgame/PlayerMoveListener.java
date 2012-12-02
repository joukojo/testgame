package com.github.joukojo.testgame;

import java.awt.Point;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.joukojo.testgame.world.core.WorldCore;
import com.github.joukojo.testgame.world.core.WorldCoreFactory;

public class PlayerMoveListener implements MouseMotionListener, MouseListener {
	private final static Logger LOG = LoggerFactory
			.getLogger(PlayerMoveListener.class);

	@Override
	public void mouseClicked(final MouseEvent e) {
		// Point locationOnScreen = e.getLocationOnScreen();
		// LOG.debug("mouse clicked");
		// LOG.debug("location x:" + locationOnScreen.x);
		// LOG.debug("location y:" + locationOnScreen.y);

	}

	@Override
	public void mousePressed(final MouseEvent e) {
		final Point locationOnScreen = e.getLocationOnScreen();
		if (LOG.isDebugEnabled()) {
			LOG.debug("mouse pressed");
			LOG.debug("location x: {}", locationOnScreen.x);
			LOG.debug("location y: {}", locationOnScreen.y);
		}

		final WorldCore worldCore = WorldCoreFactory.getWorld();

		final Player player = (Player) worldCore.getMoveable(Constants.PLAYER);
		if (player != null) {
			final Bullet bullet = new Bullet();
			// bullet.setDirectionX(bulletX);
			bullet.setDirectionY(-5);

			bullet.setLocationX(player.getPositionX());
			bullet.setLocationY(player.getPositionY());
			worldCore.addMoveable(Constants.BULLETS, bullet);
		}

	}

	@Override
	public void mouseReleased(final MouseEvent e) {
		// Point locationOnScreen = e.getLocationOnScreen();
		// LOG.debug("mouse released");
		// LOG.debug("location x:" + locationOnScreen.x);
		// LOG.debug("location y:" + locationOnScreen.y);

	}

	@Override
	public void mouseEntered(final MouseEvent e) {
		// Point locationOnScreen = e.getLocationOnScreen();
		// LOG.debug("mouse entered");
		// LOG.debug("location x:" + locationOnScreen.x);
		// LOG.debug("location y:" + locationOnScreen.y);

	}

	@Override
	public void mouseExited(final MouseEvent e) {
		// Point locationOnScreen = e.getPoint();
		// LOG.debug("mouse exited");
		// LOG.debug("location x:" + locationOnScreen.x);
		// LOG.debug("location y:" + locationOnScreen.y);
		//
		//
	}

	@Override
	public void mouseDragged(final MouseEvent e) {
		// Point locationOnScreen = e.getLocationOnScreen();
		// LOG.debug("mouse dragged");
		// LOG.debug("location x:" + locationOnScreen.x);
		// LOG.debug("location y:" + locationOnScreen.y);
		final WorldCore worldCore = WorldCoreFactory.getWorld();
		final Player player = (Player) worldCore.getMoveable(Constants.PLAYER);
		if (player != null) {
			player.setPoint(e.getPoint());
		}
	}

	@Override
	public void mouseMoved(final MouseEvent e) {
		final WorldCore worldCore = WorldCoreFactory.getWorld();
		final Player player = (Player) worldCore.getMoveable(Constants.PLAYER);
		if (player != null) {
			player.setPoint(e.getPoint());
		}

	}

}
