package com.pl.firstclicker;

import com.badlogic.gdx.Game;

import screens.SplashScreen;
import service.ScoreService;

public class FirstClickerGame extends Game {

	public final static String GAME_NAME = "First Clicker";

	public static int WIDTH = 481;
	public static int HEIGHT = 700;

	private ScoreService scoreService;

	private boolean paused;


	@Override
	public void create () {
		init();
		this.setScreen(new SplashScreen(this) {
		});

	}

	private void init(){
		initScoreService();
	}

	private void initScoreService() {
		scoreService = new ScoreService();
	}

// GETTERS AND SETTERS

	public boolean isPaused() {
		return paused;
	}

	public void setPaused(boolean paused) {
		this.paused = paused;
	}

	public ScoreService getScoreService() {
		return scoreService;
	}

}
