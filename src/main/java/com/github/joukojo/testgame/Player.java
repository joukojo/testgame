package com.github.joukojo.testgame;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.MouseInfo;
import java.awt.Point;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
	
	public void setTarget(Point point) {
		/*
		 * [px py] (get-in @data [:player :position])
        [tx ty] (get-in @data [:player :target] [0 0])
        dx (* (- tx px) 0.01)
        dy (* (- ty py) 0.01)
        new-position [(+ px dx)
                      (+ py dy)]
        new-direction [dx dy]]
		 */
		
		
	}
	
	@Override
	public void draw(Graphics graphics) {
		graphics.setColor(Color.RED);
		graphics.drawRect(positionX, positionY, 10, 10);

	}

	@Override
	public void move() {
		Point point = MouseInfo.getPointerInfo().getLocation();
		LOG.debug("location x:" + point.x);
		LOG.debug("location y:" + point.y);
		int targetX = point.x;
		int targetY = point.y;
		
		directionX = ((targetX - positionX) * 0.1); 
		directionY = ((targetY - positionY) * 0.1);

		
		positionX = (int) (positionX + directionX); 
		positionY = (int) (positionY + directionY);
	}
	
	@Override
	public boolean isOutside(int x, int y) {
		return ( positionX > x || positionX < 0) || (positionY > y || positionY < 0 ); 
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
