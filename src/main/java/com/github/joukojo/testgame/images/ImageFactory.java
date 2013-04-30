package com.github.joukojo.testgame.images;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ImageFactory {

	private static BufferedImage BULLETIMAGE;
	private static BufferedImage MONSTERIMAGE;
	private static BufferedImage playerNorthImage;
	private static BufferedImage playerNorthEastImage;
	private static BufferedImage playerEastImage;
	private static BufferedImage playerSouthEastImage;
	private static BufferedImage playerSouthImage;
	private static BufferedImage playerSouthWestImage;
	private static BufferedImage playerWestImage;
	private static BufferedImage playerNorthWestImage;
	private static Map<Integer, BufferedImage> IMAGES = new HashMap<Integer, BufferedImage>();

	private static final Logger LOG = LoggerFactory.getLogger(ImageFactory.class);

	public static BufferedImage loadPlayerImage() {
		return loadImageFromFile("/images/disasteroids2_master.png");
	}

	public static BufferedImage loadMonsterImage() {
		return loadImageFromFile("/images/invader.png");
	}

	private static BufferedImage loadImageFromFile(final String fileName) {
		final InputStream stream = ImageFactory.class.getResourceAsStream(fileName);
		BufferedImage image = null;
		try {
			if (stream != null) {

				image = ImageIO.read(stream);
			} else {
				LOG.error("Reading file:{}", fileName);
			}
		} catch (final IOException e) {
			LOG.error("Reading file:{} :" + e.getMessage(), fileName);

		}

		return image;
	}

	public synchronized static BufferedImage getBulletImage() {

		if (BULLETIMAGE == null) {
			final BufferedImage fullImage = loadPlayerImage();
			try {
				BULLETIMAGE = fullImage.getSubimage(186, 210, 15, 15);
			} finally {
				fullImage.flush();
			}
		}
		return BULLETIMAGE;
	}

	public synchronized static BufferedImage getMonsterImage() {

		if (MONSTERIMAGE == null) {
			final BufferedImage fullImage = loadMonsterImage();
			try {
				MONSTERIMAGE = fullImage.getSubimage(140, 1, 68, 45);
			} finally {
				fullImage.flush();
			}
		}

		return MONSTERIMAGE;
	}

	public synchronized static BufferedImage getPlayerNorthImage() {
		if (playerNorthImage == null) {
			final BufferedImage fullImage = loadPlayerImage();
			try {
				playerNorthImage = fullImage.getSubimage(215, 293, 71, 45);
			} finally {
				fullImage.flush();
			}
		}
		return playerNorthImage;

	}

	public synchronized static BufferedImage getPlayerNorthEastImage() {
		if (playerNorthEastImage == null) {
			final BufferedImage fullImage = loadPlayerImage();
			try {
				playerNorthEastImage = fullImage.getSubimage(149, 293, 69, 45);
			} finally {
				fullImage.flush();
			}
		}

		return playerNorthEastImage;
	}

	public synchronized static BufferedImage getPlayerEastImage() {
		// 556, 286, 30, 65
		if (playerEastImage == null) {
			final BufferedImage fullImage = loadPlayerImage();
			try {
				playerEastImage = fullImage.getSubimage(556, 286, 30, 65);
			} finally {
				fullImage.flush();
			}

		}
		return playerEastImage;

	}

	public synchronized static BufferedImage getPlayerSouthEastImage() {
		// 555,337 51,44
		if (playerSouthEastImage == null) {
			final BufferedImage fullImage = loadPlayerImage();
			try {
				playerSouthEastImage = fullImage.getSubimage(500, 293, 58, 48);
			} finally {
				fullImage.flush();
			}

		}
		return playerSouthEastImage;
	}

	public synchronized static BufferedImage getPlayerSouthImage() {
		// 497, 335 66,32
		if (playerSouthImage == null) {
			final BufferedImage fullImage = loadPlayerImage();
			try {
				playerSouthImage = fullImage.getSubimage(435, 307, 66, 32);
			} finally {
				fullImage.flush();
			}

		}

		return playerSouthImage;
	}

	public synchronized static BufferedImage getPlayerSouthWestImage() {
		// 428, 340 50,52
		if (playerSouthWestImage == null) {
			final BufferedImage fullImage = loadPlayerImage();
			try {
				playerSouthWestImage = fullImage.getSubimage(373, 290, 50, 52);
			} finally {
				fullImage.flush();
			}

		}
		return playerSouthWestImage;
	}

	public synchronized static BufferedImage getPlayerWestImage() {
		if (playerWestImage == null) {
			final BufferedImage fullImage = loadPlayerImage();
			try {
				playerWestImage = fullImage.getSubimage(338, 286, 37, 61);
			} finally {
				fullImage.flush();
			}

		}
		// 375, 350, 37, 61,
		return playerWestImage;
	}

	public synchronized static BufferedImage getPlayerNorthWestImage() {
		if (playerNorthWestImage == null) {
			final BufferedImage fullImage = loadPlayerImage();
			try {
				playerNorthWestImage = fullImage.getSubimage(284, 296, 48, 48);
			} finally {
				fullImage.flush();
			}

		}
		// 298, 319, 48, 48
		return playerNorthWestImage;
	}

	public static BufferedImage getImageForDegree(final int value) {

		if (!IMAGES.containsKey(value)) {
			final BufferedImage image = loadImageFromFile("/images/player-" + value + ".png");
			IMAGES.put(value, image);
		}

		return IMAGES.get(value);
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this).toString();
	}

}
