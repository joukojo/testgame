package com.github.joukojo.testgame;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

import org.apache.commons.lang.builder.ReflectionToStringBuilder;

public class DisplayConfiguration {

	private DisplayMode displayMode;

	private static DisplayConfiguration instance = new DisplayConfiguration();

	static {
		instance.init();

	}

	
	public void init() {
		final GraphicsEnvironment gEnvironment = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		final GraphicsDevice graphicsDevice = gEnvironment
				.getDefaultScreenDevice();
		setDisplayMode(graphicsDevice.getDisplayMode());

	}

	public int getHeight() {
		return getDisplayMode().getHeight();
	}

	public int getWidth() {

		return getDisplayMode().getWidth();
	}


	public static DisplayConfiguration getInstance() {
		return instance;
	}

	public DisplayMode getDisplayMode() {
		return displayMode;
	}

	public void setDisplayMode(final DisplayMode displayMode) {
		this.displayMode = displayMode;
	}
	
	@Override
	public String toString() {
		return ReflectionToStringBuilder.toString(this);
	}

}
