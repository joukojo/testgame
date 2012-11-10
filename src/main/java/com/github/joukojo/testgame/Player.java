package com.github.joukojo.testgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.github.joukojo.testgame.world.core.Moveable;

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



	public void setTarget(Point point) {
		/*
		 * [px py] (get-in @data [:player :position]) [tx ty] (get-in @data
		 * [:player :target] [0 0]) dx (* (- tx px) 0.01) dy (* (- ty py) 0.01)
		 * new-position [(+ px dx) (+ py dy)] new-direction [dx dy]]
		 */

	}

	@Override
	public void draw(Graphics graphics) {
		
		graphics.setColor(Color.RED);
		graphics.drawOval(positionX, positionY, 50, 50);
		
//		double direction = direction(directionX, directionY);
//		
//		BufferedImage image = ImageFactory.getPlayerNorthImage();
//		
		
		
	}
	
	
	double direction(double x, double y) {
	    if (x > 0)
	        return Math.atan(y/x);
	    if (x < 0)
	        return Math.atan(y/x)+Math.PI;
	    if (y > 0)
	        return Math.PI/2;
	    if (y < 0)
	        return -Math.PI/2;
	    return 0; // no direction
	}

	@Override
	public void move() {

		int targetX = getPoint().x;
		int targetY = getPoint().y;

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
		
		return !(getHealth() > 0);
	}

	@Override
	public void setDestroyed(boolean b) {
		

	}
	
	@Override
	public String toString() {
		return ToStringBuilder.reflectionToString(this);
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public long getScore() {
		return score;
	}

	public void setScore(long score) {
		this.score = score;
	}

	public long getHealth() {
		return health;
	}

	public void setHealth(long health) {
		this.health = health;
	}

	public Point getPoint() {
		return point;
	}

	public void setPoint(Point point) {
		this.point = point;
	}


}
