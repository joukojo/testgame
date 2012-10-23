package com.github.joukojo.testgame;

import java.awt.Color;
import java.awt.Graphics;

import com.github.joukojo.testgame.world.core.Moveable;

public class Bullet implements Moveable {

	int locationX;
	int locationY;
	private boolean isDestroyed = false;

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

	double directionX = 0.0;
	double directionY = 0.0;

	@Override
	public void draw(Graphics graphics) {
		if (!isDestroyed()) {
			graphics.setColor(Color.WHITE);

			int deltaY = (int) (locationY + directionY);
			int deltaX = (int) (locationX + directionX);
			graphics.drawLine(locationX, locationY, (int) deltaX, deltaY);
		}

	}

	@Override
	public void move() {
		locationX = (int) (locationX + directionX);
		locationY = (int) (locationY + directionY);

	}

	@Override
	public boolean isOutside(int x, int y) {
		return (locationX > x || locationX <= 0)
				|| (locationY > y || locationY <= 0);
	}

	public boolean isDestroyed() {
		return isDestroyed;
	}

	public void setDestroyed(boolean isDestroyed) {
		this.isDestroyed = isDestroyed;
	}

}
