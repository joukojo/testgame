package com.github.joukojo.testgame;

import junit.framework.Assert;

import org.junit.Test;

public class PlayerTest {

	@Test
	public void testDirectionEast() {

		final Player player = new Player();

		final double direction = player.direction(1.0, 0.0);

		Assert.assertEquals("wrong direction", 90.0, direction);
	}

	@Test
	public void testDirectionSouth() {

		final Player player = new Player();

		final double direction = player.direction(0.0, 1.0);

		Assert.assertEquals("wrong direction", 180.0, direction);
	}
	
	@Test
	public void testDirectionNorth() {

		final Player player = new Player();

		final double direction = player.direction(0.0, -1.0);

		Assert.assertEquals("wrong direction", 0.0, direction);
	}
	
	@Test
	public void testDirectionWest() {

		final Player player = new Player();

		final double direction = player.direction(-1.0, 0.0);

		Assert.assertEquals("wrong direction", 270.0, direction);
	}
	
	

}
