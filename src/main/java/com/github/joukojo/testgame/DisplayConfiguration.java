package com.github.joukojo.testgame;

import java.awt.DisplayMode;
import java.awt.GraphicsDevice;
import java.awt.GraphicsEnvironment;

public class DisplayConfiguration {

	private DisplayMode originalDisplayMode;

	public void init() {
		GraphicsEnvironment graphicsEnvironment = GraphicsEnvironment
				.getLocalGraphicsEnvironment();
		GraphicsDevice graphicsDevice = graphicsEnvironment
				.getDefaultScreenDevice();
		originalDisplayMode = graphicsDevice.getDisplayMode();

	}

	public int getHeight() {
		return originalDisplayMode.getHeight();
	}

	public int getWidth() {

		return originalDisplayMode.getWidth();
	}

	private static DisplayConfiguration INSTANCE = new DisplayConfiguration();

	static {
		INSTANCE.init();

	}

	public static DisplayConfiguration getInstance() {
		return INSTANCE;
	}

}
