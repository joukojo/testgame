package com.github.joukojo.testgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.github.joukojo.testgame.images.ImageFactory;
import com.github.joukojo.testgame.world.core.Moveable;

public class Bullet implements Moveable {

	private int locationX;
	private int locationY;
	
	private double directionX = 0.0;
	private double directionY = 0.0;
	
	private boolean destroyed = false;
	private static final ImageObserver OBSERVER = null;

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
	public void draw(final Graphics graphics) {
		if (!isDestroyed()) {
			final BufferedImage bulletImage = ImageFactory.getBulletImage();
			
			graphics.drawImage(bulletImage, locationX, locationY, 15, 15, OBSERVER );
		}

	}

	@Override
	public void move() {
		locationX = (int) (locationX + (directionX * 1.2));
		locationY = (int) (locationY + directionY * 1.2);

	}

	@Override
	public boolean isOutside(final int xCoord, final int yCoord) {
		return (locationX > xCoord || locationX == 0)
				|| (locationY > yCoord || locationY <= 0);
	}

	public boolean isDestroyed() {
		return destroyed;
	}

	public void setDestroyed(final boolean isDestroyed) {
		this.destroyed = isDestroyed;
	}

	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	

}
