package com.github.joukojo.testgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.github.joukojo.testgame.images.ImageFactory;
import com.github.joukojo.testgame.world.core.Moveable;

public class Monster implements Moveable {

	private int locationX = 0;
	private int locationY = 0;
	private double directionX = 0.1;
	private double directionY = 1;
	private boolean isDestroyed = false;

	@Override
	public void draw(final Graphics graphics) {
		if (!isDestroyed()) {
			final BufferedImage monsterImage = ImageFactory.getMonsterImage();
			graphics.drawImage(monsterImage, locationX, locationY, 68, 45, null);
		}
	}

	@Override
	public void move() {
		locationX = (int) (locationX + directionX);
		locationY = (int) (locationY + directionY);

	}

	@Override
	public boolean isOutside(final int x, final int y) {
		return (locationX > x || locationX < 0) || (locationY > y || locationY < 0);
	}

	public int getLocationX() {
		return locationX;
	}

	public void setLocationX(final int locationX) {
		this.locationX = locationX;
	}

	public int getLocationY() {
		return locationY;
	}

	public void setLocationY(final int locationY) {
		this.locationY = locationY;
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

	@Override
	public boolean isDestroyed() {
		return isDestroyed;
	}

	@Override
	public void setDestroyed(final boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public static Monster factory() {
		final Monster monster = new Monster();
		return monster; 
	}
}
