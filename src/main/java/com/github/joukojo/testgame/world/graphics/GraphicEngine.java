package com.github.joukojo.testgame.world.graphics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsConfiguration;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.joukojo.testgame.DisplayConfiguration;
import com.github.joukojo.testgame.Player;
import com.github.joukojo.testgame.PlayerMoveListener;
import com.github.joukojo.testgame.world.core.Drawable;
import com.github.joukojo.testgame.world.core.Moveable;
import com.github.joukojo.testgame.world.core.WorldCore;
import com.github.joukojo.testgame.world.core.WorldCoreFactory;

public class GraphicEngine extends JFrame {

	private final static Logger LOG = LoggerFactory
			.getLogger(GraphicEngine.class);
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private transient final BufferedImage bufferedImage;
	private transient final BufferStrategy bufferStrategy;

	public GraphicEngine(final GraphicsConfiguration gConfiguration) {
		
		super(gConfiguration);
		setTitle("testgame - alpha");
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		
		setUndecorated(true);
		setSize(DisplayConfiguration.getInstance().getWidth(), DisplayConfiguration.getInstance().getHeight());
		final Canvas canvas = new Canvas();
		canvas.setIgnoreRepaint(true);
		canvas.setSize(DisplayConfiguration.getInstance().getWidth(), DisplayConfiguration.getInstance().getHeight());
		final PlayerMoveListener mouseListener = new PlayerMoveListener();
		canvas.addMouseMotionListener(mouseListener);
		canvas.addMouseListener(mouseListener);

		add(canvas);

		setLocationRelativeTo(null);

		pack();
		setVisible(true);
		canvas.createBufferStrategy(2);
		bufferStrategy = canvas.getBufferStrategy();

		bufferedImage = gConfiguration.createCompatibleImage(DisplayConfiguration.getInstance().getWidth(), DisplayConfiguration.getInstance().getHeight());

	}


	public void drawObjects() {
		LOG.trace("drawing objects");
		// Objects needed for rendering...
		Graphics graphics = null;

		try {

			LOG.debug("clearing buffer");
			// clear back buffer...
			drawBufferImage();

			// Blit image and flip...
			LOG.debug("blit image and flip");
			graphics = bufferStrategy.getDrawGraphics();
			graphics.drawImage(bufferedImage, 0, 0, null);
			if (!bufferStrategy.contentsLost()) {
				bufferStrategy.show();
			}

		} finally {
			// release resources
			if (graphics != null) {
				graphics.dispose();
			}

		}
	}

	private void drawBufferImage() {
		Graphics2D graphics2d = null;
		try {
			graphics2d = bufferedImage.createGraphics();
			drawBackground(graphics2d);

			LOG.trace("drawing objects");
			drawObjects(graphics2d);
			LOG.trace("drawing status texts");
			drawStatusTexts(graphics2d);

			LOG.trace("buffer image is complete");
		} finally {
			if (graphics2d != null) {
				graphics2d.dispose();
			}
		}
	}

	private void drawBackground(final Graphics2D graphics2d) {
		final Color background = Color.BLACK;
		graphics2d.setColor(background);
		graphics2d.fillRect(0, 0, DisplayConfiguration.getInstance().getWidth(), DisplayConfiguration.getInstance().getHeight());
	}

	private void drawStatusTexts(final Graphics2D graphics2d) {
		graphics2d.setFont(new Font("Courier New", Font.PLAIN, 12));
		graphics2d.setColor(Color.GREEN);
		final WorldCore worldCore = WorldCoreFactory.getWorld();
		final Player player = (Player) worldCore.getMoveable("player");
		if (player != null) {
			final int level = player.getLevel();
			graphics2d.drawString("Level:" + level, 20, 20);
			graphics2d.drawString("Score:" + player.getScore(), 20, 40);
			graphics2d.drawString("Health:" + player.getHealth(), 20, 60);
		}
	}

	public void drawObjects(final Graphics graphics) {
		final WorldCore worldCore = WorldCoreFactory.getWorld();
		LOG.trace("Starting to draw drawables");
		final List<Drawable> allDrawables = worldCore.getAllDrawables();

		drawDrawableObjects(graphics, allDrawables);
		LOG.trace("Starting to draw moveables");
		final List<Moveable> allMoveables = worldCore.getAllMoveables();
		drawMoveableObjects(graphics, allMoveables);
		LOG.trace("all objects are drawn");

	}

	protected void drawDrawableObjects(final Graphics g,
			final List<Drawable> allDrawables) {
		for (final Drawable drawable : allDrawables) {
			LOG.trace("drawing object: {}", drawable);
			drawable.draw(g);
		}

		LOG.trace("all drawable objects drawn");
	}

	protected void drawMoveableObjects(final Graphics g,
			final List<Moveable> allMoveables) {
		for (final Drawable drawable : allMoveables) {
			drawable.draw(g);
		}
		LOG.trace("all moveable objects drawn");
	}

}
