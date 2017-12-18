package com.pl.firstclicker;

import com.badlogic.gdx.Game;

import screens.SplashScreen;

public class FirstClickerGame extends Game {

	public final static String GAME_NAME = "First Clicker";

	public static int WIDTH = 480;
	public static int HEIGHT = 700;

	private boolean paused;
	private int points;

	@Override
	public void create () {
		this.setScreen(new SplashScreen(this) {
		});
	}
	public void addPoint(){
		points++;
	}
// GETTERS AND SETTERS

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public int getPoints() {
		return points;
	}
}
