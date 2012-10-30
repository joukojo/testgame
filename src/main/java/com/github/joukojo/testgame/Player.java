package com.github.joukojo.testgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.joukojo.testgame.images.ImageFactory;
import com.github.joukojo.testgame.world.core.Moveable;

public class Player implements Moveable {

	private final static Logger LOG = LoggerFactory.getLogger(Player.class);
	int positionX = 0;

	public int getPositionX() {
		return positionX;
	}

	public void setPositionX(int positionX) {
		this.positionX = positionX;
	}

	public int getPositionY() {
		return positionY;
	}

	public void setPositionY(int positionY) {
		this.positionY = positionY;
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

	int positionY = 0;
	double directionX = 0;
	double directionY = 0;
	public int level = 1;
	public long score = 0;
	public long health = 100;
	public Point point = new Point(300, 300);

	public void setTarget(Point point) {
		/*
		 * [px py] (get-in @data [:player :position]) [tx ty] (get-in @data
		 * [:player :target] [0 0]) dx (* (- tx px) 0.01) dy (* (- ty py) 0.01)
		 * new-position [(+ px dx) (+ py dy)] new-direction [dx dy]]
		 */

	}

	@Override
	public void draw(Graphics graphics) {

		BufferedImage image = null;

		if (directionX == 0 && directionY < 0) {
			image = ImageFactory.getPlayerNorthImage();

		} else if (directionX > 0 && directionY <0) {
			image = ImageFactory.getPlayerNorthEastImage();

		}
		else if ( directionX > 0.0 && directionY == 0.0 ) {
			image = ImageFactory.getPlayerEastImage();
		}
		else if ( directionX >0.0 && directionY > 0.0 ) {
			image = ImageFactory.getPlayerSouthEastImage();
		}
		else if ( directionX == 0.0 && directionY > 0.0 ) {
			image = ImageFactory.getPlayerSouthImage();
		}
		else if ( directionX < 0.0 && directionY >0.0 ) {
			image = ImageFactory.getPlayerSouthWestImage();
		}
		else if ( directionX == 0.0 && directionY < 0.0 ) {
			image = ImageFactory.getPlayerWestImage();
		}
		else if ( directionX < 0.0 && directionY < 0.0 ) {
			image = ImageFactory.getPlayerNorthWestImage();
		}
		else {
			image = ImageFactory.getPlayerNorthImage();
		}

		if(image != null ) {
			graphics.drawImage(image, positionX, positionY, 80, 80, null);
		}
		else {
			LOG.error("directionX:" + directionX);
			LOG.error("directionY:" + directionY);
		graphics.setColor(Color.RED);
		graphics.drawRect(positionX, positionY, 20, 20);
		}
	}

	@Override
	public void move() {

		int targetX = point.x;
		int targetY = point.y;

		directionX = ((targetX - positionX) * 0.05);
		directionY = ((targetY - positionY) * 0.05);

		double newPositionX = (double) (positionX + directionX);
		double newPositionY = (double) (positionY + directionY);

		LOG.debug("newPositionX {}", newPositionX);
		LOG.debug("newPositionY {}", newPositionY);

		if (newPositionX <= 0) {
			positionX = 0;
		} else if (newPositionX >= Constants.SCREEN_WIDTH) {
			positionX = Constants.SCREEN_WIDTH;
		} else {
			positionX = (int) newPositionX;
		}

		if (newPositionY <= 0) {
			positionY = 0;
		} else if (newPositionY >= Constants.SCREEN_HEIGHT) {
			positionY = Constants.SCREEN_HEIGHT;
		} else {
			positionY = (int) newPositionY;
		}
		LOG.debug("PositionX {}", positionX);
		LOG.debug("PositionY {}", positionY);

	}

	@Override
	public boolean isOutside(int x, int y) {
		return (positionX > x || positionX < 0) || (positionY > y || positionY < 0);
	}

	@Override
	public boolean isDestroyed() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public void setDestroyed(boolean b) {
		// TODO Auto-generated method stub

	}

}
