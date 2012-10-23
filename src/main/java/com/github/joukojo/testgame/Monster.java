package com.github.joukojo.testgame;

import java.awt.Color;
import java.awt.Graphics;

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
			graphics.setColor(Color.GREEN);
			graphics.drawOval(locationX, locationY, 10, 20);
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

}
