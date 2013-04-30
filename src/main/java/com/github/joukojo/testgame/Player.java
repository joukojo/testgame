package com.github.joukojo.testgame;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.joukojo.testgame.images.ImageFactory;
import com.github.joukojo.testgame.world.core.Moveable;

/**
 * Player class for storing the player location
 * 
 * @author joukojo
 * 
 */
public class Player implements Moveable {

	private final static Logger LOG = LoggerFactory.getLogger(Player.class);
	private int positionY = 0;
	private double directionX = 0;
	private double directionY = 0;
	private int level = 1;
	private long score = 0;
	private long health = 100;
	private Point point = new Point(300, 300);
	private int positionX = 0;

	/**
	 * Gets the player position x coordinates in area
	 * 
	 * @return location in x
	 */
	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(final int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(final int positionY) {
		this.positionY = positionY;
	}

	public double getDirectionX() {
		return directionX;
	}

	public void setDirectionX(final double directionX) {
		this.directionX = directionX;
	}

	public double getDirectionY() {
		return directionY;
	}

	public void setDirectionY(final double directionY) {
		this.directionY = directionY;
	}

	public void setTarget(final Point point) {
		/*
		 * [px py] (get-in @data [:player :position]) [tx ty] (get-in @data
		 * [:player :target] [0 0]) dx (* (- tx px) 0.01) dy (* (- ty py) 0.01)
		 * new-position [(+ px dx) (+ py dy)] new-direction [dx dy]]
		 */

	}

	@Override
	public void draw(final Graphics graphics) {
		final BufferedImage image = ImageFactory.getImageForDegree(1);

		graphics.drawImage(image, positionX, positionY, null);

	}

	@Override
	public void move() {

		final int targetX = getPoint().x;
		final int targetY = getPoint().y;

		directionX = (targetX - positionX) * 0.05;
		directionY = (targetY - positionY) * 0.05;

		final double newPositionX = positionX + directionX;
		final double newPositionY = positionY + directionY;

		if (LOG.isDebugEnabled()) {
			LOG.debug("newPositionX {}", newPositionX);
			LOG.debug("newPositionY {}", newPositionY);
		}

		if (newPositionX <= 0) {
			positionX = 0;
		} else if (newPositionX >= DisplayConfiguration.getInstance().getWidth()) {
			positionX = DisplayConfiguration.getInstance().getWidth();
		} else {
			positionX = (int) newPositionX;
		}

		if (newPositionY <= 0) {
			positionY = 0;
		} else if (newPositionY >= DisplayConfiguration.getInstance().getHeight()) {
			positionY = DisplayConfiguration.getInstance().getHeight();
		} else {
			positionY = (int) newPositionY;
		}
		LOG.debug("PositionX {}", positionX);
		LOG.debug("PositionY {}", positionY);

	}

	@Override
	public boolean isOutside(final int xCoord, final int yCoord) {
		return positionX > xCoord || positionX < 0 || positionY > yCoord || positionY < 0;
	}

	@Override
	public boolean isDestroyed() {

		return !(getHealth() > 0);
	}

	@Override
	public void setDestroyed(final boolean isDestroyed) {
		// the player is destroyed -> game over.
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(final int level) {
		this.level = level;
	}

	public long getScore() {
		return score;
	}

	public void setScore(final long score) {
		this.score = score;
	}

	public long getHealth() {
		return health;
	}

	public void setHealth(final long health) {
		this.health = health;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(final Point point) {
		this.point = point;
	}

}
