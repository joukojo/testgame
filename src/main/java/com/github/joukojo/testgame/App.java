package com.github.joukojo.testgame;

import javax.swing.JOptionPane;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Hello world!
 * 
 */
public class App {

	private static final Logger LOG = LoggerFactory.getLogger(App.class);

	public static void main(String[] args) {

		GameEngine gameEngine = GameEngine.getInstance();
		gameEngine.init();
		LOG.debug("game engine initialized"); 
		
		JOptionPane.showMessageDialog(null, "Ready to play"); 
		
		gameEngine.startGame();
		
		
		
		
		
	}
}
