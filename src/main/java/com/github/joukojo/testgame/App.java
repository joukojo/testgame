package com.github.joukojo.testgame;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.joukojo.testgame.world.core.WorldCoreFactory;

/**
 * Hello world!
 * 
 */
public final class App {

	private App() {
		// do not instantiate
	}
	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(final String[] args) throws InterruptedException {

		while (true) {
			GameEngine.getInstance().init();
			LOG.debug("game engine initialized");

			JOptionPane.showMessageDialog(null, "Ready to play");
			LOG.debug("starting the game engine");
			GameEngine.getInstance().startGame();

			final Player player = (Player) WorldCoreFactory.getWorld().getMoveable(Constants.PLAYER);

			while (!player.isDestroyed()) {
				Thread.sleep(200L);
				Thread.yield();
			}

			GameEngine.getInstance().stopGame();
			JOptionPane.showMessageDialog(null,
					"Game Over!\nScore: " + player.getScore());
			WorldCoreFactory.getWorld().resetWorld();
		}

	}
}
