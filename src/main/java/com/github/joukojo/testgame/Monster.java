package com.github.joukojo.testgame;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import org.apache.commons.lang.builder.ToStringBuilder;

import com.github.joukojo.testgame.images.ImageFactory;
import com.github.joukojo.testgame.world.core.Moveable;

public class Monster implements Moveable {

	int locationX = 0;
	int locationY = 0;
	double directionX = 0.1;
	double directionY = 1;
	private boolean isDestroyed = false;

	@Override
	public void draw(Graphics graphics) {
		if (!isDestroyed()) {
			BufferedImage monsterImage = ImageFactory.getMonsterImage();
			graphics.drawImage(monsterImage, locationX, locationY, 68, 45, null);
		}
	}

	@Override
	public void move() {
		locationX = (int) (locationX + directionX);
		locationY = (int) (locationY + directionY);

	}

	@Override
	public boolean isOutside(int x, int y) {
		return (locationX > x || locationX < 0)
				|| (locationY > y || locationY < 0);
	}

	public int getLocationX() {
		return locationX;
	}

	public void setLocationX(int locationX) {
		this.locationX = locationX;
	}

	public int getLocationY() {
		return locationY;
	}

	public void setLocationY(int locationY) {
		this.locationY = locationY;
	}

	public double getDirectionX() {
		return directionX;
	}

	public void setDirectionX(double directionX) {
		this.directionX = directionX;
	}

	public double getDirectionY() {
		return directionY;
	}

	public void setDirectionY(double directionY) {
		this.directionY = directionY;
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

}
